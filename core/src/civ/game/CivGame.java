package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import screen.EcranJeu;


public class CivGame extends Game {

    public Stage stage; //j'ai foutu public en deuspi mais est ce que on fait on va pas plutot faire un getter ?
    SpriteBatch character_batch;
    public BitmapFont font;

    public void create() {

        stage = new Stage();
        font = new BitmapFont(); // use libGDX's default Arial font
        this.setScreen(new EcranJeu(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        stage.dispose();
        font.dispose();
    }

}
