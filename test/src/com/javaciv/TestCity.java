package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.type.UniteType;
import com.javaciv.gameElement.Unite;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.Terrain;
import com.javaciv.gameElement.Military;
import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Player;
import com.javaciv.gameElement.City;

class TestCity {
    // Declaration des variables utiles
    City city;
    Tile tile;

    @BeforeEach
    void setUp(){
        tile = new Tile(2, 59, Terrain.TERRESTRE);
        city = new City(tile);
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