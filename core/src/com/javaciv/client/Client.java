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
import com.javaciv.server.Server;

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

    // Used to send logs from the server to the client
    private String log = "";

    /**
     * Constructor of the Client class
     * @param server The server
     */
    public Client(GameInterface server) {
        this.server = server;
        this.clientId = createClient(this);
    }

    /**
     * This function returns the world map
     * @return the world map
     */
    public WorldMap getWorldMap() {
        return this.server.getWorldMap();
    }

    /**
     * This function returns the client id
     * @return the client id
     */
    public int getClientId() {
        return this.clientId;
    }

    /**
     * This function returns the next client id
     * @return the next client id
     */
    public int getNextClientId() {
        return this.server.getNextClientId();
    }

    /**
     * This function returns the gold point
     * @return the gold point
     */
    public int getGoldPoint() {
        if (this.canPassTurn()) {
            this.goldPoint = this.server.getGoldPoint();
        }
        return this.goldPoint;
    }

    /**
     * This function returns the culture point
     * @return the culture point
     */
    public int getCulturePoint() {
        if (this.canPassTurn()) {
            this.culturePoint = this.server.getCulturePoint();
        }
        return this.culturePoint;
    }

    /**
     * This function returns the science point
     * @return the science point
     */
    public int getSciencePoint() {
        if (this.canPassTurn()) {
            this.sciencePoint = this.server.getSciencePoint();
        }
        return this.sciencePoint;
    }

    /**
     * This function returns the faith point
     * @return the faith point
     */
    public int getFaithPoint() {
        if (this.canPassTurn()) {
            this.faithPoint = this.server.getFaithPoint();
        }
        return this.faithPoint;
    }

    /**
     * This function returns the gold point production
     * @return the gold point production
     */
    public int getGoldPointProduction() {
        return this.server.getGoldPointProduction();
    }

    /**
     * This function returns the culture point production
     * @return the culture point production
     */
    public int getCulturePointProduction() {
        return this.server.getCulturePointProduction();
    }

    /**
     * This function returns the science point production
     * @return the science point production
     */
    public int getSciencePointProduction() {
        return this.server.getSciencePointProduction();
    }

    /**
     * This function returns the faith point production
     * @return the faith point production
     */
    public int getFaithPointProduction() {
        return this.server.getFaithPointProduction();
    }

    /**
     * This function returns the cities
     * @return the cities
     */
    public List<City> getCities() {
        if (this.canPassTurn()) {
            this.cities = this.server.getCities();
        }
        return this.cities;
    }

    /**
     * This function returns all the cities
     * @return the cities
     */
    public List<City> getAllCities() {
        return this.getCities();
    }

    /**
     * This function returns the unites
     * @return the unites
     */
    public List<Unite> getUnites() {
        if (this.canPassTurn()) {
            this.unites = this.server.getUnites();
        }
        return this.unites;
    }

    /**
     * This function returns all the unites
     * @return the unites
     */
    public List<Unite> getAllUnites() {
        return this.getUnites();
    }

    /**
     * This function returns the owner of the unit
     * @param tile The tile
     * @return the owner of the unit
     */
    public boolean createCity(Tile tile) {
        return this.server.createCity(tile);
    }

    /**
     * This function passes the turn
     */
    public void nextTurn() {
        if (this.canPassTurn()) {
            this.server.nextTurn();
        }
    }

    /**
     * This function checks if the turn can be passed
     * @return true if the turn can be passed, false otherwise
     */
    public boolean canPassTurn() {
        return this.getClientId() == this.server.getClientId();
    }

    /**
     * Create a client
     * @param client the client
     * @return the client id
     */
    public int createClient(GameInterface client) {
        if (this.clientId == -1) {
            return this.server.createClient(this);
        } else {
            return this.clientId;
        }
    }

    /**
     * This function buys a tile
     * @param gold the gold needed to buy the tile
     * @param culture the culture needed to buy the tile
     * @param science the science needed to buy the tile
     * @param faith the faith needed to buy the tile
     * @return true if the tile has been bought, false otherwise
     */
    public boolean buyItem(int gold, int culture, int science, int faith) {
        if (this.canPassTurn()) {
            return this.server.buyItem(gold, culture, science, faith);
        } else {
            return false;
        }
    }

    /**
     * This function returns the server
     * @return the server
     */
    public GameInterface getServer() {
        return this.server;
    }

    /**
     * This function sets the log
     * @param log the log
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * This function returns the log
     * @return the log
     */
    public String getLog() {
        return this.log;
    }

    /**
     * This function returns the client
     * @param clientId the client id
     * @return the client
     */
    public Client getClient(int clientId) {
        return (Client) ((Server) this.server).getClients().get(clientId);
    }

    /**
     * This function returns the client id
     * @return the client id
     */
    public void saveGame() {
        this.server.saveGame();
    }
}
