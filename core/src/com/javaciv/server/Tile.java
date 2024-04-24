package com.javaciv.server;

/**
 * Cette classe représente une tuile.
 * Une tuile correspond à une case de la carte du monde,
 * elle contient des informations sur ce qui se trouve dessus
 * ainsi que du type de terrain qu'elle représente
 */
public class Tile {
    /**
     * La position x de la tuile;
     */
    private int x;
    /**
     * La position y de la tuile.
     */
    private int y;
    /**
     * Le type de terrain de la tuile.
    */
    private Terrain terrain;

    /**
     * Constructeur d'une tuile.
     *
     * @param x position x de la tuile
     * @param y position y de la tuile
     * @param terrain type de terrain de la tuile
     */
    public Tile(int x, int y, Terrain terrain) {
        this.x = x;
        this.y = y;
        this.terrain = terrain;
    }

    /**
     * permet d'obtenir la position x de la tuile.
     * @return la position x de la tuile.
     */
    public int getX() {
        return this.x;
    }

    /**
     * permet d'obtenir la position y de la tuile.
     * @return la position y de la tuile.
     */
    public int getY() {
        return this.y;
    }

    /** Permet de recuperer le terrain de la tuile
     * @return le terrain de la tuile
     */
    public Terrain getTerrain() {
        return this.terrain;
    }

    public void print() {
        System.out.print("[" + this.x + "," + this.y + "|" + this.terrain.toInt() + "]");
    }
}