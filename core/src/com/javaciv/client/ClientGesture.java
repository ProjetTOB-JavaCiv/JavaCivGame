package com.javaciv.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;

public class ClientGesture extends GestureDetector.GestureAdapter {
    /**
     * The camera
     */
    private OrthographicCamera camera;

    /**
     * The controller
     */
    private ClientController controller;

    /**
     * The client view
     */
    private ClientView clientView;

    /**
     * This is the constructor of the ClientGesture class.
     * @param camera the camera
     * @param controller the controller
     * @param clientView the client view
     */
    public ClientGesture(Camera camera, ClientController controller, ClientView clientView) {
        this.camera = (OrthographicCamera) camera;
        this.controller = controller;
        this.clientView = clientView;
    }

    /**
     * This function is executed when the user taps the screen.
     * @param x the x coordinate of the tap
     * @param y the y coordinate of the tap
     * @param count the number of taps
     * @param button the button that was tapped
     * @return true if the input was processed, false otherwise
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (button == Input.Buttons.LEFT) {
            clientView.Leftclicked(x, y);
        }
        return false;
    }

    /**
     * This function is executed when the user zooms the screen.
     * @param initialDistance the initial distance between the two fingers
     * @param distance the distance between the two fingers
     * @return true if the input was processed, false otherwise
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            camera.translate(-deltaX * camera.zoom, deltaY * camera.zoom);
            camera.update();
        }
        return false;
    }
}