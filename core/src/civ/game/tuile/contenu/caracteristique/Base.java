package civ.game.tuile.contenu.caracteristique;

import civ.game.Production;
import civ.game.tuile.contenu.Caracteristique;

public class Base implements Caracteristique{
    
    public int getModificateurCombat() {
        return 0;
    }

    public int getModificateurDeplacement() {
        return 0;
    }

    public Production getProduction() {
        
        Production production = new Production();
        return production;
    }
}
