package com.javaciv.client;

import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.type.LandType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.Align;
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

import java.util.Arrays;

public class ClientView implements Screen {
    private ClientController controller;

    /**
     * LibGDX objects
     */
    private Stage mapStage;
    private Stage menuStage;
    private OrthographicCamera camera;
    private InputMultiplexer inputMultiplexer;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    /**
     * Game objects
     */
    private WorldMap map;
    private Texture[] tileTextures;

    private int renderDistance;
    private int tileSize;
    private int moveSpeed;

    private Skin skin;
    private Table menuBar;
    private Table tileMenu;

    private Actor[] menuButtons;
    private Actor[] tileMenuButtons;

    private Label coordinates;

    private TextureRegionDrawable backgroundTexture;

    public ClientView(ClientController controller, WorldMap map) {
        loadConfiguration("config.txt");

        this.inputMultiplexer = new InputMultiplexer();

        this.controller = controller;
        this.map = map;

        this.tileTextures = new Texture[] {
            //TODO : Mettre les textures dans un dossier + Faire correspondre les textures avec les types de terrain
            new Texture(Gdx.files.internal("Grass.png")), // 0 -> Plaine
            new Texture(Gdx.files.internal("Sand.png")), // 1 -> Desert
            new Texture(Gdx.files.internal("Dirt.png")), // 2 -> Foret
            new Texture(Gdx.files.internal("Mountain.png")), // 3 -> Montagne
            new Texture(Gdx.files.internal("Grass1.png")), // 4 -> Colline
            new Texture(Gdx.files.internal("Water.png")) // 5 -> Mer
        };

        this.skin = new Skin(Gdx.files.internal("skin.json"));

        this.menuButtons = new Actor[] {
            new TextButton("Bouton 1", this.skin, "default"),
            new TextButton("Bouton 2", this.skin, "default"),
            new TextButton("Bouton 3", this.skin, "default"),
            new TextButton("Bouton 4", this.skin, "default"),
            new TextButton("Bouton 5", this.skin, "default")
        };

        // TODO : Créer un package séparé pour les listeners -> Faire un tableau de listeners
        // TODO : Changer les actions des différents boutons
        this.menuButtons[0].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                System.out.println("Button 1 clicked !");
            }
        });

        // TODO : Changer les actions des différents boutons
        this.menuButtons[1].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                System.out.println("Button 2 clicked !");
            }
        });

        // TODO : Changer les actions des différents boutons
        this.menuButtons[2].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                System.out.println("Button 3 clicked !");
            }
        });

        // TODO : Changer les actions des différents boutons
        this.menuButtons[3].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                System.out.println("Button 4 clicked !");
            }
        });

        // TODO : Changer les actions des différents boutons
        this.menuButtons[4].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                System.out.println("Button 5 clicked !");
            }
        });

        this.coordinates = new Label("[x, y]", this.skin, "default");
        this.coordinates.setAlignment(Align.center);

        this.tileMenuButtons = new Actor[] {
            this.coordinates,
            new TextButton("Action 1", this.skin, "default"),
            new TextButton("Action 2", this.skin, "default")
        };

        // TODO : Créer un package séparé pour les listeners -> Faire un tableau de listeners
        // TODO : Changer les actions des différents boutons
        this.tileMenuButtons[1].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                System.out.print("Action 1 clicked, current case is : ");
                System.out.println("[" + (int) getClickCoordinates().x + ", " + (int) getClickCoordinates().y + "]");
                map.at((int) getClickCoordinates().x, (int) getClickCoordinates().y).setLand(LandType.MONTAGNE);
                tiledMap = loadMap(map);
                tiledMapRenderer.setMap(tiledMap);
            }
        });

        // TODO : Changer les actions des différents boutons
        this.tileMenuButtons[2].addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                System.out.print("Action 2 clicked, current case is : ");
                System.out.println("[" + (int) getClickCoordinates().x + ", " + (int) getClickCoordinates().y + "]");
                map.at((int) getClickCoordinates().x, (int) getClickCoordinates().y).setLand(LandType.MER);
                tiledMap = loadMap(map);
                tiledMapRenderer.setMap(tiledMap);
            }
        });

        int padding = 10;

        Pixmap background = new Pixmap(1,1,Pixmap.Format.LuminanceAlpha);
        background.setColor(new Color(1, 1, 1, 0.5f));
        background.fill();
        this.backgroundTexture = new TextureRegionDrawable(new TextureRegion(new Texture(background)));

        // Menu bar
        this.menuBar = new Table();
        this.menuBar.setBackground(this.backgroundTexture);
        this.menuBar.setBounds(
            0,
            Gdx.graphics.getHeight() - this.menuButtons[0].getHeight() - 2 * padding,
            Gdx.graphics.getWidth(),
            this.menuButtons[0].getHeight() + 2 * padding
        );
        this.menuBar.defaults().padRight(0).padTop(padding);
        for(Actor button : this.menuButtons) {
            this.menuBar.add(button).padLeft(padding).padTop(padding).width(188);
        }
        this.menuBar.left().top();

        //this.menuBar.setDebug(true);

        // Tile menu
        this.tileMenu = new Table();
        this.tileMenu.setBackground(this.backgroundTexture);
        this.tileMenu.setBounds(
            Gdx.graphics.getWidth() - 160 - padding,
            0,
            160 + 3*padding,
            3 * (this.tileMenuButtons[0].getHeight() + 2*padding)
        );
        this.tileMenu.defaults().padTop(0).padRight(2*padding).fillX().center();
        this.tileMenu.row();
        this.tileMenu.add(this.tileMenuButtons[0]).padTop(padding);
        for(Actor button : Arrays.copyOfRange(this.tileMenuButtons, 1, this.tileMenuButtons.length)) {
            this.tileMenu.row();
            this.tileMenu.add(button).padTop(2*padding).width(160);
        }
        this.tileMenu.right().top();
        this.tileMenu.setVisible(false);

        //this.tileMenu.setDebug(true);

        this.mapStage = new Stage(new ScreenViewport());
        this.menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        this.camera = (OrthographicCamera) this.mapStage.getViewport().getCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.update();

        this.tiledMap = loadMap(this.map);
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);

        this.inputMultiplexer.addProcessor(this.menuStage);
        this.inputMultiplexer.addProcessor(this.controller);

        Gdx.input.setInputProcessor(this.inputMultiplexer);
    }

    @Override public void render(float t) {
        this.camera.zoom *= this.controller.getZoom();
        this.camera.zoom = Math.min(
            this.camera.zoom,
            Math.min(
                map.getWidth() * this.tileSize / Gdx.graphics.getWidth(),
                map.getHeight() * this.tileSize / Gdx.graphics.getHeight()
            )
        );
        this.camera.zoom = Math.max(
            this.camera.zoom,
            0.1f
        );
        this.camera.update();

        this.camera.position.x += this.camera.zoom * this.moveSpeed * this.controller.getMovement().x;
        this.camera.position.y += this.camera.zoom * this.moveSpeed * this.controller.getMovement().y;

        this.camera.position.x = Math.max(this.camera.position.x, this.camera.zoom * Gdx.graphics.getWidth() / 2);
        this.camera.position.x = Math.min(this.camera.position.x, this.camera.zoom * (map.getWidth() * this.tileSize - Gdx.graphics.getWidth() / 2));
        this.camera.position.y = Math.max(this.camera.position.y, this.camera.zoom * Gdx.graphics.getHeight() / 2);
        this.camera.position.y = Math.min(this.camera.position.y, this.camera.zoom * (map.getHeight() * this.tileSize - Gdx.graphics.getHeight() / 2));

        this.coordinates.setText(
            "["
            + (int) (getClickCoordinates().x)
            + ", "
            + (int) (getClickCoordinates().y)
            + "]"
        );

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render Map
        this.tiledMapRenderer.setView(this.camera);
        this.tiledMapRenderer.render();

        this.mapStage.act(Gdx.graphics.getDeltaTime());
        this.mapStage.getViewport().apply();
        this.mapStage.draw();

        // Render Menu
        this.menuStage.addActor(this.menuBar);
        if (this.controller.getDisplayTileMenu()) {
            this.tileMenu.setVisible(true);
        } else {
            this.tileMenu.setVisible(false);
        }
        this.menuStage.addActor(this.tileMenu);

        this.menuStage.act(Gdx.graphics.getDeltaTime());
        this.menuStage.getViewport().apply();
        this.menuStage.draw();
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
                return Integer.parseInt(parts[1]);
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
        TiledMapTileLayer tiledMapLayer = new TiledMapTileLayer(map.getWidth(), map.getHeight(), this.tileSize, this.tileSize);
        tiledMap.getLayers().add(tiledMapLayer);
        
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
                tiledMapLayer.setCell(j, i, cell);
            }
        }
        return tiledMap;
    }

    /**
     * Renvoie les coordonnées de la tuile cliquée par l'utilisateur.
     * @return les coordonnées de la tuile cliquée
     */
    private Vector2 getClickCoordinates() {
        return new Vector2(
            (int) (
                (this.controller.getClickCoordinates().x * this.camera.zoom +
                this.camera.position.x - (this.camera.zoom * Gdx.graphics.getWidth() / 2)
                ) / this.tileSize),
            (int) (
                (this.camera.position.y + (this.camera.zoom * Gdx.graphics.getHeight() / 2) -
                this.controller.getClickCoordinates().y * this.camera.zoom
                ) / this.tileSize)
        );
    }

    /**
     * Met à jour la carte.
     */
    private void updateMap() {
        this.tiledMap = loadMap(this.map);
        this.tiledMapRenderer.setMap(this.tiledMap);
    }
}
