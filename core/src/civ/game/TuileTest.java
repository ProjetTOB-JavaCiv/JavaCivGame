package civ.game;

import civ.game.tuile.Contenu;
import civ.game.tuile.Terrain;
import civ.game.tuile.contenu.caracteristique.Base;
import civ.game.tuile.contenu.caracteristique.Bois;
import civ.game.tuile.contenu.caracteristique.ForetTropicale;
import civ.game.tuile.terrain.Desert;
import civ.game.tuile.terrain.Ocean;
import civ.game.unite.classeUnite.terrestre.antiCavalerie.Lancier;
import civ.game.unite.classeUnite.terrestre.cavalerieLegere.Cavalier;
import civ.game.unite.classeUnite.terrestre.cavalerieLourde.CharLourd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TuileTest {
    // Declaration des variables utiles
    Tuile tuileSimple, tuileBis;
    Joueur joueur;
    Contenu base, bois, foret;
    Terrain ocean, desert;
    Production productionTuile, productionAttendue;
    Unite lancier, chardLourd, cavalier;

    @BeforeEach
    void setUp() {
        // Tuile simple
        bois = new Bois();
        base = new Base();
        foret = new ForetTropicale();
        ocean = new Ocean();
        desert = new Desert();
        tuileSimple = new Tuile(1,1, bois, ocean);
        joueur = new Joueur("Joueur", null ); // A modifier quand Personnage sera implémentée
        productionAttendue = new Production(1,0,0,1,0,0);
        tuileBis = new Tuile(0,2, foret, desert);
        cavalier = new Cavalier("cavalier", tuileBis, joueur);
    }

    @Test
    void x() {
        assertEquals(tuileSimple.x(), 1);
    }

    @Test
    void y() {
        assertEquals(tuileSimple.y(), 1);
    }

    @Test
    void getContenu() {
        assertTrue(tuileSimple.getContenu() instanceof Bois);
    }

    @Test
    void setContenu() {
        tuileSimple.setContenu(base);
        assertTrue(tuileSimple.getContenu() instanceof Base);
        tuileSimple.setContenu(bois);
        assertTrue(tuileSimple.getContenu() instanceof Bois);
    }

    @Test
    void getTerrain() {
        assertTrue(tuileSimple.getTerrain() instanceof Ocean);
    }

    @Test
    void getOccupant() {
        assertNull(tuileSimple.getOccupant());
        assertTrue(tuileBis.getOccupant() instanceof Cavalier);
        assertEquals(tuileBis.getOccupant().getNom(), "cavalier");
        assertEquals(tuileBis.getOccupant().getProprietaire(), joueur);
        assertEquals(tuileBis.getOccupant().getPosition(), tuileBis);
    }

    @Test
    void setOccupant() {
        lancier = new Lancier("lancier", tuileSimple, joueur);
        chardLourd = new CharLourd("chard", tuileSimple, joueur);
        tuileSimple.setOccupant(lancier);
        assertTrue(tuileSimple.getOccupant() instanceof Lancier);
        assertEquals(tuileSimple.getOccupant().getNom(), "lancier");
        assertEquals(tuileSimple.getOccupant().getProprietaire(), joueur);
        assertEquals(tuileSimple.getOccupant().getPosition(), tuileSimple);
        tuileSimple.setOccupant(chardLourd);
        assertTrue(tuileSimple.getOccupant() instanceof CharLourd);
        assertEquals(tuileSimple.getOccupant().getNom(), "chard");
        assertEquals(tuileSimple.getOccupant().getProprietaire(), joueur);
        assertEquals(tuileSimple.getOccupant().getPosition(), tuileSimple);

    }

    @Test
    void getProduction() {
        productionTuile = tuileSimple.getProduction();
        assertTrue(productionTuile.estEgale(productionAttendue));
    }

    @Test
    void getModificateurDeplacement() {
        assertEquals(tuileSimple.getModificateurDeplacement(), 2);
    }

    @Test
    void getModificateurCombat() {
        assertEquals(tuileSimple.getModificateurCombat(), 3);
    }
}