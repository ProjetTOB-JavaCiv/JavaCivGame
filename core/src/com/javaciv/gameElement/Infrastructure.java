package com.javaciv.gameElement;

public class Infrastructure {
    
    /** Décrit le cout nécessaire en production pour produire le batiment */
    int productionCost;
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
    /** Nombre de points de production aproté par le batiment à une ville */
    int production;


    /** Constructeur de la classe Infrastructure
     * @param productionCost : entier du cout nécessaire en production pour produire le batiment
     * @param food : entier du nombre de points de nourriture aporté par le batiment à une ville
     * @param culture  :  entier du nombre de points de culture aporté par le baitment à une ville
     * @param faith : entier du nombre de points de foi aporté par la batiment à une ville
     * @param science : entier du nombre de points de science aporté par le batiment à une ville
     * @param gold : entier du nombre de points d'or aporté par le batiment à une ville
     * @param production : entier du nombre de point de production aproté par le batiment à une ville
     *
     *
    */
    public Infrastructure( int productionCost, int food, int culture, int faith, int science, int gold, int production) {
        this.productionCost = productionCost;
        this.food = food;
        this.culture = culture;
        this.faith = faith;
        this.gold = gold;
        this.science = science;
        this.production = production;
    }

    /** Permet d'obtenir le cout de construction du batiment
     * @return le cout de construction du batiment
     */
    public int getProductionCost() {
        return this.productionCost;
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
     * @return le nombre de point de foi aporté par le batiment à une ville
     * */
    public int getGold() {
        return this.gold;
    }

    /** Permet d'obtenir le nombre de points de science aporté par le batiment à une ville
     * @return le nombre de points de science aporté par le batiment à la ville
     */
    public int getScience() {
        return this.science;
    }

    /** Permet d'obtenir le nombre de points de production aporté par le batiment à une ville
     * @return le nombre de point de production aporté par le batiment à une ville
     */
    public int getProduction() {
        return this.production;
    }
}
