package civ.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class CivGame extends Game {

    Stage stage;
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
