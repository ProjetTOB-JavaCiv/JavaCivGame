package com.javaciv.type;

public enum CivilianUniteType {
    OUVRIER(0), COLON(1)

    int typeId = 0;

    CivilianUniteType(int i) {
        this.typeId = i;
    }

    public int toInt() {
        return typeId;
    }
}