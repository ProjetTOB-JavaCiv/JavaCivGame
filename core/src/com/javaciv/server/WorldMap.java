package com.javaciv.server;

import java.util.ArrayList;

import com.javaciv.Utils;

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
     * Génère un type de terrain aléatoire.
     * @return le type de terrain généré
     */
    private Terrain randomTerrain() {
        int randomInt = Utils.randomInt(Terrain.values().length);
        // On utilise l'entier aléatoire pour choisir un terrain
        return Terrain.values()[randomInt];
    }

    /**
     * Génère une liste de germes aléatoires.
     * @return la liste de germes
     */
    private ArrayList<Tile> generateSproutsList() {
        ArrayList<Tile> sprouts = new ArrayList<Tile>();
        int sproutsCount = 10;

        for (int i = 0; i < sproutsCount; i++) {
            sprouts.add(new Tile(
                Utils.randomInt(this.width),
                Utils.randomInt(this.height),
                randomTerrain()
            ));
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
                // Ici on convertit les index de la matrice en abscisse et ordonnée (on transpose la matrice)
                worldMap[j + i * w] = new Tile(j, i, randomTerrain());
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
