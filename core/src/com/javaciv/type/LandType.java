package com.javaciv.type;

/**
 * Cette énumération représente un type de terrain pour les tuiles
 */
public enum LandType {
    PLAINE(0), DESERT(1), FORET(2), MONTAGNE(3), MER(4), COLLINE(5);

    int land = 0;

    LandType(int i) {
        this.land = i;
    }

    public int toInt() {
        return land;
    }

}
