/**
 * @file GameInterface.java
 * @brief This file contains the GameInterface interface.
 * @date 21/04/2024
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

    int getNextClientId();


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


    // Les villes du joueur
    List<City> getCities();

    // Les villes du jeu
    List<City> getAllCities();

    // Les unités du joueur
    List<Unite> getUnites();

    // Les unités du jeu
    List<Unite> getAllUnites();


    // The boolean is used to know if the city has been created
    boolean createCity(Tile tile);

    // Permet de passer au tour suivant
    void nextTurn();

    // Crée un joueur
    int createClient(GameInterface client);

    // Achète un item en dépensant de l'or, de la culture, de la science et/ou de la foi
    // Retourne vrai si l'achat a pu être effectué, faux sinon
    boolean buyItem(int gold, int culture, int science, int faith);

    // Permet de faire passer des informations de log
    void setLog(String log);

    // Permet de récupérer les informations de log
    String getLog();

    // Permet de sauvegarder la partie
    void saveGame();
}
