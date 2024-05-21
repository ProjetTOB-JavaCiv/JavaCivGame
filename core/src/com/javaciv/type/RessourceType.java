package com.javaciv.type;

/**
 * Cette énumération représente les ressources disponible sur les tuiles
 */
public enum RessourceType {
    BASE(0), BETAIL(1), BLE(2), CERVIDE(3), CRABE(4), CACAO(5);

    int ressourceType;

    RessourceType(int i) {
        this.ressourceType = i;
    }

    int toInt() {
        return this.ressourceType;
    }
}
