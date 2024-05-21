package com.javaciv.type;

public class ProductionType {

     // Attributs
    /** Ressource de construction */
    private int production = 0;
    /** Ressource de technologie */
    private int science = 0;
    /** Ressource de religion */
    private int faith = 0;
    /** ressource de food */
    private int food = 0;
    /** ressource de culture */
    private int culture = 0;
    /** ressource d'gold */
    private int gold = 0;

    // Constructeurs

    /**
     * Constructeur d'un objet Production.
     *
     * @param food la ressource de nourriture
     * @param faith la ressource de religion
     * @param culture la ressource de culture
     * @param science la ressource de technologie
     * @param gold la ressource d'or
     * @param production la ressource de construction
     */
    public ProductionType(int food, int faith, int culture, int science, int gold, int production) {
        this.production = production;
        this.science = science;
        this.faith = faith;
        this.food = food;
        this.culture = culture;
        this.gold = gold;
    }


    // Getters
    /**
     * Obtenir la valeur de matériel.
     * @return un entier, la valeur de matériel.
     */
    public int getProduction() {
        return this.production;
    }

    /**
     * La valeur de science.
     * @return un entier, la valeur de science.
     */
    public int getScience() {
        return this.science;
    }

    /**
     * La valeur de faith.
     * @return un entier, la valeur de faith.
     */
    public int getFaith() {
        return this.faith;
    }

    /**
     * Obtenir la valeur de food.
     * @return un entier, la valeur de food.
     */
    public int getFood() {
        return this.food;
    }

    /**
     * La valeur de culture.
     * @return un entier, la valeur de culture.
     */
    public int getCulture() {
        return this.culture;
    }

    /**
     * La valeur d'gold'.
     * @return un entier, la valeur d'gold'.
     */
    public int getGold() {
        return this.gold;
    }

    // Méthodes
    /**
     * Permet d'ajouter 2 productions et de stocker le resultat dans un 3e objet.
     * @param p1 production 1
     * @param p2 production 2
     * @return un objet contenant la somme des productions p1 et p2
     */
    public static ProductionType add(ProductionType p1, ProductionType p2) {
        return new ProductionType(p1.food + p2.food,
                                  p1.faith + p2.faith, 
                                  p1.culture + p2.culture, 
                                  p1.science + p2.science, 
                                  p1.gold + p2.gold, 
                                  p1.production + p2.production);
    }

    /**
     * Permet d'ajouter 3 productions et de stocker le resultat dans un 3e objet.
     * @param p1 production 1
     * @param p2 production 2
     * @param p3 production 3
     * @return un objet contenant la somme des productions p1, p2, et p3
     */
    public static ProductionType add(ProductionType p1, ProductionType p2, ProductionType p3) {
        return new ProductionType(p1.food + p2.food + p3.food,
                                  p1.faith + p2.faith + p3.faith, 
                                  p1.culture + p2.culture +  p3.culture, 
                                  p1.science + p2.science + p3.science, 
                                  p1.gold + p2.gold + p3.gold, 
                                  p1.production + p2.production + p3.production);
    }

    /**
     * Permet de vérifier que 2 productions sont égales.
     * @param p production
     * @return un booléen, vrai si les attributs de this et p ont la même valeur, faux sinon.
     */
    public boolean estEgale(ProductionType p) {
        return this.production == p.getProduction() && this.food == p.getFood() && this.faith == p.getFaith()
                && this.science == p.getScience() && this.culture == p.getCulture() && this.gold == p.getGold();
    }
} 
