package civ.game;

/**
 * Classe Ville permettant de generer les villes des joueurs et leurs actions associées.
 * @author Theophane Chollet
 * @version 0.0
 */
public class Ville {
    
    /** nom de la ville */
    private String nom;
    /** joueur possedant la ville */
    private Joueur dirigeant;
    /** tuile sur laquelle se trouve la ville */
    private Tuile position;
    /** nombre d'habitant de la ville */
    private int nbHabitant = 1;
    /** le nombre de point de vie de la ville */
    private int PV;

    /**
     * constructeur de la ville.
     * @param nom nom de la ville.
     * @param dirigeant joueur possedant la ville.
     */
    public Ville( String nom, Joueur dirigeant, Tuile position) {
        this.nom = nom;
        this.dirigeant = dirigeant;
        this.position = position;
    }

    /**
     * permet de d'obtenir le nom de la ville.
     * @return le nom de la ville.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * permet d'obtenir le joueur possedant la ville.
     * @return le joueur possedant la ville.
     */
    public Joueur getJoueur() {
        return this.dirigeant;
    }

    /**
     * permet d'obtenir la tuile sur laquelle se trouve la ville.
     * @return la tuile sur laquelle se trouve la ville.
     */
    public Tuile getPosistion() {
        return this.position;
    }

    /**
     * permet d'obtenir la production totale de la ville.
     * @return un objet production resumant la production de la ville.
     */
    public Production getProduction() {
        /* TO DO */
        return new Production();
    }

    /**
     * permet d'obtenir le nombre d'habitant actuel de la ville.
     * @return le nombre d'habitant de la ville.
     */
    public int getNbHabitant() {
        return this.nbHabitant;
    }

    /** permet d'obtenir le nombre de point de vie de la ville.
     * @return le nombre de PV de la ville.
     */
    public int getPV() {
        return this.PV;
    }

    /** 
     * permet de faire evoluer la ville.
     */
    public void evoluer() {
        this.nbHabitant ++;
        this.ajouterTuile();
        /* TO DO */
    }

    /** 
     * Permet d'ajouter une tuile au control d'une ville.
     */
    public void ajouterTuile() {
        /* TO DO */
    }




}
