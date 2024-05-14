/**
 * @file Server.java
 * @brief This file contains the Server class.
 * @author Théo Bessel
 * @date 18/04/2024
 * @version 1.0
 */

package com.javaciv.server;

import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.GameInterface;

public class Server implements GameInterface {
    private WorldMap worldMap;

    public Server() {
        this.worldMap = new WorldMap(100, 200);
    }

    public WorldMap getWorldMap() {
        return this.worldMap;
    }
}
