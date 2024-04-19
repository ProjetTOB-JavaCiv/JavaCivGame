package civ.game.unite.classeUnite.terrestre.antiCavalerie;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.unite.classeUnite.terrestre.AntiCavalerie;

/** Unité de combat de l'Antiquité efficace contre les unités montées */
public class Lancier extends AntiCavalerie{

    /**
     * Constructeur de la classe Lancier
     * @param nom l'identifiant de l'unité
     * @param position la position de depart de l'unité
     * @param proprietaire le joueur proprietaire de l'unité
     */
    public Lancier(String nom, Tuile position, Joueur proprietaire) {
        super(nom, position, proprietaire, 65);
        this.forceMelee = 25;
    }
    
}
