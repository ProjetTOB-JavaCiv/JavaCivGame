/**
 * @file ClientController.java
 * @brief This file contains the ClientController class.
 * @author Théo Bessel
 * @date 20/04/2024
 * @version 1.0
 */

package com.javaciv.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;

public class ClientController implements InputProcessor {

    private ClientView clientView;

    public ClientController(ClientView clientView) {
        this.clientView = clientView;
    }

    private Client client;

    private Vector2 movement = new Vector2(0, 0);

    private float zoom = 1.0f;

    private boolean displayTileMenu = true;

    private Vector2 coordinates = new Vector2(0, 0);


    private void move(Vector2 movement) {
        Vector2 newMovement = new Vector2(0, 0);

        newMovement.x = this.getMovement().x + movement.x;
        newMovement.y = this.getMovement().y + movement.y;

        this.movement = newMovement.nor();
    }

    private void stop(Vector2 movement) {
        Vector2 newMovement = new Vector2(0, 0);

        newMovement.x = this.getMovement().x - movement.x * Math.abs(this.getMovement().x);
        newMovement.y = this.getMovement().y - movement.y * Math.abs(this.getMovement().y);

        this.movement = newMovement.nor();
    }

    public ClientController(Client client) {
        this.client = client;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public float getZoom() {
        return this.zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public Vector2 getMovement() {
        return this.movement;
    }

    public boolean getDisplayTileMenu() {
        return this.displayTileMenu;
    }

    public Vector2 getClickCoordinates() {
        return this.coordinates;
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

    public boolean keyTyped (char character) {
        return false;
    }

    public void setDisplayTileMenu(boolean displayTileMenu) {
        this.displayTileMenu = displayTileMenu;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        if (button == Buttons.RIGHT){
            System.out.println(this.clientView);
            System.out.println("Coucou");
            System.out.println(x + "," + y );
            this.coordinates = new Vector2(x, y);
            System.out.println(this.coordinates);

            Vector2 clickCoords = clientView.getClickCoordinates();
            
            clientView.openTileMenuAt(clickCoords);
            
            
        }
        System.out.println("Touch down at (" + x + ", " + y + ")");
        displayTileMenu = true;
        this.coordinates = new Vector2(x, y);
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

    public boolean scrolled (float amountX, float amountY) {
        if (amountY > 0.7 && amountY < 14) {
            this.zoom = 1.04f;
        } else if (amountY < -0.7 && amountY > -14) {
            this.zoom = 0.96f;
        } else {
            this.zoom = 1.0f;
        }
        return false;
    }

}
