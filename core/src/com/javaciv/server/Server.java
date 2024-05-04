package com.javaciv.server;

import com.javaciv.Actor;
import com.javaciv.gameElement.map.WorldMap;

public class Server implements Actor {
    private WorldMap worldMap;

    public Server() {
        this.worldMap = new WorldMap(100, 100);
    }

    public WorldMap getWorldMap() {
        return this.worldMap;
    }
}
