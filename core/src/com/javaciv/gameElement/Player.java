package com.javaciv.gameElement;

import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe représente un dirigeant d'une civilisation, le dirigeant est
 * d'une civilisation est capable possède des villes et des unités.
 * De plus il possède des bonus qui sont propre à lui même.
 * Ex : Attila possède un bonus de dégat pour la cavalerie.
 */
public class Player {
    
    /** Nom de la civilisation */
    String name;
    /** Nombre d'or de la civilisation */
    int goldPoint;
    /** Nombre de point de culture de la civilisation */
    int culturePoint = 0;
    /** Nombre de point de science de la civilisation */
    int sciencePoint = 0;
    /** Nombre de point de foi de la civilisation */
    int faithPoint = 0;

    /** Liste comprenant l'ensemble des villes d'un joueur */
    List<City> cities = new ArrayList<City>();

    /** Liste comprenant l'ensemble des unités d'un joueur */
    List<Unite> units = new ArrayList<Unite>();

    /**
     * Constructeur d'un joueur, on commence la partie initialement avec un
     * certain nombre de gold et le reste des points à 0.
     * @param name nom du joueur
     */
    public Player(String name) {
        this.name = name;
        this.goldPoint = 10;
    }

    /** 
     * Méthode actualisant le nombre de point de culture du joueur.
     * Sachant que les villes produissent la culture, on fait la somme
     * de l'ensemble des production de culture des villes pour connaitre
     * la quantité de culture par tour d'une civilisation.
     */ 

    public String getName() {
        return this.name;
    }

    public int getCulturePoint() {
        return this.culturePoint;
    }

    public int getFaithPoint() {
        return this.faithPoint;
    }

    public int getGoldPoint() {
        return this.goldPoint;
    }

    public int getSciencePoint() {
        return this.sciencePoint;
    }
}
