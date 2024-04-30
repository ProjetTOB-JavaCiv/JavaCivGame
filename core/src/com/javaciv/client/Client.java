package com.javaciv.client;

import com.javaciv.GameInterface;
import com.javaciv.server.WorldMap;
import com.badlogic.gdx.math.Vector2;

public class Client implements GameInterface {
    private GameInterface server;

    public Client(GameInterface server) {
        this.server = server;
    }

    public WorldMap getWorldMap() {
        return this.server.getWorldMap();
    }
}
