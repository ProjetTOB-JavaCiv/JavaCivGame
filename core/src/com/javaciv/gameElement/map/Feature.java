package com.javaciv.gameElement.map;

import com.javaciv.type.FeatureType;
import com.javaciv.type.ProductionType;

public final class Feature {
    
    /** Le type de caracteristique */
    final FeatureType featureType;
    /** Production de la caracteristique */
    final ProductionType production;
    /** Modificateur de deplacement */
    final int movementModifier;
    /** Modificateur de combat */
    final int fightModifier;

    public Feature(FeatureType featureType, ProductionType production, int movementModifier, int fightModifier) {
        //Assignation des valeurs de la caracteristique
        this.featureType = featureType;
        this.production = production;
        this.movementModifier = movementModifier;
        this.fightModifier = fightModifier;
    }

    public FeatureType getFeatureType() {
        return featureType;
    }

    public ProductionType getProduction() {
        return production;
    }

    public int getMovementModifier() {
        return movementModifier;
    }

    public int getFightModifier() {
        return fightModifier;
    }
}
