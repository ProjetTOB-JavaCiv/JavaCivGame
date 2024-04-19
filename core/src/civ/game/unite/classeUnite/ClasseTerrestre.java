package civ.game.unite.classeUnite;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.tuile.terrain.Nature;
import civ.game.unite.Militaire;

/**
 * Classe abstraite permettant de regrouper tous les types d'unité militaire terrestre
 */
public abstract class ClasseTerrestre extends Militaire{
    /** cf. constructeur de la classe Unite */
    public ClasseTerrestre(String nom, Tuile position, Joueur proprietaire, int pm, int cout) {
        super(nom, position, proprietaire, pm, cout);
    }

    @Override
    public Nature getNatureDeplacement() {
        return Nature.TERRESTRE;
    }
    
}
