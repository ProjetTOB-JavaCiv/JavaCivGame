package civ.game.unite;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.Unite;


/**
 * Class abstraite regourpant toutes les actions realisables par une unité civile
 */
public abstract class Civil extends Unite{

    public Civil(String nom, Tuile position, Joueur proprietaire, int porteeDeplacement, int cout) {
        super(nom, position, proprietaire, porteeDeplacement, cout);
    }

    /**
     * Permet a une unité miliatire de capturer cette unité
     * @param autre l'unité enemie capturante
     */
    public void capturer(Militaire autre) {
        this.proprietaire = ((Militaire)autre).getProprietaire();
    }
    
}
