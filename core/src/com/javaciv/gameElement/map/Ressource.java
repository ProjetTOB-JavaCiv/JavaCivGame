package com.javaciv.gameElement.map;

import com.javaciv.type.ProductionType;
import com.javaciv.type.RessourceType;

public class Ressource {
    /** Type de la ressource */
    private final RessourceType ressourceType;
    /** production de la ressource */
    private final ProductionType production;

    /**
     * Constructeur de la classe Ressource
     * @param ressourceType le type de la ressource
     * @param production la production de la ressource
     */
    public Ressource(RessourceType ressourceType, ProductionType production) {
        this.ressourceType = ressourceType;
        this.production = production;
    }

    /**
     * Permet d'obtenir le type de la ressource
     * @return le type de la ressource
     */
    public RessourceType getRessourceType() {
        return this.ressourceType;
    }

    /**
     * Permet d'obtenir la production de la ressource
     * @return la production de la ressource
     */
    public ProductionType getProduction() {
        return this.production;
    }
}
