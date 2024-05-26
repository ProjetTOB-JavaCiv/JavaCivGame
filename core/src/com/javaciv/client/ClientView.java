/**
 * @file ClientView.java
 * @brief This file contains the ClientView class.
 * @date 20/04/2024
 */
package com.javaciv.client;

import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.gameElement.City;
import com.javaciv.type.LandType;
import com.javaciv.server.Server;
import com.javaciv.GameInterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class ClientView extends ScreenAdapter {
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
    private GestureDetector gestureDectector;

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

    /**
     * Widgets
     */
    private Skin skin;
    private Menu topMenu;
    private Menu tileMenu;
    private Menu playerMenu;
    private Menu logMenu;
    private List<Label> cityNames = new ArrayList<Label>();
    private List<Vector2> selectedTiles = new ArrayList<Vector2>();

    /**
     * Utils
     */
    private Vector3 tileMenuWorldCoordinates;
    private HashMap<Integer, Color> clientColors = new HashMap<Integer, Color>();
    private HashMap<Integer, TextureRegion> clientTextureRegions = new HashMap<Integer, TextureRegion>();

    /**
     * Constructor for ClientView
     */
    public ClientView(ClientController controller) {
        // Load configuration from `config.txt` file
        loadConfiguration("config/config.txt");

        // Set the controller and the map
        
        this.controller = controller;

        // Load all the textures for the TileMap
        this.tileTextures = new Texture[] {
            //TODO : Mettre les textures dans un dossier + Faire correspondre les textures avec les types de terrain
            new Texture(Gdx.files.internal("textures/tiles/Grass.png")), // 0 -> Plaine
            new Texture(Gdx.files.internal("textures/tiles/Sand.png")), // 1 -> Desert
            new Texture(Gdx.files.internal("textures/tiles/Dirt.png")), // 2 -> Foret
            new Texture(Gdx.files.internal("textures/tiles/Mountain.png")), // 3 -> Montagne
            new Texture(Gdx.files.internal("textures/tiles/Grass1.png")), // 4 -> Colline
            new Texture(Gdx.files.internal("textures/tiles/Water.png")) // 5 -> Mer
        };

        this.cityTextures = new Texture[] {
            new Texture(Gdx.files.internal("textures/City.png"))
        };

        this.selectTextures = new Texture[] {
            new Texture(Gdx.files.internal("textures/Selected.png")),
            new Texture(Gdx.files.internal("whiteframe.png")),
            new Texture(Gdx.files.internal("dottedgreyframe.png"))
        };

        // Load the colors for the clients
        this.initClientColors();
        this.initClientTextureRegions();

        // Load the skin for the UI
        this.skin = new Skin(Gdx.files.internal("skin/skin.json"));

        this.topMenu = new Menu(
            new Actor[] {
                new TextButton("Pass tour", this.skin, "default"),
                new TextButton("Save game", this.skin, "default"),
                new TextButton("Exit game", this.skin, "default")
            },
            new ClickListener[] {
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        //System.out.println("Button 1 clicked !");
                        controller.nextTurn();
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        //System.out.println("Button 2 clicked !");
                        controller.saveGame();
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        //System.out.println("Button 3 clicked !");
                        Gdx.app.exit();
                        System.exit(0);
                    }
                }
            }
        );

        this.topMenu.setPosition(0, Gdx.graphics.getHeight());

        this.logMenu = new Menu(
            new Actor[] {
                new Label("", this.skin, "default")
            },
            new ClickListener[] {
                new ClickListener(){}
            }
        );

        this.logMenu.setPosition(0, this.logMenu.getHeight());

        this.tileMenu = new Menu(
            new Actor[] {
                new Label(getClickCoordinatesText(), this.skin, "default"),
                new TextButton("Create City", this.skin, "default"),
                new TextButton("Buy tile", this.skin, "default"),
                new Label("Production: " + getTileAt(getClickCoordonatesnotnull()).getProduction().getProduction(), this.skin, "default"),
                new Label("Food: " + getTileAt(getClickCoordonatesnotnull()).getProduction().getFood(), this.skin, "default")
            },
            new ClickListener[] {
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        //System.out.print("Coordinates clicked, current case is : ");
                        //System.out.println("[" + (int) getClickCoordinates().x + ", " + (int) getClickCoordinates().y + "]");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        //System.out.print("Action 1 clicked, current case is : ");
                        //System.out.println("[" + (int) getClickCoordinates().x + ", " + (int) getClickCoordinates().y + "]");
                        //Calcul des coordonnées converti en int de la position de la ville
                        int xPos = (int) tileMenuWorldCoordinates.x / tileSize;
                        int yPos = (int) tileMenuWorldCoordinates.y / tileSize;
                        //controller.getWorldMap().at((int) getClickCoordinates().x, (int) getClickCoordinates().y).setLand(LandType.MONTAGNE);
                        if (controller.addCity(controller.getWorldMap().at(xPos, yPos))) {
                            // TODO : move this to as specific function with a loop over the cities
                            City city = controller.getCities().get(controller.getCities().size() - 1);
                            controller.getWorldMap().at((int) getClickCoordinates().x, (int) getClickCoordinates().y).setCity(city);
                            Label cityName = getCityName(city);
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
                        if (isCityClicked((int) tileMenuWorldCoordinates.x / tileSize, (int) tileMenuWorldCoordinates.y / tileSize)) {
                            //System.out.println("Buy tile");
                            // Get the city at the clicked coordinates
                            for (City city : controller.getCities()) {
                                if (city.getX() == (int) tileMenuWorldCoordinates.x / tileSize && city.getY() == (int) tileMenuWorldCoordinates.y / tileSize) {
                                    //System.out.println("Buying tile for city : " + city.getName());
                                    controller.buyTile(city);
                                }
                            }
                        }
                    }
                },

                new ClickListener(){},
                new ClickListener(){}

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
                        //System.out.println("Faith clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        //System.out.println("Gold clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        //System.out.println("Culture clicked !");
                    }
                },
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent e, float x, float y){
                        //System.out.println("Science clicked !");
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

        // Add a gesture detector to the input multiplexer
        this.inputMultiplexer.addProcessor(new GestureDetector(new ClientGesture(camera, controller, this)));

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

        // Hide "Buy tile" button if there is no city on the tile
        if (this.controller.getDisplayTileMenu()) {
            if (isCityClicked((int) tileMenuWorldCoordinates.x / tileSize, (int) tileMenuWorldCoordinates.y / tileSize)) {
                ((TextButton) this.tileMenu.getMenuItems()[2]).setVisible(true);
            } else {
                ((TextButton) this.tileMenu.getMenuItems()[2]).setVisible(false);
            }
        }

        ((Label) this.logMenu.getMenuItems()[0]).setText(this.controller.getLog());

        this.logMenu.resizeMenu();

        // Actualisation des informations sur le joueur
        ((Label) this.playerMenu.getMenuItems()[0]).setText("Faith : " + this.controller.getGameInfos().get("faith"));
        ((Label) this.playerMenu.getMenuItems()[1]).setText("Gold : " + this.controller.getGameInfos().get("gold"));
        ((Label) this.playerMenu.getMenuItems()[2]).setText("Culture : " + this.controller.getGameInfos().get("culture"));
        ((Label) this.playerMenu.getMenuItems()[3]).setText("Science : " + this.controller.getGameInfos().get("science"));

        ((Label) this.tileMenu.getMenuItems()[3]).setText("Production: " + getTileAt(getClickCoordonatesnotnull()).getProduction().getProduction());
        ((Label) this.tileMenu.getMenuItems()[4]).setText("Food: " + getTileAt(getClickCoordonatesnotnull()).getProduction().getFood());

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
        this.menuStage.addActor(this.logMenu);
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
        if (this.cityNames != null && this.controller.getCities() != null) {
            for(int i = 0; i < this.cityNames.size(); i++) {
                City city = this.controller.getCities().get(i);
                Vector3 cityCoords = this.camera.project(new Vector3(city.getX() * this.tileSize, (city.getY() + 1) * this.tileSize, 0));
                this.cityNames.get(i).setPosition(cityCoords.x, cityCoords.y);
            }
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
     * Renvoie la tuile à une position donnée.
     * @param coords les coordonnées de la tuile
     * @return la tuile selectionnée
     */
    public Tile getTileAt(Vector2 coords) {
        return this.controller.getWorldMap().at((int) coords.x, (int) coords.y);
    }

    /**
     * Renvoie la ville à une position donnée.
     * @param coords les coordonnées de la tuile
     * @return la ville sur la tuile selectionnée
     */
    private City getCityAt(Vector2 coords) {
        return this.controller.getWorldMap().at((int) coords.x, (int) coords.y).getCity();
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
        for (City city : this.controller.getServer().getAllCities()) {
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

        TiledMapTileLayer cityTerritory = new TiledMapTileLayer(map.getWidth(), map.getHeight(), this.tileSize, this.tileSize);
        for (City city : this.controller.getServer().getAllCities()) {
            for (Tile tile : city.getCityTiles()) {
                final TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                cell.setTile(
                    new StaticTiledMapTile(
                        this.clientTextureRegions.get(city.getOwner().getClientId())
                    )
                );
                cityTerritory.setCell(tile.getX(), tile.getY(), cell);
            }
        }
        tiledMap.getLayers().add(cityTerritory);

        return tiledMap;
    }

    /**
     * Renvoie les coordonnées de la tuile cliquée par l'utilisateur.
     * @return les coordonnées de la tuile cliquée
     */
    public Vector2 getClickCoordinates() {
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

    /** Uniquement utilisé dans client controller pour gerer l'ouverture du menu des villes */
    public int getTileSize() {
        return this.tileSize;
    }

    /**
     * Met à jour la carte.
     */
    public void updateMap() {
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

    private Vector2 getClickCoordonatesnotnull() {
        if (this.camera != null) {
            return getClickCoordinates();
        } else {
            return new Vector2(0, 0);
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


    public void Leftclicked(float x, float y){
        //System.out.println("Click at [" + x + ", " + y + "]");
        int clickedX = (int) getMouseCoordinates().x; 
        int clickedY = (int) getMouseCoordinates().y;
        if (isCityClicked(clickedX, clickedY)) {
            //System.out.println("City clicked at coordinates: [" + clickedX + ", " + clickedY + "]");
            //Affichage du menu de la ville
        } else {
            //System.out.println("No city clicked. Coordinates: [" + clickedX + ", " + clickedY + "]");
        }
    }

    /** Méthode déterminant la présence d'une ville sur une tuile de coordonnées x et y.
     * @param x abscisse sur la carte
     * @param y ordonnées
     * @return si une ville existe ou non
    */
    public boolean isCityClicked(int x, int y) {
        return this.controller.getWorldMap().at(x, y).getCity() != null;
    }
    
    private TextureRegion getTintedTextureRegion(Texture texture, Color color) {
        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        Pixmap originalPixmap = texture.getTextureData().consumePixmap();
        Pixmap pixmap = new Pixmap(texture.getWidth(), texture.getHeight(), Pixmap.Format.RGBA8888);
    
        for (int x = 0; x < texture.getWidth(); x++) {
            for (int y = 0; y < texture.getHeight(); y++) {
                Color pixelColor = new Color();
                Color.rgba8888ToColor(pixelColor, originalPixmap.getPixel(x, y));
                pixelColor.mul(color);
                pixmap.drawPixel(x, y, Color.rgba8888(pixelColor));
            }
        }
    
        Texture newTexture = new Texture(pixmap);
        originalPixmap.dispose();
        pixmap.dispose();
        return new TextureRegion(newTexture);
    }

    private Label getCityName(City city) {
        Label cityName = new Label(city.getName(), skin, "backgrounded");
        Pixmap background = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        Color color = this.clientColors.get(city.getOwner().getClientId());
        color = new Color(color.r, color.g, color.b, 0.5f);
        background.setColor(color);
        background.fill();
        cityName.getStyle().background = new TextureRegionDrawable(new TextureRegion(new Texture(background)));
        return cityName;
    }

    private void initClientColors() {
        for (int i = 0; i < ((Server) this.controller.getServer()).getClientCount(); i++) {
            // Generate random color for each client
            Color color = new Color(
                (float) Math.random(),
                (float) Math.random(),
                (float) Math.random(),
                1
            );
            this.clientColors.put(i, color);
        }
    }

    private void initClientTextureRegions() {
        for (int i = 0; i < ((Server) this.controller.getServer()).getClientCount(); i++) {
            //System.out.println(getTintedTextureRegion(this.selectTextures[1], this.clientColors.get(i)) != null);
            this.clientTextureRegions.put(i, getTintedTextureRegion(this.selectTextures[1], this.clientColors.get(i)));
        }
    }

    @Override public void dispose() {
        for(Texture t : this.tileTextures) {
            t.dispose();
        }
        for(Texture t : this.cityTextures) {
            t.dispose();
        }
        for(Texture t : this.selectTextures) {
            t.dispose();
        }
        this.skin.dispose();
        this.mapStage.dispose();
        this.menuStage.dispose();
        this.labelStage.dispose();
    }
}