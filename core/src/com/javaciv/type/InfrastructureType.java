package com.javaciv.type;

public enum InfrastructureType {
    GRENIER(0), MONUMMENT_HISTORIQUE(1), MURAILLE(2), ATELIER(3); // etc, on ajoute le reste plus tard.

    int typeId = 0;
    
    InfrastructureType(int i) {
        this.typeId = i;
    }

    public int toInt() {
        return typeId;
    }
}
