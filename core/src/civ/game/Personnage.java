package civilization;

/**
 * Classe Personnage representant les personnages historiques incarnés par les joueurs.
 * @author Theophane Chollet
 * @version 0.0
 */
public abstract class Personnage {

    /** nom du personnage */
    private String nom;

    /**
     * Constructeur des objets Personnage.
     * @param nom le nom du personnage.
     */
    public Personnage(String nom) {
        this.nom = nom;
    }

    /**
     * Permet d'obtenir le nom du personnage.
     * @return le nom du personnage.
     */
    public String getNom() {
        return this.nom;
    }

    /** Methode permettant d'appliquer le bonus du personnage. (Je sais pas encore comment faire) */
    abstract void bonus();
}
