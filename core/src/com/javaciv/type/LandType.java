package com.javaciv.type;

/**
 * Cette énumération représente un type de terrain pour les tuiles
 */
public enum LandType {
    PLAINE(0), DESERT(1), PRAIRIE(2), MONTAGNE(3), TOUNDRA(4), EAU(5);

    int land = 0;

    LandType(int i) {
        this.land = i;
    }

    public int toInt() {
        return land;
    }

    public String toFirstLetter() {
        return this.toString().substring(0, 1);
    }
}
