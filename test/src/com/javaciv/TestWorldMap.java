package com.javaciv;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.HashMap;
import java.util.HashSet;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.WorldMap;

public class TestWorldMap {
    public WorldMap map;

    @BeforeEach
    void initMap() {
        map = new WorldMap(10, 10);
    }

    @Test
    void at(){
        Tile tile = map.at(1,2);
        assertEquals(tile.getX(), 1);
        assertEquals(tile.getY(), 2);
    }

    @Test
    void getHeight(){
        assertEquals(map.getHeight(), 10);
    }

    @Test
    void getWidth(){
        assertEquals(map.getWidth(), 10);
    }

    @Test
    void nextTo(){
        HashSet<Tile> voisins = map.nextTo(1,1);
        assert(voisins.contains(map.at(1,2)));
        assert(voisins.contains(map.at(2,1)));
        assert(!voisins.contains(map.at(1,9)));
        assert(!voisins.contains(map.at(1,1)));
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
