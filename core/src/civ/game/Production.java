package game;


/**
 * Classe Production permettant de structurer la facon de recuperer la production
 * des differentes tuiles / villes / joueurs.
 * @author Theophane Chollet
 * @version 0.0
 */
public class Production {
    
    /** Ressource de construction */
    public int materiel = 0;
    /** Ressource de technologie */
    public int science = 0;
    /** Ressource de religion */
    public int foi = 0;
    /** ressource de nourriture */
    public int nourriture = 0;
    /** ressource de culture */
    public int culture = 0;
    /** ressource d'or */
    public int or = 0;


    /**
     * Permet d'ajouter 2 production et de stocker le resultat dans un 3e objet
     * @param p1 production 1
     * @param p2 production 2
     * @return un objet contenant la somme des productions p1 et p2
     */
    public static Production add(Production p1, Production p2) {
        Production sum = new Production();

        sum.materiel = p1.materiel + p2.materiel;
        sum.nourriture = p1.nourriture + p2.nourriture;
        sum.foi = p1.foi + p2.foi;
        sum.science = p1.science + p2.science;
        sum.culture = p1.culture + p2.culture;
        sum.or = p1.or + p2.or;

        return sum;
    }
}
