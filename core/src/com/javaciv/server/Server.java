package com.javaciv.server;

import com.javaciv.Actor;

public class Server implements Actor {
    private WorldMap worldMap;

    public Server() {
        this.worldMap = new WorldMap(300, 300);
    }

    public WorldMap getWorldMap() {
        return this.worldMap;
    }
}
