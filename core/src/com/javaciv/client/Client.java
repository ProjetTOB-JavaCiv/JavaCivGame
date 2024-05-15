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

    int clientId = -1;

    public Client(GameInterface server) {
        this.server = server;
        this.clientId = createClient(this);
    }

    public WorldMap getWorldMap() {
        return this.server.getWorldMap();
    }

    public int getClientId() {
        return this.clientId;
    }

    public int getGoldPoint() {
        return this.server.getGoldPoint();
    }

    public int getCulturePoint() {
        return this.server.getCulturePoint();
    }

    public int getSciencePoint() {
        return this.server.getSciencePoint();
    }

    public int getFaithPoint() {
        return this.server.getFaithPoint();
    }

    public void nextTurn() {
        if (this.canPassTurn()) {
            this.server.nextTurn();
        }
    }

    public boolean canPassTurn() {
        return this.getClientId() == this.server.getClientId();
    }

    public int createClient(GameInterface client) {
        if (this.clientId == -1) {
            return this.server.createClient(this);
        } else {
            return this.clientId;
        }
    }
}
