/**
 * @file Server.java
 * @brief This file contains the Server class.
 * @date 18/04/2024
 */

package com.javaciv.server;

import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.Infrastructure;
import com.javaciv.gameElement.Unite;
import com.javaciv.gameElement.City;
import com.javaciv.client.Client;
import com.javaciv.GameInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server implements GameInterface {
    private WorldMap worldMap;

    private HashMap<Integer, GameInterface> clients;

    private int clientId;

    private List<Integer> goldPoint;

    private List<Integer> culturePoint;

    private List<Integer> sciencePoint;

    private List<Integer> faithPoint;

    private List<List<City>> cities;

    private List<List<Unite>> unites;

    public Server() {
        this.worldMap = new WorldMap(100, 200);
        this.clients = new HashMap<Integer, GameInterface>();
        this.clientId = 0;
        this.goldPoint = new ArrayList<Integer>();
        this.culturePoint = new ArrayList<Integer>();
        this.sciencePoint = new ArrayList<Integer>();
        this.faithPoint = new ArrayList<Integer>();
        this.cities = new ArrayList<List<City>>();
        this.unites = new ArrayList<List<Unite>>();
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

    // TODO : implement the productions amounts to replace hardcoded amount for each player
    public int getGoldPointProduction() {
        int goldPointProduction = 0;

        for (City city : this.getCities()) {
            goldPointProduction += city.getGoldPerTurnProd();
        }

        return goldPointProduction;
    }

    public int getCulturePointProduction() {
        int culturePointProduction = 0;

        for (City city : this.getCities()) {
            culturePointProduction += city.getCulturePerTurnProd();
        }

        return culturePointProduction;
    }

    public int getSciencePointProduction() {
        int sciencePointProduction = 0;

        for (City city : this.getCities()) {
            sciencePointProduction += city.getSciencePerTurnProd();
        }

        return sciencePointProduction;
    }

    public int getFaithPointProduction() {
        int faithPointProduction = 0;

        for (City city : this.getCities()) {
            faithPointProduction += city.getFaithPerTurnProd();
        }

        return faithPointProduction;
    }

    public List<City> getCities() {
        return this.cities.get(this.clientId);
    }

    public List<Unite> getUnites() {
        return this.unites.get(this.clientId);
    }

    public boolean createCity(Tile tile) {
        if (this.isTileAvailableForCity(tile)) {
            List<City> playerCities = this.getCities();
            Client owner = (Client) this.clients.get(this.clientId - 1);
            City city = new City(tile, owner);
            // TODO : remove this default infrastructure and find a way to access to a city from a tile.
            // TODO : add the actions to add infrastructures and tiles to a city with a menu button.
            Infrastructure infrastructure = new Infrastructure(10, 3, 2, 1, 2, 4, 1);
            city.buildInfrastructure(infrastructure);
            playerCities.add(city);
            this.cities.set(this.clientId, playerCities);
            return true;
        }
        return false;
    }

    private boolean isTileAvailableForCity(Tile tile) {
        /*Si des unités terrestre ne peuvent même pas loger sur la tuille, une ville encore moins. Peut être renommer la
        varible pour la rendre plus parlante ??*/        
        if(tile.getIsTraversableByLandUnit() == false) {
            return false;
        }

        for (City city : this.getCities()) {
            if (city.getPosition().distance(tile) == 0) {
                return false;
            }
        }
        return true; 
    }


    public void nextTurn() {

        //Actualisation du nombre de point du joueur
        this.goldPoint.set(this.clientId, this.goldPoint.get(this.clientId) + this.getGoldPointProduction());
        this.culturePoint.set(this.clientId, this.culturePoint.get(this.clientId) + this.getCulturePointProduction());
        this.sciencePoint.set(this.clientId, this.sciencePoint.get(this.clientId) + this.getSciencePointProduction());
        this.faithPoint.set(this.clientId, this.faithPoint.get(this.clientId) + this.getFaithPointProduction());
        this.clientId = (this.clientId + 1) % getClientCount();
        System.out.println("Next turn, clientId is : " + this.getClientId());

        //Actualisation de l'état des villes : Ajout d'une nouvelle tuile.
        for (City city : this.getCities()) {
            city.checkForNewTile();
        }
    }

    public int createClient(GameInterface client) {
        this.clients.put(getClientCount() - 1, client);
        this.goldPoint.add(0);
        this.culturePoint.add(0);
        this.sciencePoint.add(0);
        this.faithPoint.add(0);
        this.cities.add(new ArrayList<City>());
        this.unites.add(new ArrayList<Unite>());
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
