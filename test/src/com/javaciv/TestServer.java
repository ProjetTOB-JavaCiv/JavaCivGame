package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.server.Server;
import com.javaciv.client.Client;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.WorldMap;
import java.util.Random;

class ServerTest {
    // Declaration des variables utiles
    Server server;
    Client player;

    @BeforeEach
    void setUp(){
        server = new Server();
        player = new Client(server);
    }

    @Test
    void getClientId() {
        assertEquals(server.getClientId(), 0);
    }

    @Test
    void getCulturepoint() {
        assertEquals(server.getCulturePoint(), 0);
    }

    @Test
    void getFaithPoint() {
        assertEquals(server.getFaithPoint(), 0);
    }

    @Test
    void getGoldPoint() {
        assertEquals(server.getGoldPoint(), 0);
    }

    @Test
    void getSciencePoint() {
        assertEquals(server.getSciencePoint(), 0);
    }

    @Test
    void getGoldPointProduction() {
        assertEquals(server.getGoldPointProduction(), 0);
    }

    @Test
    void getCulturePointProduction() {
        assertEquals(server.getCulturePointProduction(), 0);
    }

    @Test
    void getSciencePointProduction() {
        assertEquals(server.getSciencePointProduction(), 0);
    }

    @Test
    void getFaithPointProduction() {
        assertEquals(server.getFaithPointProduction(), 0);
    }

    @Test
    void getCities() {
        assert(server.getCities().isEmpty());
    }

    @Test
    void getUnites() {
        assert(server.getUnites().isEmpty());
    }

    @Test
    void createCity() {
        boolean created = false;
        int no = 0;
        int x, y;
        Tile tile= server.getWorldMap().at(0,0);
        while (!created & no<1) {
            Random rx = new Random();
            x = rx.nextInt((server.getWorldMap().getWidth()-1) + 1) + 1;
            Random ry = new Random();
            y = ry.nextInt((server.getWorldMap().getHeight()-1) + 1) + 1;
            tile = server.getWorldMap().at(x,y);
            created = server.createCity(tile);
            if (tile.getIsTraversableByLandUnit()) {
                assertEquals(created,true);
                no ++;
            } else {
                assertEquals(created, false);
            }
        }
        assertEquals(server.getCities().isEmpty(), false);
        assertEquals(server.getCities().size(), 1);
        // Cas où il y a déjà une ville sur la tuile
        assertEquals(server.createCity(tile), false);

    }

    @Test
    void nextTurn() {
        server.nextTurn();
        assertEquals(server.getClientId(), 0);
    }

    @Test
    void getClientCount() {
        assertEquals(server.getClientCount(), 1);
    }

    @Test
    void createClient() {
        Client player2 = new Client(server);
        assertEquals(server.getClientCount(), 2);
        assertEquals(server.getClientId(), 0);
        server.nextTurn();
        assertEquals(server.getClientId(), 1);
        server.nextTurn();
        assertEquals(server.getClientId(), 0);
    }


}