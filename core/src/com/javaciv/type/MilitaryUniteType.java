package com.javaciv.type;

public enum MilitaryUniteType {
    LANCIER(0), CHEVALIER(1), ARCHER(2), CATAPULTE(3);

    int typeId = 0;

    MilitaryUniteType(int i) {
        this.typeId = i;
    }

    public int toInt() {
        return typeId;
    }
}
