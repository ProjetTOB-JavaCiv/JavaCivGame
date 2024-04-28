package com.javaciv.server;

import java.util.Set;

import com.javaciv.server.Unite;

/**
 * Classe représentant une unité militaire, cette classe est une implémentation
 * de la classe Unité,
 * Une unité militaire est capable d'attaquer d'autres unités, d'attaquer des villes,
 * de se reposer pour gagner de la vie et attendre des instructions supplémentaires.
 */
public class Military implements Unite {

    /** Le nom de l'unité */
    String name;
    /** Le joueur possedant l'unité */
    //Player owner;
    /** La tuile sur laquelle se trouve l'unite */
    Tile position;
    /** Le nombre de points de vie actuel de l'unité */
    int pv;
    /** Puissance d'attaque d'une unité */
    int attack;
    /** Point de défense aux agression d'une unité */
    int defense;
    /** Le nombre de point de mouvement restant a l'unité */
    int actionPoint;
    /** indicateur permettant de savoir si l'unité s'est deja deplacée dans le tour */
    boolean alreadyMove = false;
    /** La quantité de point d'action que possède une unité au début du tour*/
    final int BASE_ACTION_POINT;
    /** le cout en materiel pour produire l'unité */
    final int PRODUCTION_COST;
    
    //TODO: Finir le constructeur -> Problème de paramètre trop nombreux.
    public Military(String name, int BASE_ACTION_POINT, int PRODUCTION_COST, int attack, int pv, int defense) {
        this.name = name;
        this.position = new Tile(0, 0, Terrain.TERRESTRE);
        //this.owner = owner;
        this.BASE_ACTION_POINT = BASE_ACTION_POINT;
        this.PRODUCTION_COST = PRODUCTION_COST;
        this.position.setMilitaryUnitOnTile(this);
        this.actionPoint = this.BASE_ACTION_POINT;
        this.attack = attack;
        this.pv = pv;
        this.defense = defense;
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

}
