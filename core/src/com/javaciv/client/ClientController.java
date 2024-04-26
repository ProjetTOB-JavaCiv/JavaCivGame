package com.javaciv.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class ClientController implements InputProcessor {

    private Client client;

    private Vector2 movement = new Vector2(0, 0);

    private float zoom = 0;

    public ClientController(Client client) {
        this.client = client;
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                this.move(new Vector2(0, 1));
                break;
            case Input.Keys.DOWN:
                this.move(new Vector2(0, -1));
                break;
            case Input.Keys.LEFT:
                this.move(new Vector2(-1, 0));
                break;
            case Input.Keys.RIGHT:
                this.move(new Vector2(1, 0));
                break;
            /*case Input.Keys.Q:
                this.zoom = 0.01f;
                break;
            case Input.Keys.W:
                this.zoom = -0.01f;
                break;*/
            default:
                System.out.println(keycode + " pressed");
                break;
        }
        return false;
    }

    public boolean keyUp (int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                this.stop(new Vector2(0, 1));
                break;
            case Input.Keys.DOWN:
                this.stop(new Vector2(0, -1));
                break;
            case Input.Keys.LEFT:
                this.stop(new Vector2(-1, 0));
                break;
            case Input.Keys.RIGHT:
                this.stop(new Vector2(1, 0));
                break;
            /*case Input.Keys.Q:
                this.zoom = 0.0f;
                break;
            case Input.Keys.W:
                this.zoom = 0.0f;
                break;*/
            default:
                System.out.println(keycode + " released");
                break;
        }
        return false;
    }

    public float getZoom() {
        return this.zoom;
    }

    public Vector2 getMovement() {
        return this.movement;
    }

    public void move(Vector2 movement) {
        Vector2 newMovement = new Vector2(0, 0);

        newMovement.x = this.getMovement().x + movement.x;
        newMovement.y = this.getMovement().y + movement.y;

        this.movement = newMovement.nor();
    }

    public void stop(Vector2 movement) {
        Vector2 newMovement = new Vector2(0, 0);

        newMovement.x = this.getMovement().x - movement.x * Math.abs(this.getMovement().x);
        newMovement.y = this.getMovement().y - movement.y * Math.abs(this.getMovement().y);

        this.movement = newMovement.nor();
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        System.out.println("Touch down at (" + x + ", " + y + ")");
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchCancelled (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    private float lastZoom;

    public boolean scrolled (float amountX, float amountY) {
        if (amountY > 0.7 && amountY < 14) {
            this.zoom = 1.02f;
        } else if (amountY < -0.7 && amountY > -14) {
            this.zoom = 0.98f;
        } else {
            this.zoom = 1f;
        }
        return false;
    }

}
