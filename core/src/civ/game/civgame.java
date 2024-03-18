package civ.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class civgame implements ApplicationListener {
	SpriteBatch tile_batch;
	SpriteBatch character_batch;
	Texture grass_img;
	Texture dirt_img;
	Texture character_img;
	Music maxwellcat;
	Camera camera;
	Matrix4 projection = new Matrix4(
		new Vector3(1, 1, 0),
		new Quaternion(0, 0, 0, 0),
		new Vector3(1, 1, 1)
	);

	int row = 10000;
	int col = 10000;
	int bi = 0;
	int bj = 0;
	int posx = 0;
	int posy = 0;
	int[][] map = new int [row][col];
	/*int[][] map = {
		{0,1,0,1,0,1,0,1,0,1,0,1},
		{1,0,0,1,0,0,1,0,0,1,0,0},
		{0,1,0,0,0,1,0,0,0,1,0,0},
		{1,0,0,0,0,1,0,0,0,0,1,0},
		{0,1,0,0,0,0,0,1,0,0,0,0},
		{1,0,0,0,0,0,0,1,0,0,0,0},
		{0,1,0,0,0,0,0,0,1,0,0,0},
		{1,0,0,0,0,0,0,0,1,0,0,0},
		{0,1,0,0,0,0,0,0,0,1,0,0},
		{1,0,0,0,0,0,0,0,0,1,0,0}
	};*/

	int render_distance = 40;
	int sprite_size = 5;
	int move_speed = 20000;
	
	@Override
	public void create () {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = (int)(Math.random()*2);
			}
		}

		tile_batch = new SpriteBatch();
		character_batch = new SpriteBatch();
		grass_img = new Texture("grass.jpg");
		dirt_img = new Texture("dirt.jpg");
		
		maxwellcat = Gdx.audio.newMusic(Gdx.files.internal("maxwell.mp3"));
		maxwellcat.setLooping(true);
		maxwellcat.play();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tile_batch.setProjectionMatrix(camera.combined);
		tile_batch.setTransformMatrix(projection);

		tile_batch.begin();

		int k = 0;
		int imin = (int)Math.max((camera.position.y/sprite_size) - render_distance, 0);
		int imax = (int)Math.min((camera.position.y/sprite_size) + render_distance, map.length);
		int jmin = (int)Math.max((camera.position.x/sprite_size) - render_distance, 0);
		int jmax = (int)Math.min((camera.position.x/sprite_size) + render_distance, map[0].length);
		//for(int i = 0; i < map.length; i++) {
		//	for(int j = 0; j < map[0].length; j++) {
		for(int i = imin; i < imax; i++) {
			for(int j = jmin; j < jmax; j++) {
				// TODO : use HASH_MAP
				k = map[0].length-(j+1);
				//k = jmax-(j+1);
				if (map[i][k] == 0) {
					tile_batch.draw(grass_img, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
				} else if (map[i][k] == 1) {
					tile_batch.draw(dirt_img, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
				}
			}
		}
		tile_batch.end();
		
		System.out.println("Cursor [" + (int)(Gdx.input.getX()/sprite_size) + ", " + (int)((Gdx.graphics.getHeight()-Gdx.input.getY())/sprite_size) + "]");
		System.out.println("(bi : " + bi + ", bj : " + bj + ")");
		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
			int i = bi + (int)((Gdx.graphics.getHeight()-Gdx.input.getY())/sprite_size);
			int j = bj + (int)(Gdx.input.getX()/sprite_size);
			int l = map[0].length-(j+1);
			map[i][l] = 1-map[i][l];
		}

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
	
	@Override
	public void dispose () {
		grass_img.dispose();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}
}
