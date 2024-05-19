/**
 * @file ClientView.java
 * @brief This file contains the ClientView class.
 * @author Théo Bessel
 * @date 20/04/2024
 * @version 1.0
 */

package com.javaciv.client;

import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.gameElement.City;
import com.javaciv.type.LandType;
import com.javaciv.client.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ClientView implements Screen {
    /**
     * Controller associated with this view
     */
    private ClientController controller;

    /**
     * LibGDX objects
     */
    private Stage mapStage;
    private Stage menuStage;
    private Stage labelStage;
    private OrthographicCamera camera;
    private InputMultiplexer inputMultiplexer;

    /**
     * TiledMap objects
     */
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    /**
     * Game objects
     */
    private Texture[] tileTextures;
    private Texture[] cityTextures;
    private Texture[] selectTextures;

    /**
     * Configuration objects
     */
    private int renderDistance;
    private int tileSize;
    private int moveSpeed;
    private Vector3 tileMenuWorldCoordinates;

    /**
     * Widgets
     */
    private Skin skin;
    private Menu topMenu;
    private Menu tileMenu;
    private Menu playerMenu;
    private List<Label> cityNames = new ArrayList<Label>();
    private List<Vector2> selectedTiles = new ArrayList<Vector2>();


    /**
     * Constructor for ClientView
     */
    public ClientView(ClientController controller) {
        // Load configuration from `config.txt` file
        loadConfiguration("config.txt");

        // Set the controller and the map
        
        this.controller = new ClientController(this);
        this.controller = controller;

        // Load all the textures for the TileMap
        this.tileTextures = new Texture[] {
            //TODO : Mettre les textures dans un dossier + Faire correspondre les textures avec les types de terrain
            new Texture(Gdx.files.internal("Grass.png")), // 0 -> Plaine
            new Texture(Gdx.files.internal("Sand.png")), // 1 -> Desert
            new Texture(Gdx.files.internal("Dirt.png")), // 2 -> Foret
            new Texture(Gdx.files.internal("Mountain.png")), // 3 -> Montagne
            new Texture(Gdx.files.internal("Grass1.png")), // 4 -> Colline
            new Texture(Gdx.files.internal("Water.png")) // 5 -> Mer
        };

        this.cityTextures = new Texture[] {
            new Texture(Gdx.files.internal("City.png"))
        };

        this.selectTextures = new Texture[] {
            new Texture(Gdx.files.internal("redframe.png"))
        };

        //Load all other textures to put on Tiles
        Texture gearTexture = new Texture(Gdx.files.internal("gear.png"));

        // Load the skin for the UI
        this.skin = new Skin(Gdx.files.internal("skin.json"));

        this.topMenu = new Menu(
            new Actor[] {
                new TextButton("Pass tour", this.skin, "default"),
                new TextButton("Bouton 2", this.skin, "default"),
                new TextButton("Bouton 3", this.skin, "default"),
                new TextButton("Bouton 4", this.skin, "default"),
                new TextButton("Bouton 5", this.skin, "default")
            },
            new ClickListener[] {
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Button 1 clicked !");
                        controller.nextTurn();
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Button 2 clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Button 3 clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Button 4 clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Button 5 clicked !");
                    }
                }
            }
        );

        this.topMenu.setPosition(0, Gdx.graphics.getHeight());

        this.tileMenu = new Menu(
            new Actor[] {
                new Label(getClickCoordinatesText(), this.skin, "default"),
                new TextButton("Create City", this.skin, "default"),
                new TextButton("Action 2", this.skin, "default")
            },
            new ClickListener[] {
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.print("Coordinates clicked, current case is : ");
                        System.out.println("[" + (int) getClickCoordinates().x + ", " + (int) getClickCoordinates().y + "]");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.print("Action 1 clicked, current case is : ");
                        System.out.println("[" + (int) getClickCoordinates().x + ", " + (int) getClickCoordinates().y + "]");
                        //controller.getWorldMap().at((int) getClickCoordinates().x, (int) getClickCoordinates().y).setLand(LandType.MONTAGNE);
                        if (controller.addCity(controller.getWorldMap().at((int) getClickCoordinates().x, (int) getClickCoordinates().y))) {
                            // TODO : move this to as specific function with a loop over the cities
                            City city = controller.getCities().get(controller.getCities().size() - 1);
                            Label cityName = new Label(city.getName(), skin, "backgrounded");
                            Pixmap background = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
                            background.setColor(new Color(0, 1, 1, 0.2f));
                            background.fill();
                            TextureRegionDrawable backgroundTexture = new TextureRegionDrawable(new TextureRegion(new Texture(background)));
                            cityName.getStyle().background = backgroundTexture;
                            cityNames.add(
                                cityName
                            );
                            labelStage.addActor(cityName);
                        }
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.print("Action 2 clicked, current case is : ");
                        System.out.println("[" + (int) getClickCoordinates().x + ", " + (int) getClickCoordinates().y + "]");
                        controller.getWorldMap().at((int) getClickCoordinates().x, (int) getClickCoordinates().y).setLand(LandType.MER);
                    }
                }
            },
            true // Make the menu a row menu
        );

        ((Label) this.tileMenu.getMenuItems()[0]).setAlignment(Align.center);

        this.tileMenu.setPosition(Gdx.graphics.getWidth() - this.tileMenu.getWidth(), Gdx.graphics.getHeight());

        this.playerMenu = new Menu(
            new Actor[] {
                new Label("Faith :      " + this.controller.getGameInfos().get("faith"), this.skin, "default"),
                new Label("Gold : " + this.controller.getGameInfos().get("gold"), this.skin, "default"),
                new Label("Culture : " + this.controller.getGameInfos().get("culture"), this.skin, "default"),
                new Label("Science : " + this.controller.getGameInfos().get("science"), this.skin, "default")
            },
            new ClickListener[] {
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Faith clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Gold clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Culture clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        System.out.println("Science clicked !");
                    }
                }
            },
            true
        );

        this.playerMenu.setPosition(Gdx.graphics.getWidth() - this.playerMenu.getWidth(), Gdx.graphics.getHeight());

        // Setup the stagess for the tilemap and for the menus
        this.mapStage = new Stage(new ScreenViewport());
        this.menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.labelStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));


        // Setup the camera for the tilemap (the camera will be able to move and zoom on the map)
        this.camera = (OrthographicCamera) this.mapStage.getViewport().getCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.update();

        // Load the map and the renderer
        this.tiledMap = loadMap(this.controller.getWorldMap());
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);

        // Create an input multiplexer, which will handle all the inputs
        // We give to this input multiplexer the ownership of all the input processors
        this.inputMultiplexer = new InputMultiplexer();
        this.inputMultiplexer.addProcessor(this.menuStage);
        this.inputMultiplexer.addProcessor(this.controller);

        // Set the input multiplexer as the input processor for the game
        Gdx.input.setInputProcessor(this.inputMultiplexer);
    }

    @Override public void render(float t) {
        // Clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the game variables :
        if (this.controller.getDisplayTileMenu()) {
            ((Label) this.tileMenu.getMenuItems()[0]).setText("["
                + (int) tileMenuWorldCoordinates.x / this.tileSize
                + ", "
                + (int) tileMenuWorldCoordinates.y / this.tileSize
                + "]");
        }
        ((Label) this.playerMenu.getMenuItems()[0]).setText("Faith : " + this.controller.getGameInfos().get("faith"));
        ((Label) this.playerMenu.getMenuItems()[1]).setText("Gold : " + this.controller.getGameInfos().get("gold"));
        ((Label) this.playerMenu.getMenuItems()[2]).setText("Culture : " + this.controller.getGameInfos().get("culture"));
        ((Label) this.playerMenu.getMenuItems()[3]).setText("Science : " + this.controller.getGameInfos().get("science"));

        this.playerMenu.resizeMenu();
        this.playerMenu.setPosition(Gdx.graphics.getWidth() - this.playerMenu.getWidth(), Gdx.graphics.getHeight());

        // Update camera with all the zoom and movement stuff
        float newZoom = this.camera.zoom * this.controller.getZoom();
        if (newZoom != 0) {
            if (Math.min(
                    this.controller.getWorldMap().getWidth() * this.tileSize - Gdx.graphics.getWidth() * newZoom,
                    this.controller.getWorldMap().getHeight() * this.tileSize - Gdx.graphics.getHeight() * newZoom
                ) > 0 && newZoom > 0.15f) {
                    this.camera.zoom = newZoom;
                    this.controller.setZoom(1.0f);
            }
        }
        this.camera.update();
        if (this.controller.getDisplayTileMenu()) {
            this.tileMenu.setVisible(true);
            // Convertir les coordonnées de la case spécifique en coordonnées d'tileMenuWorldCoordinates
            Vector3 screenCoords = this.camera.project(new Vector3(tileMenuWorldCoordinates.x, tileMenuWorldCoordinates.y, 0));
            this.tileMenu.setPosition(screenCoords.x, screenCoords.y);
        } else {
            this.tileMenu.setVisible(false);
        }
        
        

        // Update camera position
        this.camera.position.x += this.camera.zoom * this.moveSpeed * this.controller.getMovement().x;
        this.camera.position.y += this.camera.zoom * this.moveSpeed * this.controller.getMovement().y;

        // Render the map in the mapStage
        this.tiledMapRenderer.setView(this.camera);
        this.tiledMapRenderer.render();

        // TODO : Add a function to update the map only when needed
        // This can be done by sending a signal from the server to all the clients in Server.nextTurn() function
        this.updateMap();
        
        // Draw the mapStage
        this.mapStage.act(Gdx.graphics.getDeltaTime());
        this.mapStage.getViewport().apply();
        this.mapStage.draw();

        // Render the menus in the menuStage
        this.menuStage.addActor(this.topMenu);
        this.menuStage.addActor(this.tileMenu);
        this.menuStage.addActor(this.playerMenu);
        


        // Draw the labelStage
        this.labelStage.act(Gdx.graphics.getDeltaTime());
        this.labelStage.getViewport().apply();
        this.labelStage.draw();
        
        // Draw the menuStage
        this.menuStage.act(Gdx.graphics.getDeltaTime());
        this.menuStage.getViewport().apply();
        this.menuStage.draw();

    }

    void renderCities() {
        if (this.cityNames != null) {
            for(int i = 0; i < this.cityNames.size(); i++) {
                City city = this.controller.getCities().get(i);
                Vector3 cityCoords = this.camera.project(new Vector3(city.getX() * this.tileSize, (city.getY() + 1) * this.tileSize, 0));
                this.cityNames.get(i).setPosition(cityCoords.x, cityCoords.y);
            }
        }
    }

    @Override public void resize(int w, int h) {}

    @Override public void pause() {}

    @Override public void resume() {}

    @Override public void hide() {}

    @Override public void show() {}

    @Override public void dispose() {
        for(Texture t : this.tileTextures) {
            t.dispose();
        }
    }

    /**
     * Parse la configuration du jeu. Exemple de configuration :
     * renderDistance=40
     * tileSize=5
     * moveSpeed=2000
     * @param key la clé de la configuration
     * @return la valeur de la configuration
     */
    private int parseConfiguration(FileHandle file, String key) {
        String[] lines = file.readString().split("\n");
        for(String line : lines) {
            String[] parts = line.split("=");
            if(parts[0].equals(key)) {
                return Integer.parseInt(parts[1].trim());
            }
        }
        return 0;
    }

    /**
     * Charge la configuration du jeu.
     * @param path le chemin du fichier de configuration
     */
    private void loadConfiguration(String path) {
        this.renderDistance = parseConfiguration(Gdx.files.internal(path), "renderDistance");
        this.tileSize = parseConfiguration(Gdx.files.internal(path), "tileSize");
        this.moveSpeed = parseConfiguration(Gdx.files.internal(path), "moveSpeed");
    }

    /**
     * Charge une carte à partir d'une carte du jeu.
     * @param map la carte du jeu
     * @return la carte chargée
     */
    private TiledMap loadMap(WorldMap map) {
        TiledMap tiledMap = new TiledMap();
        TiledMapTileLayer tiledMapLayer0 = new TiledMapTileLayer(map.getWidth(), map.getHeight(), this.tileSize, this.tileSize);
        tiledMap.getLayers().add(tiledMapLayer0);
        
        for(int i = 0; i < map.getHeight(); i++) {
            for(int j = 0; j < map.getWidth(); j++) {
                final TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(
                    new StaticTiledMapTile(
                        new TextureRegion(
                            this.tileTextures[map.at(j, i).getLand().toInt()], 0, 0, this.tileSize, this.tileSize
                        )
                    )
                );
                tiledMapLayer0.setCell(j, i, cell);
            }
        }

        TiledMapTileLayer tiledMapLayer1 = new TiledMapTileLayer(map.getWidth(), map.getHeight(), this.tileSize, this.tileSize);
        for (City city : this.controller.getCities()) {
            final TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
            cell.setTile(
                new StaticTiledMapTile(
                    new TextureRegion(
                        this.cityTextures[0], 0, 0, this.tileSize, this.tileSize
                    )
                )
            );
            tiledMapLayer1.setCell(city.getX(), city.getY(), cell);
        }
        tiledMap.getLayers().add(tiledMapLayer1);
        

        TiledMapTileLayer redFrameLayer = new TiledMapTileLayer(map.getWidth(), map.getHeight(), this.tileSize, this.tileSize);
        for (Vector2 selectedTile : selectedTiles) {
            final TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
            cell.setTile(
                new StaticTiledMapTile(
                    new TextureRegion(
                        this.selectTextures[0], 0, 0, this.tileSize, this.tileSize
                    )
                )
            );
            redFrameLayer.setCell((int) selectedTile.x, (int) selectedTile.y, cell);
        }
        tiledMap.getLayers().add(redFrameLayer);

        return tiledMap;
    }

    /**
     * Renvoie les coordonnées de la tuile cliquée par l'utilisateur.
     * @return les coordonnées de la tuile cliquée
     */
    private Vector2 getClickCoordinates() {
        Vector3 mouseCoords = this.camera.unproject(new Vector3(this.controller.getClickCoordinates(), 0));
        return new Vector2(
            (int) ( mouseCoords.x / this.tileSize ),
            (int) ( mouseCoords.y / this.tileSize )
        );
    }

    /**
     * Renvoie les coordonnées de la souris dans la map.
     * @return les coordonnées de la souris
     */
    private Vector2 getMouseCoordinates() {
        Vector3 mouseCoords = this.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        return new Vector2(
            (int) ( mouseCoords.x / this.tileSize ),
            (int) ( mouseCoords.y / this.tileSize )
        );
    }

    /**
     * Met à jour la carte.
     */
    private void updateMap() {
        this.renderCities();
        this.tiledMap = loadMap(this.controller.getWorldMap());
        this.tiledMapRenderer.setMap(this.tiledMap);
    }

    private boolean isInMap(Vector2 coords) {
        return
            coords.x >= 0 && 
            coords.x < this.controller.getWorldMap().getWidth() && 
            coords.y >= 0 && 
            coords.y < this.controller.getWorldMap().getHeight();
    }

    private String getClickCoordinatesText() {
        // Update tile menu coordinates text
        if (this.camera != null && isInMap(getClickCoordinates())) {
            return "["
                + (int) (getClickCoordinates().x)
                + ", "
                + (int) (getClickCoordinates().y)
                + "]";
        } else {
            return "[x, y]";
        }
    }


    public void openTileMenuAt(Vector2 coordinates) {
        //if (isInMap(coordinates)) {
            // Met à jour les coordonnées du menu de la tuile
            selectedTiles.clear();
            this.tileMenu.setPosition(coordinates.x, Gdx.graphics.getHeight() - coordinates.y);
            tileMenuWorldCoordinates = this.camera.unproject(new Vector3(coordinates.x, coordinates.y, 0));
            // Rend visible le menu de la tuile
            this.controller.setDisplayTileMenu(true);
            selectedTiles.add(getClickCoordinates());
        //}
    }

    public void closeAllSelectedTiles() {
        selectedTiles.clear();
    }

}