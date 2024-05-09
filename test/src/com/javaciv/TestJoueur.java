package com.javaciv;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.gameElement.Player;

class JoueurTest {
    // Declaration des variables utiles
    Player player;

    @BeforeEach
    void setUp(){
        player = new Player("joueur");
    }

    @Test
    void getNom() {
        assertEquals(player.getName(), "joueur");
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
        assertEquals(player.getGoldPoint(), 10);
    }

    @Test
    void getSciencePoint() {
        assertEquals(player.getSciencePoint(), 0);
    }

}
