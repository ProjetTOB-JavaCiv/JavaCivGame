package com.javaciv.gameElement.map;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.javaciv.Utils;
import com.javaciv.builder.HashMapLand;
import com.javaciv.type.LandType;
import com.javaciv.type.ProductionType;

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
        generateRandomMap(h, w);
    }

    /**
     * Génère une tuile aléatoire
     * @param onlySea si vrai, la tuile générée sera une tuile de mer
     * @return la tuile avec un type de terrain généré aléatoire
     */
    private Tile randomTile(Map<Integer, Double> distribution) {
        // On génère un terrain aléatoire en fonction d'une distribution de probabilités donnée
        int randomInt = Utils.randomInt(distribution);

        // On utilise l'entier randomInt pour choisir un terrain et on cherche dans la hashMap a quoi cela correspond
        return HashMapLand.getLand(LandType.values()[randomInt]);
    }

    /**
     * Génère une liste de germes aléatoires.
     * @return la liste de germes
     */
    private ArrayList<Tile> generateSproutsList(Map<Integer, Double> distribution, LandType landType, int sproutsCount, double center) {
        ArrayList<Tile> sprouts = new ArrayList<Tile>();

        for (int i = 0; i < sproutsCount; i++) {
            Tile newTile = randomTile(distribution);
            int x = Utils.randomInt((int) (center * this.width), (int) ((1f-center) * this.width));
            int y = Utils.randomInt((int) (center * this.height), (int) ((1f-center) * this.height));

            if (this.worldMap[x + y * this.width].getLand() == landType) {
                newTile.setX(x);
                newTile.setY(y);
                sprouts.add(newTile);
            } else {
                i--;
                continue;
            }
        }

        return sprouts;
    }

    /**
     * Récupère le terrain de la germe la plus proche d'une tuile donnée.
     * On ajoute une distance maximale de recherche.
     * @param tile la tuile donnée
     * @return le terrain de la germe la plus proche
     */
    public LandType getNearestSprout(Tile tile, int maxDistance) {
        Tile nearestSprout = null;
        double minDistance = Double.MAX_VALUE;

        for (Tile sprout : this.sprouts) {
            double distance = tile.distance(sprout);
            if (distance < minDistance) {
                minDistance = distance;
                nearestSprout = sprout;
            }
        }
        if (tile.distance(nearestSprout) <= maxDistance) {
            return nearestSprout.getLand();
        }
        else {
            // On fait un vote de majorité sur les tuiles voisines
            LandType[] neighbors = new LandType[8];
            LandType majority = tile.getLand();
            int count = 1;
            neighbors[0] = tile.getLand();
            for (int i = -1; i <= 1; i++) {
                for (int j = 0; j <= 1; j++) {
                    int x = tile.getX() + i;
                    int y = tile.getY() + j;
                    x = x < 0 ? 0 : x;
                    y = y < 0 ? 0 : y;
                    x = x >= this.width ? this.width - 1 : x;
                    y = y >= this.height ? this.height - 1 : y;
                    Tile neighbor = this.at(x, y);
                    if (neighbor != null) {
                        neighbors[count] = neighbor.getLand();
                        count++;
                    }
                }
            }
            for (int i = 0; i < count; i++) {
                int currentCount = 1;
                for (int j = i + 1; j < count; j++) {
                    if (neighbors[i] == neighbors[j]) {
                        currentCount++;
                    }
                }
                if (currentCount > count / 2) {
                    majority = neighbors[i];
                    break;
                }
            }
            return majority;
        }
    }

    /**
     * Génère une carte aléatoire.
     * @param h la hauteur de la carte
     * @param w la largeur de la carte
     * @return la carte générée
     */
    private void generateRandomMap(int h, int w) {
        // On définit une distribution de probabilités pour les différents types de terrain
        HashMap<Integer, Double> distPlaine = new HashMap();
        distPlaine.put(0, 0.45);
        distPlaine.put(5, 0.55);

        HashMap<Integer, Double> distSubPlaine = new HashMap();
        distSubPlaine.put(0, 0.2);
        distSubPlaine.put(1, 0.2);
        distSubPlaine.put(2, 0.3);
        distSubPlaine.put(4, 0.25);
        distSubPlaine.put(5, 0.05);

        HashMap<Integer, Double> distMontagne = new HashMap();
        distMontagne.put(1, 0.45);
        distMontagne.put(2, 0.25);
        distMontagne.put(3, 0.1);
        distMontagne.put(4, 0.2);
        

        this.worldMap = new Tile[h * w];

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                this.worldMap[j + i * w] = HashMapLand.getLand(LandType.EAU);
                this.worldMap[j + i * w].setX(i);
                this.worldMap[j + i * w].setY(j);
            }
        }

        this.sprouts = generateSproutsList(distPlaine, LandType.EAU, 60, 0.1f);
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Tile tile = this.worldMap[j + i * w];
                tile.setLand(getNearestSprout(tile, 25));
            }
        }

        this.sprouts = generateSproutsList(distSubPlaine, LandType.PLAINE, 120, 0.2f);
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Tile tile = this.worldMap[j + i * w];
                if (tile.getLand() == LandType.PLAINE) {
                    tile.setLand(getNearestSprout(tile, 8));
                }
            }
        }

        this.sprouts = generateSproutsList(distMontagne, LandType.PRAIRIE, 100, 0.1f);
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Tile tile = this.worldMap[j + i * w];
                if (tile.getLand() == LandType.PRAIRIE) {
                    tile.setLand(getNearestSprout(tile, 5));
                }
            }
        }

        this.sprouts = generateSproutsList(distMontagne, LandType.TOUNDRA, 100, 0.2f);
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Tile tile = HashMapLand.getLand(LandType.TOUNDRA);
                tile.setX(j);
                tile.setY(i);
                tile.setLand(getNearestSprout(tile, 7));
                worldMap[j + i * w] = tile;
            }
        }
        
    }

    /**
     * Renvoie la tuile à la position donnée.
     * @param x l'abscisse donnée
     * @param y l'ordonnée donnée
     * @return la tuile recherchée
     */
    public Tile at(int i, int j) {
        if (i < 0 || i >= this.width || j < 0 || j >= this.height) {
            return this.worldMap[0];
        }
        else {
            return this.worldMap[i + j * this.width];
        }
    }

    /**
     * Renvoie la hauteur de la carte.
     * @return la hauteur de la carte
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Renvoie la largeur de la carte.
     * @return la largeur de la carte
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Renvoie un ensemble de tuile adjacente aux coordonnées données
     * @param x l'abscisse donnée
     * @param y l'ordonnée donnée
     * @return l'ensemble des tuiles adjacentes
     */
    public HashSet<Tile> nextTo(int x, int y) {
        HashSet<Tile> nextToSet = new HashSet<Tile>();
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                nextToSet.add(this.at(i, j));
            }
        }
        nextToSet.remove(this.at(x,y));
        return nextToSet;
    }

    /**
     * Renvoie la carte du monde.
     * @return la carte du monde
     */
    public Tile[] getWorldMap() {
        return this.worldMap;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                str += this.at(i, j).getLand().toFirstLetter() + " ";
            }
            str += "\n";
        }
        return str;
    }
}