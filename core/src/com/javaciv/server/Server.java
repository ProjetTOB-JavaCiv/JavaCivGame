package com.javaciv.server;

import com.javaciv.GameInterface;

public class Server implements GameInterface {
    private WorldMap worldMap;

    public Server() {
        this.worldMap = new WorldMap(100, 100);
    }

    public WorldMap getWorldMap() {
        return this.worldMap;
    }
}
