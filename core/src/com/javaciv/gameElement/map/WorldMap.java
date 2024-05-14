package com.javaciv.gameElement.map;

import java.util.ArrayList;

import java.util.HashMap;
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
    private int height;

    /**
     * La largeur de la map.
     */
    private int width;

    /**
     * La map est stockée sous la forme d'un tableau
     * 1D pour des raisons de performances.
     */
    public Tile[] worldMap;

    /**
     * Liste des germes sur la carte.
     */
    private ArrayList<Tile> sprouts;

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
     * Génère une tuile aléatoire
     * @param onlySea si vrai, la tuile générée sera une tuile de mer
     * @return la tuile avec un type de terrain généré aléatoire
     */
    private Tile randomTile() {
        // On définit une distribution de probabilités pour les différents types de terrain
        // PLAINE -> 0.3
        // DESERT -> 0.1
        // FORET -> 0.1
        // MONTAGNE -> 0.1
        // COLLINE -> 0.2
        // MER -> 0.2
        HashMap<Integer, Double> distribution = new HashMap();
        distribution.put(0, 0.6);
        distribution.put(1, 0.04);
        distribution.put(2, 0.25);
        distribution.put(3, 0.01);
        distribution.put(4, 0.025);
        distribution.put(5, 0.3);

        // On génère un terrain aléatoire en fonction d'une distribution de probabilités donnée
        int randomInt = Utils.randomInt(distribution);

        // On utilise l'entier randomInt pour choisir un terrain et on cherche dans la hashMap a quoi cela correspond
        return HashMapLand.getLand(LandType.values()[randomInt]);
    }

    /**
     * Génère une liste de germes aléatoires.
     * @return la liste de germes
     */
    private ArrayList<Tile> generateSproutsList() {
        ArrayList<Tile> sprouts = new ArrayList<Tile>();
        int sproutsCount = 400;

        for (int i = 0; i < sproutsCount; i++) {
            Tile newTile = randomTile();
            newTile.setX(Utils.randomInt(this.width));
            newTile.setY(Utils.randomInt(this.height));

            sprouts.add(newTile);
        }

        return sprouts;
    }

    /**
     * Récupère le terrain de la germe la plus proche d'une tuile donnée.
     * @param tile la tuile donnée
     * @return le terrain de la germe la plus proche
     */
    public LandType getNearestSprout(Tile tile) {
        Tile nearestSprout = null;
        double minDistance = Double.MAX_VALUE;

        for (Tile sprout : this.sprouts) {
            double distance = tile.distance(sprout);
            if (distance < minDistance) {
                minDistance = distance;
                nearestSprout = sprout;
            }
        }

        return nearestSprout.getLand();
    }

    /**
     * Génère une carte aléatoire.
     * @param h la hauteur de la carte
     * @param w la largeur de la carte
     * @return la carte générée
     */
    private Tile[] generateRandomMap(int h, int w) {
        Tile[] worldMap = new Tile[h * w];
        this.sprouts = generateSproutsList();
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Tile tile = HashMapLand.getLand(LandType.COLLINE);
                tile.setX(j);
                tile.setY(i);
                tile.setLand(getNearestSprout(tile));
                worldMap[j + i * w] = tile;
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
