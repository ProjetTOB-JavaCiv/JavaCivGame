/**
 * @file Client.java
 * @brief This file contains the Client class.
 * @date 18/04/2024
 */

package com.javaciv.client;

import com.javaciv.GameInterface;
import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.City;
import com.javaciv.gameElement.Unite;

import java.util.List;

public class Client implements GameInterface {
    private GameInterface server;

    private int clientId = -1;

    private int goldPoint;

    private int culturePoint;

    private int sciencePoint;

    private int faithPoint;

    private List<City> cities;

    private List<Unite> unites;

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
        if (this.canPassTurn()) {
            this.goldPoint = this.server.getGoldPoint();
        }
        return this.goldPoint;
    }

    public int getCulturePoint() {
        if (this.canPassTurn()) {
            this.culturePoint = this.server.getCulturePoint();
        }
        return this.culturePoint;
    }

    public int getSciencePoint() {
        if (this.canPassTurn()) {
            this.sciencePoint = this.server.getSciencePoint();
        }
        return this.sciencePoint;
    }

    public int getFaithPoint() {
        if (this.canPassTurn()) {
            this.faithPoint = this.server.getFaithPoint();
        }
        return this.faithPoint;
    }

    public int getGoldPointProduction() {
        return this.server.getGoldPointProduction();
    }

    public int getCulturePointProduction() {
        return this.server.getCulturePointProduction();
    }

    public int getSciencePointProduction() {
        return this.server.getSciencePointProduction();
    }

    public int getFaithPointProduction() {
        return this.server.getFaithPointProduction();
    }

    public List<City> getCities() {
        if (this.canPassTurn()) {
            this.cities = this.server.getCities();
        }
        return this.cities;
    }

    public List<Unite> getUnites() {
        if (this.canPassTurn()) {
            this.unites = this.server.getUnites();
        }
        return this.unites;
    }

    public boolean createCity(Tile tile) {
        return this.server.createCity(tile);
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

    public boolean buyItem(int gold, int culture, int science, int faith) {
        if (this.canPassTurn()) {
            return this.server.buyItem(gold, culture, science, faith);
        } else {
            return false;
        }
    }
}
