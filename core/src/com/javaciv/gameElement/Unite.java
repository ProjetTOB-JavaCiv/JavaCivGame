package com.javaciv.gameElement;

import java.util.Set;

import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.WorldMap;

/** Interface définisant ce qu'est une unité.
 *  Interface qui va être implémenté par les classe "Military" et "Civilian"
 */
public interface Unite {

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