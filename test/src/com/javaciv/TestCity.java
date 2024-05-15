package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.javaciv.builder.HashMapLand;

import com.javaciv.type.UniteType;
import com.javaciv.type.LandType;
import com.javaciv.gameElement.Unite;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.City;
import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.server.Server;
import com.javaciv.client.Client;

class TestCity {
    // Declaration des variables utiles
    City city;
    Tile tile;
    WorldMap worldMap = new WorldMap(100, 100);
    Server server = new Server();
    Client player = new Client(server);

    @BeforeEach
    void setUp(){
        tile = HashMapLand.getLand(LandType.PLAINE);
        tile.setX(2);
        tile.setY(59);

        city = new City(tile, worldMap, player);
    }

    @Test
    void getpopulation() {
        assertEquals(city.getPopulation(), 1);
    }

    @Test
    void refreshPopulation() {
        city.refreshPopulation();
        assertEquals(city.getPopulation(), 1);
    }

}