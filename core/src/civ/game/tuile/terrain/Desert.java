package civ.game.tuile.terrain;

import java.util.ArrayList;
import java.util.List;

import civ.game.Production;
import civ.game.tuile.Terrain;
import civ.game.tuile.contenu.Caracteristique;

/** Classe representant les terrains Deserts */
public class Desert implements Terrain{
    /** Represente si le terrain a des colline ou non */
    private Boolean colline;

    /**
     * Constructeur par défaut de la classe Desert.
     */
    public Desert() {
        this.colline = false;
    }

    /**
     * Constructeur de la classe Desert
     * @param colline true si le terrain a des colline, false sinon
     */
    public Desert(Boolean colline) {
        this.colline = colline;
    }
    
    public int getModificateurCombat() {
        return this.colline ? 3 : 0;
    }

    public int getModificateurDeplacement() {
        return 0;
    }

    public Production getProduction() {
        Production production = new Production();
        production.materiel += this.colline ? 0: 1;
        return production;
    }

    public Nature getNature() {
        return Nature.TERRESTRE;
    }

    @SuppressWarnings("unchecked")
    public List<Class<Caracteristique>> getCaracteristiquesPossibles() {
        List<Class<Caracteristique>> possible = new ArrayList<Class<Caracteristique>>();
        try {
            possible.add((Class<Caracteristique>)Class.forName("civ.game.tuile.contenu.caracteristique.Base"));
            possible.add((Class<Caracteristique>)Class.forName("civ.game.tuile.contenu.caracteristique.Oasis"));
            possible.add((Class<Caracteristique>)Class.forName("civ.game.tuile.contenu.caracteristique.PlaineInnondable"));
        } catch (ClassNotFoundException e) {
            System.out.println("La classe n'as pas ete trouvée");
        }
        
        return possible;
    }
}