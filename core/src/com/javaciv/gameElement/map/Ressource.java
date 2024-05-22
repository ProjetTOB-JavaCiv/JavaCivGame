package com.javaciv.gameElement.map;

import com.javaciv.type.ProductionType;
import com.javaciv.type.RessourceType;

public class Ressource {
    /** Type de la ressource */
    private final RessourceType ressourceType;
    /** production de la ressource */
    private final ProductionType production;

    public Ressource(RessourceType ressourceType, ProductionType production) {
        this.ressourceType = ressourceType;
        this.production = production;
    }

    public RessourceType getRessourceType() {
        return this.ressourceType;
    }

    public ProductionType getProduction() {
        return this.production;
    }
}
