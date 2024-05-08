package com.javaciv;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import com.javaciv.builder.HashMapUnitBuilder;
import com.javaciv.type.UniteType;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.Terrain;
import com.javaciv.gameElement.Military;
import com.javaciv.gameElement.Player;



public class TestUnite {
   
    // Declaration des variables utiles
    Military lancier, lancier2;
    Player player;
    HashMap<UniteType, Military> map;
    Tile tile;
    Terrain nature;
   
    @Test
    //Test juste si l'execution de buildHashMapMilitary fonctionne, à finir.
    void testBuilder() {
        map = HashMapUnitBuilder.buildHashMapMilitary();
    }

    // Declaration des variables utiles

    /* 
    @BeforeEach
    void setUp() {
        marais = new Marais();
        lac = new Lac();
        joueur = new Joueur("Joueur", null ); // A modifier quand Personnage sera implémentée
        tuile = new Tuile(1,1, marais, lac);
        lancier = new Lancier("lancier", tuile, joueur);
    } */

    @Test
    void getName() {
        assertEquals("lancier", lancier.getName());
    }

    @Test
    void getOwner() {
        assertEquals(lancier.getOwner(), player);
    }

    @Test
    void getPosition() {
        assertEquals(lancier.getPosition(), tile);
    }

    @Test
    void getCost() {
        assertEquals(lancier.getCost(), 65);
    }

    @Test
    void getPV() {
        assertEquals(lancier.getPV(), 100);
    }

    @Test
    void getActionPoint() {
        assertEquals(lancier.getActionPoint(), 2);
    }

    @Test
    void seDeplacer() {
        // TO DO
    }

    @Test
    void getDeplacementPossible() {
        // TO DO
    }

    @Test
    void calculDeplacement() {
        // TO DO
    }

    @Test
    void debutTour() {
        assertEquals(lancier.getActionPoint(), 2);
        //assertFalse(lancier.getIndicateurDeplacement());
    }

    @Test
    void getNatureDeplacement() {
        assertEquals(lancier.getNatureDeplacement(), nature.TERRESTRE);
    }
}
