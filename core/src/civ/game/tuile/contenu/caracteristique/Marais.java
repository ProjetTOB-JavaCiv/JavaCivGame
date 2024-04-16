package civ.game.tuile.contenu.caracteristique;

import civ.game.Production;
import civ.game.tuile.contenu.Caracteristique;

public class Marais implements Caracteristique{
    
    public int getModificateurCombat() {
        return -2;
    }

    public int getModificateurDeplacement() {
        return 1;
    }

    public Production getProduction() {
        Production production = new Production();
        production.nourriture = 1;
        return production;
    }
}

