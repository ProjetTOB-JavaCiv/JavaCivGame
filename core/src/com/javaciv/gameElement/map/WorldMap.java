package com.javaciv.gameElement.map;

import java.util.ArrayList;

import com.javaciv.Utils;
import com.javaciv.builder.HashMapLand;
import com.javaciv.type.LandType;

/**
 * Cette classe représente la carte du monde du jeu.
 * Cette carte est implémentée comme une matrice.
 */
public class WorldMap {
    /**
     * La hauteur de la map.
     */
    public int height;

    /**
     * La largeur de la map.
     */
    public int width;

    /**
     * La map est stockée sous la forme d'un tableau
     * 1D pour des raisons de performances.
     */
    public Tile[] worldMap;

    /**
     * Initialise une carte vide à partir de ses dimensions.
     * @param h la hauteur de la carte
     * @param w la largeur de la carte
     */
    public WorldMap(int h, int w) {
        this.height = h;
        this.width = w;
        this.worldMap = generateRandomMap(h, w);
    }

    /**
     * Génère une tuille aléatoire
     * @return la tuille avec un type de terrain généré aléatoire
     */
    private Tile randomTile() {
        int randomInt = Utils.randomInt(LandType.values().length);
        // On utilise l'entier aléatoire pour choisir un terrain et on cherche dans la hashMap a quoi cela correspond
        return HashMapLand.getLand(LandType.values()[randomInt]);
    }

    /**
     * Génère une liste de germes aléatoires.
     * @return la liste de germes
     */
    private ArrayList<Tile> generateSproutsList() {
        ArrayList<Tile> sprouts = new ArrayList<Tile>();
        int sproutsCount = 10;

        for (int i = 0; i < sproutsCount; i++) {
            Tile newTile = randomTile();
            newTile.setX(Utils.randomInt(this.width));
            newTile.setY(Utils.randomInt(this.height));

            sprouts.add(newTile);
        }

        return sprouts;
    }

    /**
     * Génère une carte aléatoire.
     * @param h la hauteur de la carte
     * @param w la largeur de la carte
     * @return la carte générée
     */
    private Tile[] generateRandomMap(int h, int w) {
        Tile[] worldMap = new Tile[h * w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) { 
                //Génération d'une tuille aléatoire au position (x, y)
                Tile newTile = randomTile();
                newTile.setX(i);
                newTile.setY(j);
                
                // Ici on convertit les index de la matrice en abscisse et ordonnée (on transpose la matrice)
                worldMap[j + i * w] = newTile;
            }
        }
        return worldMap;
    }

    /**
     * Renvoie la tuile à la position donnée.
     * @param x l'abscisse donnée
     * @param y l'ordonnée donnée
     * @return la tuile recherchée
     */
    public Tile at(int i, int j) {
        return this.worldMap[j + i * this.width];
    }

    /**
     * Renvoie la hauteur de la carte.
     * @return la hauteur de la carte
     */
    public int getHeight() {
        return height;
    }

    /**
     * Renvoie la largeur de la carte.
     * @return la largeur de la carte
     */
    public int getWidth() {
        return width;
    }

    /**
     * Renvoie la carte du monde.
     * @return la carte du monde
     */
    public Tile[] getWorldMap() {
        return worldMap;
    }
}
