package civ.game.unite.classeUnite.terrestre.combatDistance;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.unite.classeUnite.terrestre.CombatDistance;


/** Cette unité d'attaque à distance de l'Antiquité est faible, mais plus efficace en attaque qu'en défense */
public class Frondeur extends CombatDistance{

    /**
     * Constructeur de la classe Frondeur
     * @param nom l'identifiant de l'unité
     * @param position la position de depart de l'unité
     * @param proprietaire le joueur proprietaire de l'unité
     */
    public Frondeur(String nom, Tuile position, Joueur proprietaire) {
        super(nom, position, proprietaire, 35, 1);
        this.forceDistance = 15;
        this.forceMelee = 5;
    }
    
}
