import civ.game.Production;
import civ.game.Tuile;
import civ.game.tuile.contenu.caracteristique.Base;
import civ.game.tuile.Contenu;
import civ.game.tuile.Terrain;
import civ.game.tuile.contenu.caracteristique.Bois;
import civ.game.tuile.terrain.Ocean;

import org.junit.*;

/**
 * Classe de tests unitaires TestTuile permettant de tester la classe Tuile.
 * @author Elisa Ciocarlan
 * @version 0.0
 */
public class TuileTest {
    Tuile tuileSimple;
    Contenu base, bois;
    Terrain ocean;
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
        Assert.assertEquals(tuileSimple.x(), 1);
        Assert.assertEquals(tuileSimple.y(), 1);
    }


    @Test public void testContenu() {
        Assert.assertEquals(tuileSimple.getContenu(), new Bois());
        tuileSimple.setContenu(base);
        Assert.assertEquals(tuileSimple.getContenu(), new Base());
        tuileSimple.setContenu(bois);
        Assert.assertEquals(tuileSimple.getContenu(), new Bois());
    }

    @Test public void testTerrain() {
        Assert.assertEquals(tuileSimple.getTerrain(), new Ocean());
    }

    @Test public void testOccupant() {
        Assert.assertNull(tuileSimple.getOccupant());
    }

    @Test public void testProduction() {
        productionTuile = tuileSimple.getProduction();
        Assert.assertEquals(productionTuile, productionAttendue);
    }

    @Test public void testModificateurs() {
        Assert.assertEquals(tuileSimple.getModificateurCombat(), 3);
        Assert.assertEquals(tuileSimple.getModificateurDeplacement(), 1);
    }

}
