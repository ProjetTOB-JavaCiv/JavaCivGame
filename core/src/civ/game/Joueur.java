package civilization;

/**
 * Classe regroupant toutes les actions relative au joueur directement
 * @author Theophane Chollet
 * @version 0.0
 */
public class Joueur {

    /** Nom du joueur */
    private String nom;
    /** Personnage historique incarné par le joueur */
    private Personnage personnage;


    /**
     * Constructeur des objets de type Joueur
     * @param nom nom du joueur
     * @param personnage Personnage historique incarné par le joueur
     */
    public Joueur(String nom, Personnage personnage) {
        this.nom = nom;
        this.personnage = personnage;
    }


    /**
     * Permet d'obtenir le nom du joueur
     * @return le nom du joueur
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Permet d'obtenir le personnage historique incarné par le joueur
     * @return le personnage historique incarné par le joueur
     */
    public Personnage getPersonnage() {
        return this.personnage;
    }

    /**
     * Methode permettant de recuperer la production totale de toute les villes du joueur.
     * 
     * @return un objet de type Production contenant la production totale du joueur.
     */
    public Production getProduction() {
        /* TO DO */

        return new Production();
    }
}
