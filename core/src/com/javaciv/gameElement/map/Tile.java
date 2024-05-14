package com.javaciv.gameElement.map;

import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Military;
import com.javaciv.type.LandType;
import com.javaciv.gameElement.Player;

/**
 * Cette classe représente une tuile.
 * Une tuile correspond à une case de la carte du monde,
 * elle contient des informations sur ce qui se trouve dessus
 * ainsi que du type de terrain qu'elle représente
 */
public class Tile {
    /** La position x de la tuile;
     */
    private int x;
    /** La position y de la tuile.
     */
    private int y;
    /** Le type de terrain de la tuile.
    */
    private LandType land;

    /** Propriétaire de la case, null si la case est occupé par personne */
    Player owner = null;

    /** Nombre de points de nourriture de la tuile */
    int food;
    /** Nombre de points de culture de la tuile */
    int culture;
    /** Nombre de points de foi de la tuile */
    int faith;
    /** Nombre de points de science de la tuile */
    int science;
    /** Nombre de points d'or de la tuille */
    int gold;
    /** Nombre de points de production de la tuile */
    int production;

    /** Flotant indicateur de la valeur stratégique d'une tuile */
    double strategicValue;

    /** Booléen décrivant si la tuile est traversable par une unité terrestre
     * Ceci ne peut changer car c'est une propriété géographique du terrain
     */
    final boolean isTraversableByLandUnit;
    /** Booléen décrivant si la tuile est traversable par une unité maritime
     * Ceci ne peut changer car c'est une propriété géographique du terrain
     */
    final boolean isTraversableBySeaUnit;

    /** Variable stockant l'unité militaire étant sur la case.
     * Il n'est pas obligatoire pour une tuile d'avoir une unité militaire dessus
    */
    private Military miliratyOnTile;
    /** Variable stockant l'unité civile étant sur la case.
     * Il n'est pas obligatoire pour une tuile d'avoir une unité militaire dessus
    */
    private Civilian civilianOnTile;

    /** Booléen qui décrit si une tuile est occupé par une unité militaire
     */
    private boolean isMilitaryUnitOnTile = false;

    /** Booléen qui décrit si une tuile est occupé par une unité civile
     */
    private boolean isCivilianUnitOnTile = false;

    /**
     * Constructeur d'une tuile.
     *
     * @param x position x de la tuile
     * @param y position y de la tuile
     * @param terrain type de terrain de la tuile
     */
    public Tile(int x, int y, LandType land,
    boolean isTraversableByLandUnit, boolean isTraversableBySeaUnit, 
    int food, int culture, int faith, int science, int gold, int production, double baseLandValue) {
        this.x = x;
        this.y = y;
        this.land = land;
        this.isTraversableByLandUnit = isTraversableByLandUnit;
        this.isTraversableBySeaUnit = isTraversableBySeaUnit;

        //Assignation des valeurs de la tuile en terme de points de ressources
        this.food = food;
        this.culture = culture;
        this.faith = faith;
        this.science = science;
        this.gold = gold;
        this.production = production;

        //Assignation de la valeur stratégique de la tuile TODO : Ajouter de la valeur si y'a une ressource stratégique
        this.strategicValue = baseLandValue;
    }

    /**
     * Calcule la distance entre deux tuiles.
     * @param other l'autre tuile
     * @return la distance entre les deux tuiles
     */
    public float distance(Tile other) {
        return (float) Math.sqrt(
            Math.pow(this.getX() - other.getX(), 2) +
            Math.pow(this.getY() - other.getY(), 2)
        );
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

    public int getFood() {
        return this.food;
    }

    public int getCulture() {
        return this.culture;
    }

    public int getScience() {
        return this.science;
    }

    public int getFaith() {
        return this.faith;
    }

    public int getGold() {
        return this.gold;
    }

    public int getProduction() {
        return this.production;
    }

    /** Permet de recuperer le terrain de la tuile
     * @return le terrain de la tuile
     */
    public LandType getLand() {
        return this.land;
    }

    public void print() {
        System.out.print("[" + this.x + "," + this.y + "|" + this.land.toInt() + "]");
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

    public boolean getIsTraversableByLandUnit() {
        return this.isTraversableByLandUnit;
    }

    public boolean getIsTraversableBySeaUnit() {
        return this.isTraversableBySeaUnit;
    }

    public double getBaseLandValue() {
        return this.strategicValue;
    }

    /** Renvoie le propriétaire de la case */
    public Player getOwner() {
        return this.owner;
    }

    /** Permet de changer de position une tuille sur l'axe X, est utile UNIQUEMENT lors de la création de
     * la map, après cela, la position d'une tuile ne doit pas changer
     * TODO : Changer le degré de liberté de cette méthode ??
     */
    public void setX(int x) {
        this.x = x;
    }

    /** Permet de changer de position une tuille sur l'axe Y, est utile UNIQUEMENT lors de la création de
     * la map, après cela, la position d'une tuile ne doit pas changer  */
    public void setY(int y) {
        this.y = y;
    }

    /** Permet de changer le terrain d'une tuile, est utile UNIQUEMENT lors de la création de
     * la map, après cela, le terrain d'une tuile ne doit pas changer  */
    public void setLand(LandType land) {
        this.land = land;
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

    /** Set la propriété d'un joueur sur une tuille 
     * @param owner la civilisation qui va posséder la tuille
    */
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}