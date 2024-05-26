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

    /**
     * Constructeur de la classe Feature
     * @param featureType le type de caracteristique
     * @param production la production de la caracteristique
     * @param movementModifier le modificateur de deplacement
     * @param fightModifier le modificateur de combat
     */
    public Feature(FeatureType featureType, ProductionType production, int movementModifier, int fightModifier) {
        //Assignation des valeurs de la caracteristique
        this.featureType = featureType;
        this.production = production;
        this.movementModifier = movementModifier;
        this.fightModifier = fightModifier;
    }

    /**
     * Permet d'obtenir le type de caracteristique
     * @return le type de caracteristique
     */
    public FeatureType getFeatureType() {
        return featureType;
    }

    /**
     * Permet d'obtenir la production de la caracteristique
     * @return la production de la caracteristique
     */
    public ProductionType getProduction() {
        return production;
    }

    /**
     * Permet d'obtenir le modificateur de deplacement
     * @return le modificateur de deplacement
     */
    public int getMovementModifier() {
        return movementModifier;
    }

    /**
     * Permet d'obtenir le modificateur de combat
     * @return le modificateur de combat
     */
    public int getFightModifier() {
        return fightModifier;
    }
}
