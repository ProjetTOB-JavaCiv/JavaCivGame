package com.javaciv.type;

/**
 * Cette énumération représente un type de terrain pour les tuiles
 */
public enum TechnologyID {
    MURAILLE(0), CATAPULTE(1);

    int technology = 0;

    TechnologyID(int i) {
        this.technology = i;
    }

}
