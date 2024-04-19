package civ.game;
import civ.game.tuile.contenu.caracteristique.Base;
import civ.game.tuile.Contenu;
import civ.game.tuile.Terrain;
import civ.game.tuile.contenu.caracteristique.Bois;
import civ.game.tuile.terrain.Ocean;

import org.junit.*;
import org.junit.Assert.*;

/**
 * Classe de tests unitaires TestTuile permettant de tester la classe Tuile.
 * @author Elisa Ciocarlan
 * @version 0.0
 */
public class TuileTest {
    Tuile tuileSimple;
    Contenu base, bois, foretTropicale, marais, oasis, plaineInnondable;
    Terrain desert, lac, montagne, ocean, plaine, prairie, toundra;
    Production productionTuile, productionAttendue;

    @Before
    public void setUp() {
        // Tuile simple
        bois = new Bois();
        ocean = new Ocean();
        base = new Base();
        tuileSimple = new Tuile(1,1, bois, ocean);
        productionAttendue = new Production(1,0,0,1,0,0);
    }

    @Test
    public void testPosition() {
        assertEquals(tuileSimple.x(), 1);
        assertEquals(tuileSimple.y(), 1);
    }


    @Test public void testContenu() {
        assertEquals(tuileSimple.getContenu(), new Bois());
        tuileSimple.setContenu(base);
        assertEquals(tuileSimple.getContenu(), new Base());
        tuileSimple.setContenu(bois);
        assertEquals(tuileSimple.getContenu(), new Bois());
    }

    @Test public void testTerrain() {
        assertEquals(tuileSimple.getTerrain(), new Ocean());
    }

    @Test public void testOccupant() {
        assertEquals(tuileSimple.getOccupant(), null);
    }

    @Test public void testProduction() {
        productionTuile = tuileSimple.getProduction();
        assertEquals(productionTuile, productionAttendue);
    }

    @Test public void testModificateurs() {
        assertEquals(tuileSimple.getModificateurCombat(), 3);
        assertEquals(tuileSimple.getModificateurDeplacement(), 1);
    }

}
