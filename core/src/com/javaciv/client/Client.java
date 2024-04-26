package com.javaciv.client;

import com.javaciv.Actor;
import com.javaciv.server.WorldMap;
import com.badlogic.gdx.math.Vector2;

public class Client implements Actor {
    private Actor server;

    public Client(Actor server) {
        this.server = server;
    }

    public WorldMap getWorldMap() {
        return this.server.getWorldMap();
    }
}
