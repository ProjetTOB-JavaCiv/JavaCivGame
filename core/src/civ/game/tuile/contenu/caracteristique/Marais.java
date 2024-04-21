package game.tuile.contenu.caracteristique;

import game.tuile.contenu.Caracteristique;
import game.Production;

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

