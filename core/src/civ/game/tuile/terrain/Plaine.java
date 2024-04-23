package game.tuile.terrain;

import java.util.ArrayList;
import java.util.List;

import game.Production;
import game.tuile.Terrain;
import game.tuile.contenu.Caracteristique;

/** Classe Terrain representant les terrains plaine */
public class Plaine implements Terrain{
    /** Represente si le terrain a des colline ou non */
    private Boolean colline;

    /**
     * Constructeur de la classe Plaine
     * @param colline true si le terrain a des colline, false sinon
     */
    public Plaine(Boolean colline) {
        this.colline = colline;
    }
    
    public int getModificateurCombat() {
        return colline ? 3 : 0;
    }

    public int getModificateurDeplacement() {
        return 0;
    }

    public Production getProduction() {
        Production production = new Production();
        production.nourriture = 1;
        production.materiel = 1;
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
            possible.add((Class<Caracteristique>)Class.forName("civ.game.tuile.contenu.caracteristique.Bois"));
            possible.add((Class<Caracteristique>)Class.forName("civ.game.tuile.contenu.caracteristique.Marais"));
            possible.add((Class<Caracteristique>)Class.forName("civ.game.tuile.contenu.caracteristique.PlaineInnondable"));
        } catch (ClassNotFoundException e) {
            System.out.println("La classe n'as pas ete trouvée");
        }
        
        return possible;
    }
}
