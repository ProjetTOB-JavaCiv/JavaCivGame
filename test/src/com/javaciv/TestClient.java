package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.server.Server;
import com.javaciv.client.Client;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.WorldMap;
import java.util.Random;

class ClientTest {
    // Declaration des variables utiles
    Server server;
    Client player;
    Client player2;

    @BeforeEach
    void setUp(){
        server = new Server();
        player = new Client(server);
        player2 = new Client(server);
    }

    @Test
    void getId() {
        assertEquals(player.getClientId(), 0);
        assertEquals(player2.getClientId(), 1);
    }

    @Test
    void getWorldMap() {
        assertEquals(player.getWorldMap(), player2.getWorldMap());
    }

    @Test
    void canPassTurn() {
        assertEquals(player2.canPassTurn(), false);
        assertEquals(player.canPassTurn(), true);
    }

    @Test
    void nextTurn() {
        player.nextTurn();
        assertEquals(player.canPassTurn(), false);
        assertEquals(player2.canPassTurn(), true);
    }

    @Test
    void getCulturepoint() {
        assertEquals(player.getCulturePoint(), 0);
    }

    @Test
    void getFaithPoint() {
        assertEquals(player.getFaithPoint(), 0);
    }

    @Test
    void getGoldPoint() {
        assertEquals(player.getGoldPoint(), 0);
    }

    @Test
    void getSciencePoint() {
        assertEquals(player.getSciencePoint(), 0);
    }

    @Test
    void getGoldPointProduction() {
        assertEquals(player.getGoldPointProduction(), 0);
    }

    @Test
    void getCulturePointProduction() {
        assertEquals(player.getCulturePointProduction(), 0);
    }

    @Test
    void getSciencePointProduction() {
        assertEquals(player.getSciencePointProduction(), 0);
    }

    @Test
    void getFaithPointProduction() {
        assertEquals(player.getFaithPointProduction(), 0);
    }

    @Test
    void getCities() {
        assert(player.getCities().isEmpty());
    }

    @Test
    void getUnites() {
        assert(player.getUnites().isEmpty());
    }

    @Test
    void createCity() {
        boolean created = false;
        int x, y;
        while (!created) {
            Random rx = new Random();
            x = rx.nextInt((player.getWorldMap().getWidth()-1) + 1);
            Random ry = new Random();
            y = ry.nextInt((player.getWorldMap().getHeight()-1) + 1);
            Tile tile = player.getWorldMap().at(x,y);
            created = player.createCity(tile);
            if (tile.getIsTraversableByLandUnit()) {
                assertEquals(created,true);
            } else {
                assertEquals(created, false);
            }
        }
        assertEquals(player.getCities().isEmpty(), false);
    }

}
