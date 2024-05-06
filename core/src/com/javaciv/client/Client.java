package com.javaciv.client;

import com.javaciv.GameInterface;
import com.javaciv.gameElement.map.WorldMap;

public class Client implements GameInterface {
    private GameInterface server;

    public Client(GameInterface server) {
        this.server = server;
    }

    public WorldMap getWorldMap() {
        return this.server.getWorldMap();
    }
}
