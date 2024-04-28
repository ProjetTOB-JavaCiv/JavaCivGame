package com.javaciv.server.unite;

import com.javaciv.server.unite.Unite;
import com.javaciv.server.unite.UniteType;

import com.javaciv.server.Tile;
import com.javaciv.server.WorldMap;

public abstract class CivilianUnite implements Unite {

    public UniteType type;

    public Tile position;

    public CivilianUnite(UniteType type, Tile position) {
        if (type.isMilitary()) {
            throw new IllegalArgumentException("CivilianUnite cannot be military");
        }
        this.type = type;
        this.position = position;
    }

    public Tile getPosition() {
        return this.position;
    }

    public void changePosition(WorldMap map, Tile destination) {
        this.position = destination;
    }
}