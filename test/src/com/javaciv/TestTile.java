package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.builder.HashMapUnitBuilder;
import com.javaciv.type.UniteType;
import com.javaciv.gameElement.Unite;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.Terrain;
import com.javaciv.gameElement.Military;
import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Player;

class TestTile {

    // Declaration des variables utiles
    Tile tile;
    Military military;
    Civilian civilian;
    Player player;

    @BeforeEach
    void setUp(){
        tile = new Tile(2, 59, Terrain.TERRESTRE);
        player = new Player("joueur");
        military = new Military("militaire", player, 10, 20, 3, 100, 5);
        civilian = new Civilian("Civilian", player, 10, 20);
    }

    @Test
    void testGetX(){
        assertEquals(tile.getX(), 2);
    }

    @Test
    void testGetY(){
        assertEquals(tile.getY(), 59);
    }

    @Test
    void testGetTerrain(){
        assertEquals(tile.getTerrain(), Terrain.TERRESTRE);
    }

    @Test
    void testGetIsMilitaryOnTile(){
        assertEquals(tile.getIsMilitaryUnitOnTile(), false);
    }

    @Test
    void testGetIsCivilianOnTile(){
        assertEquals(tile.getIsCivilianUnitOnTile(), false);
    }

    @Test
    void testSetMilitaryOnTile(){
        tile.setMilitaryUnitOnTile(military);
        assertEquals(tile.getIsMilitaryUnitOnTile(), true);
    }

    @Test
    void testSetCivilianOnTile(){
        tile.setCivilianUnitOnTile(civilian);
        assertEquals(tile.getIsCivilianUnitOnTile(), true);
    }

    @Test
    void testGetMilitaryOnTile(){
        tile.setMilitaryUnitOnTile(military);
        assertEquals(tile.getMilitaryOnTile(), military);
    }

    @Test
    void testGetCivilianOnTile(){
        tile.setCivilianUnitOnTile(civilian);
        assertEquals(tile.getCivilianOnTile(), civilian);
    }
    
}