package com.javaciv.type;

public enum UniteType {
    LANCIER(0), CHEVALIER(1), ARCHER(2), CATAPULTE(3), 
    OUVRIER(4), COLON(5);

    int typeId = 0;

    UniteType(int i) {
        this.typeId = i;
    }
    
    public int toInt() {
        return typeId;
    }
}