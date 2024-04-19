package civ.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EcranJeu implements Screen {
    final CivGame jeu;
    Stage stage;
    Texture grass_img;
    Texture desert_img;
    Texture dirt_img;
    Texture lac_img;
    Texture montagne_img;
    Texture prairie_img;
    Texture plaine_img;
    Texture ocean_img;
    Texture toundra_img;

    boolean MenuTuileOuvert = false;
    private Button caca, pipi, prout, caca2;
    private Window menu_tuile;
    Texture character_img;
    Sound fart1, fart2, fart3, fart4;
    Music maxwellcat;
    Camera camera;
    Matrix4 projection = new Matrix4(
            new Vector3(1, 1, 0),
            new Quaternion(0, 0, 0, 0),
            new Vector3(1, 1, 1)
    );
    
    int row = 300;
    int col = 300;
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

    public EcranJeu (final CivGame jeu) {
        this.jeu = jeu;
        stage = new Stage();

        Map mapList = Map.creerMapAleatoire(row, col);
        map = mapList.getTableau();
        
        grass_img = new Texture("grass.jpg");
        dirt_img = new Texture("dirt.jpg");
        desert_img = new Texture("desert.jpg");
        lac_img = new Texture("lac.jpg");
        montagne_img = new Texture("marron.jpg");
        prairie_img = new Texture("prairie.jpg");
        plaine_img = new Texture("plaine.jpg");
        ocean_img = new Texture("ocean.jpg");
        toundra_img = new Texture("toundra.jpg");
        creer_menu_tuiles();
        fart1 = Gdx.audio.newSound(Gdx.files.internal("fart.wav"));
        fart2 = Gdx.audio.newSound(Gdx.files.internal("fart2.mp3"));
        fart3 = Gdx.audio.newSound(Gdx.files.internal("fart3.mp3"));
        fart4 = Gdx.audio.newSound(Gdx.files.internal("fart4.mp3"));


        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
    }

    @Override
    public void render (float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        jeu.stage.getBatch().setProjectionMatrix(camera.combined);
        jeu.stage.getBatch().setTransformMatrix(projection);
        Gdx.input.setInputProcessor(stage);
        jeu.stage.getBatch().begin();
        stage.act();


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
                    jeu.stage.getBatch().draw(desert_img, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
                } else if (map[i][k] == 1) {
                    jeu.stage.getBatch().draw(lac_img, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
                } else if (map[i][k] == 2) {
                    jeu.stage.getBatch().draw(montagne_img, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
                } else if (map[i][k] == 3) {
                    jeu.stage.getBatch().draw(ocean_img, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
                } else if (map[i][k] == 4) {
                    jeu.stage.getBatch().draw(plaine_img, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
                } else if (map[i][k] == 5) {
                    jeu.stage.getBatch().draw(prairie_img, sprite_size*j, sprite_size*i, sprite_size, sprite_size);
                } else if (map[i][k] == 6) {
                    jeu.stage.getBatch().draw(toundra_img, sprite_size * j, sprite_size * i, sprite_size, sprite_size);
                }
            }
        }
        stage.draw();

        jeu.stage.getBatch().end();

        System.out.println("Cursor [" + (int)(Gdx.input.getX()/sprite_size) + ", " + (int)((Gdx.graphics.getHeight()-Gdx.input.getY())/sprite_size) + "]");
        System.out.println("(bi : " + bi + ", bj : " + bj + ")");

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !MenuTuileOuvert){
            afficher_menu_tuiles();
        }
        caca.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fart1.play();
            }
        });
        pipi.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fart2.play();
            }
        });
        prout.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fart3.play();
            }
        });
        caca2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                fart4.play();
            }
        });
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){

            caca.setVisible(false);
            pipi.setVisible(false);
            prout.setVisible(false);
            caca2.setVisible(false);
            MenuTuileOuvert = false;
            //map[i][l] = 1-map[i][l];

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
    public void creer_menu_tuiles() {
        float echelle = 0.2f;
        Skin mySkin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        menu_tuile = new Window("menu_tuile", mySkin);
        caca = new TextButton("caca", mySkin, "default");
        
        caca.setTransform(true);
        caca.setScale(echelle);
        pipi = new TextButton("pipi", mySkin, "default");
        pipi.setTransform(true);
        pipi.setScale(echelle);
        prout = new TextButton("prout", mySkin, "default");
        prout.setTransform(true);
        prout.setScale(echelle);
        caca2 = new TextButton("caca2", mySkin, "default");
        caca2.setTransform(true);
        caca2.setScale(echelle);
        caca.setVisible(false);
        pipi.setVisible(false);
        prout.setVisible(false);
        caca2.setVisible(false);
        stage.addActor(caca);
        stage.addActor(pipi);
        stage.addActor(prout);
        stage.addActor(caca2);


    }

    public void afficher_menu_tuiles() {
        float echelle = 0.2f;
        int x = Gdx.input.getX();
        int y = (int)(Gdx.graphics.getHeight()-Gdx.input.getY());
        int i = bi + (int)((Gdx.graphics.getHeight()-Gdx.input.getY())/sprite_size);
        int j = bj + (int)(Gdx.input.getX()/sprite_size);
        int l = map[0].length-(j+1);
        caca.setPosition(x,y);
        pipi.setPosition(x,y-caca.getHeight()*echelle) ;
        prout.setPosition(x,y-2*caca.getHeight()*echelle);
        caca2.setPosition(x,y - 3*caca.getHeight()*echelle);
        caca.setVisible(true);
        pipi.setVisible(true);
        prout.setVisible(true);
        caca2.setVisible(true);
        MenuTuileOuvert = true;
    }
    @Override
    public void dispose () {
        grass_img.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void show() {

        maxwellcat = Gdx.audio.newMusic(Gdx.files.internal("maxwell.mp3"));
        maxwellcat.setLooping(true);
        maxwellcat.play();
    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
