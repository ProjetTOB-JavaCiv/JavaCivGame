/**
 * @file GameInterface.java
 * @brief This file contains the GameInterface interface.
 * @author Théo Bessel
 * @date 21/04/2024
 * @version 1.0
 */

package com.javaciv;

import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.City;
import com.javaciv.gameElement.Unite;

import java.util.List;

/**
 * Cette interface représente un jeu
 */
public interface GameInterface {
    // La map du jeu
    WorldMap getWorldMap();


    // L'id du joueur (côté serveur : l'id du joueur courant, côté client : l'id du client)
    int getClientId();


    // L'or du joueur
    int getGoldPoint();

    // Les points de culture du joueur
    int getCulturePoint();

    // Les points de science du joueur
    int getSciencePoint();

    // Les points de foi du joueur
    int getFaithPoint();


    // La production d'or du joueur
    int getGoldPointProduction();

    // La production de culture du joueur
    int getCulturePointProduction();

    // La production de science du joueur
    int getSciencePointProduction();

    // La production de foi du joueur
    int getFaithPointProduction();


    List<City> getCities();

    List<Unite> getUnites();


    void createCity(Tile tile);


    // Permet de passer au tour suivant
    void nextTurn();


    // Crée un joueur
    int createClient(GameInterface client);
}
