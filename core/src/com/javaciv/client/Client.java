package com.javaciv.client;

import com.javaciv.Actor;
import com.javaciv.server.WorldMap;

public class Client implements Actor {
    private Actor server;

    public Client(Actor server) {
        this.server = server;
    }

    public WorldMap getWorldMap() {
        return this.server.getWorldMap();
    }
}
