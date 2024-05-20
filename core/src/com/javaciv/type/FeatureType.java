package com.javaciv.type;

/**
 * Cette énumération représente un type de terrain pour les tuiles
 */
public enum FeatureType {
    BASE(0), WOODS(1), MARSH(2), OASIS(3), RAINFOREST(4);

    int featureType = 0;
    
    FeatureType(int i) {
        this.featureType = i;
    }

    int toInt() {
        return featureType;
    }

}