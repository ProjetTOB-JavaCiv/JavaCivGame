package com.javaciv.server;

/**
 * Cette énumération représente un type de terrain pour les tuiles
 */
public enum Terrain {
    TERRESTRE(0), MARITIME(1), INFRANCHISSABLE(2);

    int terrain = 0;

    Terrain(int i) {
        this.terrain = i;
    }

    public int toInt() {
        return terrain;
    }
}
