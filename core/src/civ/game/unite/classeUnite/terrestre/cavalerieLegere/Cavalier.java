package civ.game.unite.classeUnite.terrestre.cavalerieLegere;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.unite.classeUnite.terrestre.CavalerieLegere;

/** Unité de cavalerie légère et rapide de l'ère classique */
public class Cavalier extends CavalerieLegere{

    /**
     * Constructeur de la classe cavalier
     * @param nom l'identifiant de l'unité
     * @param position la position de depart de l'unité
     * @param proprietaire le joueur proprietaire de l'unité
     */
    public Cavalier(String nom, Tuile position, Joueur proprietaire) {
        super(nom, position, proprietaire, 4, 80);
        this.forceMelee = 36;
    }
    
}
