package com.javaciv.gameElement;

public class Infrastructure {
    
    /** Décrit le cout nécessaire en production pour produire le batiment */
    int coutConstructionBuild;

    /** Nombre de points de nourriture aporté par le batiment à une ville */
    int food;

    /** Nombre de points de culture aporté par le baitment à une ville */
    int culture;

    /** Nombre de points de foi aporté par la batiment à une ville */
    int faith;

    /** Nombre de points de science aporté par le batiment à une ville */
    int science;

    /** Nombre de points d'or aporté par le batiment à une ville */
    int gold;

    /*
    /** Constructeur de la classe Batiment
     * @param coutConstructionBuild : entier du cout nécessaire en production pour produire le batiment
     * @param nourriture : entier du nombre de points de nourriture aporté par le batiment à une ville
     * @param culture  :  entier du nombre de points de culture aporté par le baitment à une ville
     * @param foi : entier du nombre de points de foi aporté par la batiment à une ville
     * @param science : entier du nombre de points de science aporté par le batiment à une ville
     * @param or : entier du nombre de points d'or aporté par le batiment à une ville
     *
     *
    public void Batiment( int coutConstructionBuild, int nourriture, int culture,
                          int foi, int science, int or) {
        this.coutConstructionBuild = coutConstructionBuild;
        this.nourriture = nourriture;
        this.culture = culture;
        this.foi = foi;
        this.or = or;
        this.science = science;
    }
    */

    /** Permet d'obtenir le cout de construction du batiment
     * @return le cout de construction du batiment
     */
    public int getCoutConstructionBuild() {
        return this.coutConstructionBuild;
    }

    /** Permet d'obtenir le nombre de points de culture aporté par le batiment à une ville
     * @return le nombre de points de culture aporté par le batiment à la ville
     */
    public int getCulture() {
        return this.culture;
    }

    /** Permet d'obtenir le nombre de points de nourriture aporté par le batiment à une ville
     * @return le nombre de points de nourriture aporté par le batiment à la ville
     */
    public int getFood() {
        return this.food;
    }

    /** Permet d'obtenir le nombre de points de foi aporté par le batiment à une ville
     * @return le nombre de points de foi aporté par le batiment à la ville
     */
    public int getFaith() {
        return this.faith;
    }

    /** Permet d'obtenir le nombre de points d'or aporté par le batiment à une ville
     * @return le nombre de point de foi aporté par le batiment à une ville*/
    public int getGold() {
        return this.gold;
    }

    /** Permet d'obtenir le nombre de points de science aporté par le batiment à une ville
     * @return le nombre de points de science aporté par le batiment à la ville
     */
    public int getScience() {
        return this.science;
    }
}
