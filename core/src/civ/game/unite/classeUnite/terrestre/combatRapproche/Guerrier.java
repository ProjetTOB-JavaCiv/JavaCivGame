package civ.game.unite.classeUnite.terrestre.combatRapproche;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.unite.classeUnite.terrestre.CombatRapproche;

/** Unité de combat rapproché peu puissante de l'Antiquité. */
public class Guerrier extends CombatRapproche{

    /**
     * Constructeur de la classe guerrier
     * @param nom l'identifiant de l'unité
     * @param position la position de depart de l'unité
     * @param proprietaire le joueur proprietaire de l'unité
     */
    public Guerrier(String nom, Tuile position, Joueur proprietaire) {
        super(nom, position, proprietaire, 20);
        this.ajouterAvantage("civ.game.unite.classeUnite.terrestre.AntiCavalerie", 5);
        this.forceMelee = 20;
    }
    
}
