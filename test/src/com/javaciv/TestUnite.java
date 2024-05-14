package com.javaciv;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.javaciv.builder.HashMapUnit;
import com.javaciv.type.UniteType;
import com.javaciv.type.LandType;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.Military;
import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Player;



public class TestUnite {
   
    // Declaration des variables utiles
    Military military;
    Civilian civilian;
    Player player1, player2;
    HashMap<UniteType, Military> map;
    LandType nature;
   
    @BeforeEach
    void setUp() {
        player1 = new Player("Joueur1");
        player2 = new Player("Joueur2");
        military = new Military("militaire", player1, 11, 22, 3, 100, 5);
        civilian = new Civilian("civil", player2, 10, 20);
    }

    @Test
        //Test juste si l'execution de buildHashMapMilitary fonctionne, à finir.
    void testBuilder() {
        Military unit = HashMapUnit.getMilitary(UniteType.ARCHER);
    }

    @Test
    void getName() {
        assertEquals("militaire", military.getName());
        assertEquals("civil", civilian.getName());
    }

    @Test
    void getOwner() {
        assertEquals(military.getOwner(), player1);
        assertEquals(civilian.getOwner(), player2);
    }

    @Test
    void getPosition() {
        assertEquals(military.getPosition().getX(), 0);
        assertEquals(military.getPosition().getY(), 0);
        assertEquals(military.getPosition().getLand(), LandType.PLAINE);
        assertEquals(civilian.getPosition().getX(), 0);
        assertEquals(civilian.getPosition().getY(), 0);
        assertEquals(civilian.getPosition().getLand(), LandType.PLAINE);
    }

    @Test
    void getCost() {
        assertEquals(military.getCost(), 22);
        assertEquals(civilian.getCost(), 20);
    }


    @Test
    void getActionPoint() {
        assertEquals(military.getActionPoint(), 11);
        assertEquals(civilian.getActionPoint(), 10);
    }

    @Test
    void getBaseActionPoint() {
        assertEquals(military.getActionPoint(), 11);
        assertEquals(civilian.getBaseActionPoint(), 10);
    }

    @Test
    void getAttack() {
        assertEquals(military.getAttack(), 3);
    }

    @Test
    void getPV() {
        assertEquals(military.getPV(), 100);
    }

    @Test
    void getDefense() {
        assertEquals(military.getDefense(), 5);
    }

    @Test
    void getCalculMovement() {
        // TO DO
    }

    @Test
    void seDeplacer() {
        // TO DO
    }

    @Test
    void getNatureDeplacement() {
        // TO DO
    }

    @Test
    void getPossibleMovement() {
        // TO DO
    }

}
