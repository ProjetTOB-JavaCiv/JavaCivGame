package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.builder.HashMapUnit;
import com.javaciv.builder.HashMapLand;

import com.javaciv.type.UniteType;
import com.javaciv.type.LandType;
import com.javaciv.gameElement.Unite;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.Military;
import com.javaciv.gameElement.Civilian;
import com.javaciv.server.Server;
import com.javaciv.client.Client;

class TestTile {

    // Declaration des variables utiles
    Tile tile;
    Military military;
    Civilian civilian;
    Server server;
    Client player;

    @BeforeEach
    void setUp(){
        tile = HashMapLand.getLand(LandType.PLAINE);
        tile.setX(2);
        tile.setY(59);

        server = new Server();
        player = new Client(server);
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
    void testSetX(){
        tile.setX(5);
        assertEquals(tile.getX(), 5);
    }

    @Test
    void testSetY(){
        tile.setY(1);
        assertEquals(tile.getY(), 1);
    }


    @Test
    void testGetLand(){
        assertEquals(tile.getLand(), LandType.PLAINE);
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

    @Test
    void getOwner() {
        assertEquals(tile.getOwner(), null);
    }

    @Test
    void setOwner() {
        tile.setOwner(player);
        assertEquals(tile.getOwner(), player);
    }
    
}