package game.tuile.terrain;

import java.util.ArrayList;
import java.util.List;

import game.Production;
import game.tuile.Terrain;
import game.tuile.contenu.Caracteristique;


/** Classe representant les terrains ocean */
public class Ocean implements Terrain{
    
    public int getModificateurCombat() {
        return 0;
    }

    public int getModificateurDeplacement() {
        return 0;
    }

    public Production getProduction() {
        Production production = new Production();
        production.nourriture = 1;
        return production;
    }

    public Nature getNature() {
        return Nature.MARITIME;
    }

    @SuppressWarnings("unchecked")
    public List<Class<Caracteristique>> getCaracteristiquesPossibles() {
        List<Class<Caracteristique>> possible = new ArrayList<Class<Caracteristique>>();
        try {
            possible.add((Class<Caracteristique>)Class.forName("civ.game.tuile.contenu.caracteristique.Base"));
        } catch (ClassNotFoundException e) {
            System.out.println("La classe n'as pas ete trouvée");
        }
        return possible;
    }
}
