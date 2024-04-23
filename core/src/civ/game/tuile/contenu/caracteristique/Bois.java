package game.tuile.contenu.caracteristique;

import game.Production;
import game.tuile.contenu.Caracteristique;

/** Classe implementant la caracteristique bois */
public class Bois implements Caracteristique{
    
    public int getModificateurCombat() {
        return 3;
    }

    public int getModificateurDeplacement() {
        return 1;
    }

    public Production getProduction() {
        
        Production production = new Production();
        production.materiel = 1;
        return production;
    }
}
