package civ.game.unite.classeUnite.terrestre;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.unite.classeUnite.ClasseTerrestre;

public class AntiCavalerie extends ClasseTerrestre {

    /**
     * Constructeur de la classe abstraite AntiCavalerie
     * @param nom l'identifiant de l'unité
     * @param position la position de depart de l'unité
     * @param proprietaire le joueur proprietaire de l'unité
     * @param cout le cout de production de l'unité
     */
    public AntiCavalerie(String nom, Tuile position, Joueur proprietaire, int cout) {
        super(nom, position, proprietaire, 2, cout);
    }
    
}