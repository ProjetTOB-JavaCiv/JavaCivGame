package com.javaciv.server;

import java.util.Set;

public interface Unite {

    /** Le nom de l'unité */
    String nom;
    /** Le joueur possedant l'unité */
    Joueur owner;
    /** La tuile sur laquelle se trouve l'unite */
    Tile position;
    /** Le nombre de points de vie actuel de l'unité */
    int pv = 100;
    /** Le nombre de point de mouvement restant a l'unité */
    int actionPoint;
    /** indicateur permettant de savoir si l'unité s'est deja deplacée dans le tour */
    boolean alreadyMove = false;
    /** La quantité de tuile que peut parcourir une unité en un tour */
    final int MOVEMENT_RANGE;
    /** le cout en materiel pour produire l'unité */
    final int COUT;

    /**
    * methode de deplacement de l'unité sur une tuile
    * Pre : destination in getDeplacementPossible()
    * @param destination tuile de destination de l'unité
    * @param map carte du jeu sur laquelle l'unité évolue
    */
    public void seDeplacer(WorldMap map, Tile destination);

    /**
    * Permet d'obtenir l'ensemble des tuiles sur lesquelles l'unité peut se deplacer
    * @param map la carte du jeu
    * @param destination Destination que l'unité va atteindre
    * @return l'ensemble des tuiles sur lesquelles l'unité peut se deplacer
    */
    public Set<Tile> getPossibleMovement(WorldMap map);

     /**
     * Permet de calculer les deplacement possible de maniere recursive
    * @param map la carte du jeu
    * @param t la tuile a partir de laquelle on lance le calcul
    * @param pMRestant le nombre de point de mouvement restant
    * @return les deplacements possible a partir d'une tuile et d'un certain nombre de point de mouvement
    */
    Set<Tile> calculMovement(WorldMap map, Tile currentTile, int remaningActionPoint, boolean iDeplacement);

    /**
     * Permet d'obtenir les informations géographique du terrain sur lequel cette unité peut se deplacer
     * @return la nature du terrain sur lequel cette unité peut se deplacer
     */
    abstract public Tile getNatureDeplacement();
}
