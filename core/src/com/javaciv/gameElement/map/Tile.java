package com.javaciv.gameElement.map;

import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Military;

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
     * L'unité militaire étant sur la case
     * Il n'est pas obligatoire pour une tuille d'avoir une unité militaire dessus
    */
    private Military miliratyOnTile;
    /**
     * L'unité civile étant sur la case
     * Il n'est pas obligatoire pour une tuille d'avoir une unité militaire dessus
    */
    private Civilian civilianOnTile;

    /**
     * Booléen qui décrit si une tuille est occupé par une unité militaire
     */
    private boolean isMilitaryUnitOnTile = false;

    /**
     * Booléen qui décrit si une tuille est occupé par une unité civile
     */
    private boolean isCivilianUnitOnTile = false;

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

    /**
     * Permet d'obtenir l'unite civile qui est sur la tuile
     * @return l'unite civile qui est sur la tuile
     */
    public Civilian getCivilianOnTile() {
        return this.civilianOnTile;
    }

    /**
     * Permet d'obtenir l'unite militaire qui est sur la tuile
     * @return l'unite militaire qui est sur la tuile
     */
    public Military getMilitaryOnTile() {
        return this.miliratyOnTile;
    }

    /**
     * Permet de savoir si une unité civile est sur la tuile
     * @return boolean indiquant si une unité civile est sur la tuile
     */
    public boolean getIsCivilianUnitOnTile() {
        return this.isCivilianUnitOnTile;
    }

    /**
     * Permet de savoir si une unité militaire est sur la tuile
     * @return boolean indiquant si une unité militaire est sur la tuile
     */
    public boolean getIsMilitaryUnitOnTile() {
        return this.isMilitaryUnitOnTile;
    }

    /**
     * Set une unité militaire sur la tuille
     * @param unit Unité militaire
     */
    public void setMilitaryUnitOnTile(Military unit) {
        this.isMilitaryUnitOnTile = true;
        this.miliratyOnTile = unit;
    }
    /**
     * Set une unité civile sur la tuille
     * @param unit Unité civile
     */
    public void setCivilianUnitOnTile(Civilian unit) {
        this.isCivilianUnitOnTile = true;
        this.civilianOnTile = unit;
    }
}