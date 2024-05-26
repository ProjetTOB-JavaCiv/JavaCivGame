package com.javaciv.gameElement;

import java.util.Set;

import com.javaciv.builder.HashMapLand;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.type.LandType;
import com.javaciv.client.Client;

/**
 * 
 */
public class Civilian implements Unite {
    
    /** Le nom de l'unité */
    String name;
    /** Le joueur possedant l'unité */
    Client owner;
    /** La tuile sur laquelle se trouve l'unite */
    Tile position;
    /** Le nombre de point de mouvement restant a l'unité */
    int actionPoint;
    /** indicateur permettant de savoir si l'unité s'est deja deplacée dans le tour */
    boolean alreadyMove = false;
    /** La quantité de point d'action que possède une unité au début du tour*/
    final int BASE_ACTION_POINT;
    /** le cout en materiel pour produire l'unité */
    final int PRODUCTION_COST;

    public Civilian(String name, Client owner, int BASE_ACTION_POINT, int PRODUCTION_COST) {
        this.name = name;
        this.position = HashMapLand.getLand(LandType.PLAINE);
        this.owner = owner;
        this.BASE_ACTION_POINT = BASE_ACTION_POINT;
        this.PRODUCTION_COST = PRODUCTION_COST;
        this.position.setCivilianUnitOnTile(this);
        this.actionPoint = this.BASE_ACTION_POINT;
    }


    public Set<Tile> calculMovement(WorldMap map, Tile currentTile, int remaningActionPoint, boolean iDeplacement) {
        // TODO Auto-generated method stub
        return null;
    }

    //Vrmt public ça ???
    public void seDeplacer(WorldMap map, Tile destination) {
        // TODO Auto-generated method stub
        
    }

    public Tile getNatureDeplacement() {
        // TODO Auto-generated method stub
        return null;
    }

    public Set<Tile> getPossibleMovement(WorldMap map) {
        return null;
    }

    /**
     * Renvoie le nom de l'unité
     * @return le nom de l'unité
     */
    public String getName() {
        return this.name;
    }

    /** Retourne le joueur qui possède l'unité*/
    public Client getOwner() {
        return this.owner;
    }

    /** Renvoie la tuile sur laquelle se trouve l'unite */
    public Tile getPosition() {
        return this.position;
    }

    /** Renvoie le nombre de points d'action restant a l'unité */
    public int getActionPoint() {
        return this.actionPoint;
    }

    /** Renvoie le nombre de point d'action initiale de l'unité */
    public int getBaseActionPoint() {
        return this.BASE_ACTION_POINT;
    }

    /** Renvoie le cout en production pour produire l'unité */
    public int getCost() {
        return this.PRODUCTION_COST;
    }

    public String toString() {
        return "[Civilian | " + this.name + " : " + this.actionPoint + "ap]";
    }
}
