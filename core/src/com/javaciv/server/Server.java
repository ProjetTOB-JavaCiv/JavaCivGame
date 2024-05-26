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
import com.javaciv.type.LandType;
import com.javaciv.client.Client;
import com.javaciv.GameInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileWriter;


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

    /**
     * This is the constructor of the Server class.
     */
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

    /**
     * This function returns the world map.
     * @return the world map
     */
    public WorldMap getWorldMap() {
        return this.worldMap;
    }

    /**
     * This function returns the client id.
     * @return the client id
     */
    public int getClientId() {
        return this.clientId;
    }

    /**
     * This function returns the gold point of the client.
     * @return the gold point of the client
     */
    public int getGoldPoint() {
        return this.goldPoint.get(this.clientId);
    }

    /**
     * This function returns the culture point of the client.
     * @return the culture point of the client
     */
    public int getCulturePoint() {
        return this.culturePoint.get(this.clientId);
    }

    /**
     * This function returns the science point of the client.
     * @return the science point of the client
     */
    public int getSciencePoint() {
        return this.sciencePoint.get(this.clientId);
    }

    /**
     * This function returns the faith point of the client.
     * @return the faith point of the client
     */
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

    // TODO : implement the productions amounts to replace hardcoded amount for each player
    public int getCulturePointProduction() {
        int culturePointProduction = 0;

        for (City city : this.getCities()) {
            culturePointProduction += city.getCulturePerTurnProd();
        }

        return culturePointProduction;
    }

    // TODO : implement the productions amounts to replace hardcoded amount for each player
    public int getSciencePointProduction() {
        int sciencePointProduction = 0;

        for (City city : this.getCities()) {
            sciencePointProduction += city.getSciencePerTurnProd();
        }

        return sciencePointProduction;
    }

    // TODO : implement the productions amounts to replace hardcoded amount for each player
    public int getFaithPointProduction() {
        int faithPointProduction = 0;

        for (City city : this.getCities()) {
            faithPointProduction += city.getFaithPerTurnProd();
        }

        return faithPointProduction;
    }

    /**
     * This function returns the cities of the client.
     * @return the cities of the client
     */
    public List<City> getCities() {
        return this.cities.get(this.clientId);
    }

    /**
     * This function returns all the cities of all the clients.
     * @return all the cities of the clients
     */
    public List<City> getAllCities() {
        List<City> allCities = new ArrayList<City>();
        for (List<City> cities : this.cities) {
            allCities.addAll(cities);
        }
        return allCities;
    }

    /**
     * This function returns the unites of the client.
     * @return the unites of the client
     */
    public List<Unite> getUnites() {
        return this.unites.get(this.clientId);
    }

    /**
     * This function returns all the unites of all the clients.
     * @return all the unites of the clients
     */
    public List<Unite> getAllUnites() {
        List<Unite> allUnites = new ArrayList<Unite>();
        for (List<Unite> unites : this.unites) {
            allUnites.addAll(unites);
        }
        return allUnites;
    }

    /**
     * This function creates a city on a tile.
     * @param tile the tile where the city will be created
     * @return true if the city has been created, false otherwise
     */
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
        this.setLog("The tile is not available for a city.");
        return false;
    }

    /**
     * This function checks if a tile is available for a city.
     * @param tile the tile to check
     * @return true if the tile is available for a city, false otherwise
     */
    private boolean isTileAvailableForCity(Tile tile) {
        /*Si des unités terrestre ne peuvent même pas loger sur la tuile, une ville encore moins. Peut être renommer la
        varible pour la rendre plus parlante ??*/
        if(tile.getIsTraversableByLandUnit() == false) {
            return false;
        }

        if(tile.getLand() == LandType.EAU) {
            return false;
        }

        for (City city : this.getCities()) {
            if (city.getPosition().distance(tile) == 0) {
                return false;
            }
        }
        return true; 
    }

    /**
     * This function returns the next client id.
     * @return the next client id
     */
    public int getNextClientId() {
        return (this.clientId + 1) % getClientCount();
    }

    /**
     * This function passes the turn.
     */
    public void nextTurn() {
        //Actualisation du nombre de point du joueur
        this.goldPoint.set(this.clientId, this.goldPoint.get(this.clientId) + this.getGoldPointProduction());
        this.culturePoint.set(this.clientId, this.culturePoint.get(this.clientId) + this.getCulturePointProduction());
        this.sciencePoint.set(this.clientId, this.sciencePoint.get(this.clientId) + this.getSciencePointProduction());
        this.faithPoint.set(this.clientId, this.faithPoint.get(this.clientId) + this.getFaithPointProduction());
        this.clientId = this.getNextClientId();
        System.out.println("Next turn, clientId is : " + this.getClientId());

        //Actualisation de l'état des villes : Ajout d'une nouvelle tuile.
        for (City city : this.getCities()) {
            city.checkForNewTile();
        }
    }

    /**
     * This function creates a client.
     * @param client the client
     * @return the client id
     */
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

    /**
     * This function returns the number of clients.
     * @return the number of clients
     */
    public int getClientCount() {
        if (this.clients == null) {
            return 0;
        } else {
            return this.clients.size();
        }
    }

    /**
     * This function buys an item from the client.
     * The item can be a city, a unit, an infrastructure, etc.
     * @param gold the gold cost of the item
     * @param culture the culture cost of the item
     * @param science the science cost of the item
     * @param faith the faith cost of the item
     * @return true if the item has been bought, false otherwise
     */
    public boolean buyItem(int gold, int culture, int science, int faith) {
        if (this.goldPoint.get(this.clientId) >= gold &&
            this.culturePoint.get(this.clientId) >= culture &&
            this.sciencePoint.get(this.clientId) >= science &&
            this.faithPoint.get(this.clientId) >= faith) {
                this.goldPoint.set(this.clientId, this.goldPoint.get(this.clientId) - gold);
                this.culturePoint.set(this.clientId, this.culturePoint.get(this.clientId) - culture);
                this.sciencePoint.set(this.clientId, this.sciencePoint.get(this.clientId) - science);
                this.faithPoint.set(this.clientId, this.faithPoint.get(this.clientId) - faith);
                return true;
        } else {
            if (this.goldPoint.get(this.clientId) < gold) {
                //System.out.println("Not enough gold to buy this item.");
                this.setLog("Not enough gold to buy this item.");
            }
            if (this.culturePoint.get(this.clientId) < culture) {
                //System.out.println("Not enough culture to buy this item.");
                this.setLog("Not enough culture to buy this item.");
            }
            if (this.sciencePoint.get(this.clientId) < science) {
                //System.out.println("Not enough science to buy this item.");
                this.setLog("Not enough science to buy this item.");
            }
            if (this.faithPoint.get(this.clientId) < faith) {
                //System.out.println("Not enough faith to buy this item.");
                this.setLog("Not enough faith to buy this item.");
            }
            return false;
        }
    }

    /**
     * This function sets the log of the client.
     * @param log the log
     */
    public void setLog(String log) {
        this.clients.get(this.clientId - 1).setLog(log);
    }

    /**
     * This function returns the log of the client.
     * @return the log of the client
     */
    public String getLog() {
        return this.clients.get(this.clientId - 1).getLog();
    }

    /**
     * This function returns the clients.
     * @return the clients
     */
    public List<GameInterface> getClients() {
        List<GameInterface> clients = new ArrayList<GameInterface>();
        for (int i = 0; i < getClientCount(); i++) {
            clients.add(this.clients.get(i));
        }
        return clients;
    }

    /**
     * This function saves the game.
     */
    public void saveGame() {
        // Open the file
        File file = new File("save/gameState.txt");
        // Write the game state
        try {
            FileWriter writer = new FileWriter(file);
            // Print the world map
            writer.write(this.worldMap.toString());
            writer.write("\n\n=======================================\n");
            // Print the clients
            for (int i = 0; i < getClientCount(); i++) {
                GameInterface client = this.clients.get(i);
                if (client != null) {
                    writer.write("Client n°" + client.getClientId());
                    writer.write("\n---------------------------------------\n");
                    // Print the cities
                    writer.write("Cities :\n");
                    if (client.getCities() != null) {
                        for (City city : client.getCities()) {
                            writer.write(" - " + city.toString() + "\n");
                        }
                    }
                    writer.write("\n---------------------------------------\n");
                    // Print the unites
                    writer.write("Unites :\n");
                    if (client.getUnites() != null) {
                        for (Unite unite : client.getUnites()) {
                            writer.write(" - " + unite.toString() + "\n");
                        }
                    }
                    writer.write("\n---------------------------------------\n");
                    writer.write("Ressources :\n");
                    // Print the gold point
                    writer.write(" - Gold : " + client.getGoldPoint() + "\n");
                    // Print the culture point
                    writer.write(" - Culture : " + client.getCulturePoint() + "\n");
                    // Print the science point
                    writer.write(" - Science : " + client.getSciencePoint() + "\n");
                    // Print the faith point
                    writer.write(" - Faith : " + client.getFaithPoint() + "\n");
                    writer.write("\n=======================================\n\n");
                }
            }
            // Close the file
            writer.close();
            this.setLog("Game saved in save/gameState.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
