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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Server implements GameInterface {
    private WorldMap worldMap;

    private HashMap<Integer, GameInterface> clients;

    private int clientId;

    private List<Integer> goldPoint;

    private List<Integer> culturePoint;

    private List<Integer> sciencePoint;

    private List<Integer> faithPoint;

    public Server() {
        this.worldMap = new WorldMap(100, 200);
        this.clients = new HashMap<Integer, GameInterface>();
        this.clientId = 0;
        this.goldPoint = new ArrayList<Integer>();
        this.culturePoint = new ArrayList<Integer>();
        this.sciencePoint = new ArrayList<Integer>();
        this.faithPoint = new ArrayList<Integer>();
    }

    public WorldMap getWorldMap() {
        return this.worldMap;
    }

    public int getClientId() {
        return this.clientId;
    }

    public int getGoldPoint() {
        return this.goldPoint.get(this.clientId);
    }

    public int getCulturePoint() {
        return this.culturePoint.get(this.clientId);
    }

    public int getSciencePoint() {
        return this.sciencePoint.get(this.clientId);
    }

    public int getFaithPoint() {
        return this.faithPoint.get(this.clientId);
    }

    public void nextTurn() {
        // TODO : implement the productions amounts to replace hardcoded amount for each player
        this.goldPoint.set(this.clientId, this.goldPoint.get(this.clientId) + 10);
        this.culturePoint.set(this.clientId, this.culturePoint.get(this.clientId) + 2);
        this.sciencePoint.set(this.clientId, this.sciencePoint.get(this.clientId) + 4);
        this.faithPoint.set(this.clientId, this.faithPoint.get(this.clientId) + 12);
        this.clientId = (this.clientId + 1) % getClientCount();
        System.out.println("Next turn, clientId is : " + this.getClientId());
    }

    public int createClient(GameInterface client) {
        this.clients.put(getClientCount() - 1, client);
        this.goldPoint.add(0);
        this.culturePoint.add(0);
        this.sciencePoint.add(0);
        this.faithPoint.add(0);
        return getClientCount() - 1;
    }

    private int getClientCount() {
        if (this.clients == null) {
            return 0;
        } else {
            return this.clients.size();
        }
    }
}
