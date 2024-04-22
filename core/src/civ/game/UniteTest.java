package civ.game;

import civ.game.tuile.Contenu;
import civ.game.tuile.Terrain;
import civ.game.tuile.contenu.caracteristique.Marais;
import civ.game.tuile.terrain.Lac;
import civ.game.tuile.terrain.Nature;
import civ.game.unite.classeUnite.terrestre.antiCavalerie.Lancier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniteTest {

    // Declaration des variables utiles
    Unite lancier;
    Joueur joueur;
    Tuile tuile;
    Contenu marais;
    Terrain lac;


    @BeforeEach
    void setUp() {
        marais = new Marais();
        lac = new Lac();
        joueur = new Joueur("Joueur", null ); // A modifier quand Personnage sera implémentée
        tuile = new Tuile(1,1, marais, lac);
        lancier = new Lancier("lancier", tuile, joueur);
    }

    @Test
    void getNom() {
        assertEquals("lancier", lancier.getNom());
    }

    @Test
    void getProprietaire() {
        assertEquals(lancier.getProprietaire(), joueur);
    }

    @Test
    void getPosition() {
        assertEquals(lancier.getPosition(), tuile);
    }

    @Test
    void getCout() {
        assertEquals(lancier.getCout(), 65);
    }

    @Test
    void getPV() {
        assertEquals(lancier.getPV(), 100);
    }

    @Test
    void getPm() {
        assertEquals(lancier.getPm(), 2);
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
        assertEquals(lancier.getPm(), 2);
        assertFalse(lancier.getIndicateurDeplacement());
    }

    @Test
    void getNatureDeplacement() {
        assertEquals(lancier.getNatureDeplacement(), Nature.TERRESTRE);
    }
}