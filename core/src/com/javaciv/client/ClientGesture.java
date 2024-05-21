package com.javaciv.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;

public class ClientGesture extends GestureDetector.GestureAdapter {
    private OrthographicCamera camera;
    private ClientController controller;
    private ClientView clientView;

    public ClientGesture(Camera camera, ClientController controller, ClientView clientView) {
        this.camera = (OrthographicCamera) camera;
        this.controller = controller;
        this.clientView = clientView;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (button == Input.Buttons.LEFT) {
            clientView.Leftclicked(x, y);
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            camera.translate(-deltaX * controller.getZoom(), deltaY * controller.getZoom());
            camera.update();
        }
        return false;
    }
}