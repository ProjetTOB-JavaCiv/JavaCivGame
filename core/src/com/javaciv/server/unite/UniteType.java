package com.javaciv.server.unite;

public enum UniteType {
    OUVRIER(0), COLON(1), LANCIER(2), CHEVALIER(3), ARCHER(4), CATAPULTE(5);

    int typeId = 0;

    UniteType(int i) {
        this.typeId = i;
    }

    public int toInt() {
        return typeId;
    }

    public boolean isMilitary() {
        return this == LANCIER 
            || this == CHEVALIER 
            || this == ARCHER 
            || this == CATAPULTE;
    }

    public boolean isCivilian() {
        return this == OUVRIER 
            || this == COLON;
    }
}