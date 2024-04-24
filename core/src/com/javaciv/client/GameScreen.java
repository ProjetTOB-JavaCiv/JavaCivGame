package com.javaciv.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.javaciv.JavaCiv;
import com.javaciv.server.WorldMap;

public class GameScreen implements Screen {

    final JavaCiv game;
    Texture tileTexture;
    Texture tile2Texture;
    Camera camera;
    Matrix4 projection = new Matrix4(
            new Vector3(1, 1, 0),
            new Quaternion(0, 0, 0, 0),
            new Vector3(1, 1, 1)
    );
    WorldMap map;

    SpriteBatch tile_batch;

    public GameScreen(JavaCiv game) {
        this.game = game;
        this.tile_batch = new SpriteBatch();
        this.map = game.getClient().getWorldMap();
        this.tileTexture = new Texture(Gdx.files.internal("tile.png"));
        this.tile2Texture = new Texture(Gdx.files.internal("tile2.png"));
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
    }

    int render_distance = 40;
    int sprite_size = 5;
    int move_speed = 20000;
    int row = 300;
    int col = 300;
    int bi = 0;
    int bj = 0;
    int posx = 0;
    int posy = 0;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tile_batch.setProjectionMatrix(camera.combined);
        tile_batch.setTransformMatrix(projection);
        tile_batch.begin();

        int k = 0;
        int imin = (int)Math.max((camera.position.y/sprite_size) - render_distance, 0);
        int imax = (int)Math.min((camera.position.y/sprite_size) + render_distance, map.getHeight());
        int jmin = (int)Math.max((camera.position.x/sprite_size) - render_distance, 0);
        int jmax = (int)Math.min((camera.position.x/sprite_size) + render_distance, map.getWidth());
        //for(int i = 0; i < map.length; i++) {
        //	for(int j = 0; j < map[0].length; j++) {
        for(int i = imin; i < imax; i++) {
            for(int j = jmin; j < jmax; j++) {
                // TODO : use HASH_MAP
                k = map.getWidth()-(j+1);
                //k = jmax-(j+1);
                if (map.at(i, k).getTerrain().toInt() == 0) {
                    tile_batch.draw(tileTexture, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
                } else {
                    tile_batch.draw(tile2Texture, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
                }
            }
        }

        tile_batch.end();

        System.out.println("Camera position: " + camera.position);
        System.out.println("Cursor [" + (int)(Gdx.input.getX()/sprite_size) + ", " + (int)((Gdx.graphics.getHeight()-Gdx.input.getY())/sprite_size) + "]");
        System.out.println("(bi : " + bi + ", bj : " + bj + ")");

        if (
                Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                        Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                        Gdx.input.isKeyPressed(Input.Keys.UP) ||
                        Gdx.input.isKeyPressed(Input.Keys.DOWN)
        ) {
            //System.out.println("Camera [" + (int)camera.position.x + ", " + (int)camera.position.y + "]");
            //System.out.println("Window [" + imin + "-" + imax + ";" + jmin + "-" + jmax + "]");
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                posx -= move_speed*Gdx.graphics.getDeltaTime();
                if (posx < -sprite_size) {
                    camera.position.x -= sprite_size;
                    bj--;
                    posx = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                posx += move_speed*Gdx.graphics.getDeltaTime();
                if (posx > sprite_size) {
                    camera.position.x += sprite_size;
                    bj++;
                    posx = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                posy += move_speed*Gdx.graphics.getDeltaTime();
                if (posy > sprite_size) {
                    camera.position.y += sprite_size;
                    bi++;
                    posy = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                posy -= move_speed*Gdx.graphics.getDeltaTime();
                if (posy < -sprite_size) {
                    camera.position.y -= sprite_size;
                    bi--;
                    posy = 0;
                }
            }
        }

        camera.update();
    }

    @Override public void resize(int w, int h) {}

    @Override public void pause() {}

    @Override public void resume() {}

    @Override public void hide() {}

    @Override public void show() {}

    @Override public void dispose() {
        this.tileTexture.dispose();
    }
}
