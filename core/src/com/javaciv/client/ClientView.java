package com.javaciv.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.javaciv.server.WorldMap;

public class ClientView implements Screen {
    private ClientController controller;

    /**
     * LibGDX objects
     */
    private Stage mapStage;
    private Stage menuStage;
    private OrthographicCamera camera;

    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

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

    private TextButton[] menuButtons;

    public ClientView(ClientController controller, WorldMap map) {
        loadConfiguration("config.txt");

        this.controller = controller;
        this.map = map;

        this.tileTextures = new Texture[] {
            new Texture(Gdx.files.internal("Grass.png")),
            new Texture(Gdx.files.internal("Grass1.png")),
            new Texture(Gdx.files.internal("Dirt.png"))
        };

        this.skin = new Skin(Gdx.files.internal("skin.json"));



        this.menuButtons = new TextButton[] {
            new TextButton("1", this.skin, "default"),
            new TextButton("2", this.skin, "default"),
            new TextButton("3", this.skin, "default"),
            new TextButton("4", this.skin, "default"),
            new TextButton("5", this.skin, "default")
        };

        int padding = 10;

        this.menuBar = new Table();
        this.menuBar.setBounds(
            0,
            Gdx.graphics.getHeight() - this.menuButtons[0].getHeight() - 2 * padding,
            Gdx.graphics.getWidth(),
            this.menuButtons[0].getHeight() + 2 * padding
        );
        this.menuBar.defaults().padRight(0);

        for(TextButton button : this.menuButtons) {
            this.menuBar.add(button).padLeft(padding).padTop(padding).width(188);
        }

        this.menuBar.left().top();

        //this.menuBar.setDebug(true);

        this.mapStage = new Stage(new ScreenViewport());
        this.menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        //this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //this.camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        //this.camera = new OrthographicCamera();
        this.camera = (OrthographicCamera) this.mapStage.getViewport().getCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.update();

        this.tiledMap = loadMap(this.map);
        this.tiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);

        Gdx.input.setInputProcessor(this.controller);
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
     * Parse a configuration from a key and a file, example :
     * renderDistance=40
     * tileSize=5
     * moveSpeed=2000
     * @param key the key to search for
     * @return the value of the key
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

    private void loadConfiguration(String path) {
        this.renderDistance = parseConfiguration(Gdx.files.internal(path), "renderDistance");
        this.tileSize = parseConfiguration(Gdx.files.internal(path), "tileSize");
        this.moveSpeed = parseConfiguration(Gdx.files.internal(path), "moveSpeed");
    }

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
                            this.tileTextures[map.at(i, j).getTerrain().toInt()], 0, 0, this.tileSize, this.tileSize
                        )
                    )
                );
                tiledMapLayer.setCell(j, i, cell);
            }
        }
        return tiledMap;
    }
}
