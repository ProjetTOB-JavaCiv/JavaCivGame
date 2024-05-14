/**
 * @file Client.java
 * @brief This file contains the Client class.
 * @author Théo Bessel
 * @date 18/04/2024
 * @version 1.0
 */

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
