package com.javaciv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.javaciv.server.WorldMap;

public class TestWorldMap {
    public WorldMap map;

    @BeforeEach
    void initMap() {
        this.map = new WorldMap(10, 10);
    }

    @Test
    void printMap() {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                map.at(i, j).print();
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
