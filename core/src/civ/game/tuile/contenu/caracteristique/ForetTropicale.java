package civ.game.tuile.contenu.caracteristique;

import civ.game.Production;
import civ.game.tuile.contenu.Caracteristique;

public class ForetTropicale implements Caracteristique{
    
    public int getModificateurCombat() {
        return 3;
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

