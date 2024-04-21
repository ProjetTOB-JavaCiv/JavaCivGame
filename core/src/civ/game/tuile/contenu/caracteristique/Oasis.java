package game.tuile.contenu.caracteristique;

import game.Production;
import game.tuile.contenu.Caracteristique;

public class Oasis implements Caracteristique{
    
    public int getModificateurCombat() {
        return 0;
    }

    public int getModificateurDeplacement() {
        return 0;
    }

    public Production getProduction() {
        Production production = new Production();
        production.or = 1;
        production.nourriture = 3;
        return production;
    }
}
