package com.javaciv.server.unite;

import com.javaciv.server.unite.Unite;
import com.javaciv.server.unite.UniteType;

import com.javaciv.server.Tile;
import com.javaciv.server.WorldMap;

public abstract class MilitaryUnite /*implements Unite*/ {

    public UniteType type;

    public MilitaryUnite(UniteType type) {
        if (type.isCivilian()) {
            throw new IllegalArgumentException("MilitaryUnite cannot be civilian");
        }
        this.type = type;
    }
}
