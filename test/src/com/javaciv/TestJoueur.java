package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.server.Server;
import com.javaciv.client.Client;

class JoueurTest {
    // Declaration des variables utiles
    Server server;
    Client player;

    @BeforeEach
    void setUp(){
        server = new Server();
        player = new Client(server);
        player.nextTurn();
    }

    @Test
    void getId() {
        assertEquals(player.getClientId(), server.getClientId());
    }

    @Test
    void getCulturepoint() {
        assertEquals(player.getCulturePoint(), 2);
    }

    @Test
    void getFaithPoint() {
        assertEquals(player.getFaithPoint(), 12);
    }

    @Test
    void getGoldPoint() {
        assertEquals(player.getGoldPoint(), 10);
    }

    @Test
    void getSciencePoint() {
        assertEquals(player.getSciencePoint(), 4);
    }

}
