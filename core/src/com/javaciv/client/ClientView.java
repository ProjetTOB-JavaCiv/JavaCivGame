package com.javaciv.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.javaciv.events.KeyPressedEvent;
import com.javaciv.server.WorldMap;

public class ClientView implements Screen, ClientListener {
    private ClientController controller;

    /**
     * LibGDX objects
     */
    private Camera camera;
    private Matrix4 projection = new Matrix4(
        new Vector3(1,1,0),
        new Quaternion(0,0,0,0),
        new Vector3(1,1,1)
    );
    private SpriteBatch tileBatch;

    /**
     * Game objects
     */
    private WorldMap map;
    private Texture[] tileTextures;

    private int renderDistance;
    private int zoom;
    private int moveSpeed;

    public ClientView(ClientController controller, WorldMap map, SpriteBatch tileBatch, Texture[] tileTextures, int renderDistance, int zoom, int moveSpeed) {
        loadConfiguration("config.txt");

        this.tileTextures = new Texture[] {
            new Texture(Gdx.files.internal("tile.png")),
            new Texture(Gdx.files.internal("tile2.png")),
            new Texture(Gdx.files.internal("tile2.png"))
        };

        this.controller = controller;

        this.map = map;

        this.tileBatch = tileBatch;

        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    }

    @Override public void render(float t) {
        int k = 0;
        int iMin = (int)Math.max((camera.position.y/zoom) - renderDistance, 0);
        int iMax = (int)Math.min((camera.position.y/zoom) + renderDistance, map.getHeight());
        int jMin = (int)Math.max((camera.position.x/zoom) - renderDistance, 0);
        int jMax = (int)Math.min((camera.position.x/zoom) + renderDistance, map.getWidth());

        tileBatch.setProjectionMatrix(camera.combined);
        tileBatch.setTransformMatrix(projection);
        tileBatch.begin();
        for(int i = iMin; i < iMax; i++) {
            for(int j = jMin; j < jMax; j++) {
                k = map.getWidth()-(j+1);
                tileBatch.draw(this.tileTextures[map.at(i, k).getTerrain().toInt()], zoom*j, zoom*i, zoom, zoom);
            }
        }
        tileBatch.end();
    }

    @Override public void resize(int w, int h) {}

    @Override public void pause() {}

    @Override public void resume() {}

    @Override public void hide() {}

    @Override public void show() {}

    @Override public void dispose() {}

    @Override public void keyPressed(KeyPressedEvent e) {}

    /**
     * Parse a configuration from a key and a file, example :
     * renderDistance=40
     * zoom=5
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
        this.zoom = parseConfiguration(Gdx.files.internal(path), "zoom");
        this.moveSpeed = parseConfiguration(Gdx.files.internal(path), "moveSpeed");
    }
}
