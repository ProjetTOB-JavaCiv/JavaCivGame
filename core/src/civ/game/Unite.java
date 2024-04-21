package game;

/**
 * InterfaceUnite permettant de definir les actions de base realisable par tout type d'unité
 * @author Theophane Chollet
 * @version 0.0
 */
public interface Unite {

    /**
     * Permet d'obtenir le nom de l'unité.
     * @return le nom de l'unité.
     */
    String getNom();

    /**
     * Permet d'obtenir la puissance de l'unité.
     * @return la puissance de l'unité.
     */
    int getPuissance();

    /**
     * Permet d'obtenir les points de vie restant de l'unité.
     * @return les points de vie restant de l'unité.
     */
    int getPV();

    /**
     * Permet d'obtenir la portée de l'unité.
     * @return la portée de l'unité.
     */
    int getPortee();

    /**
     * Methode d'attaque d'une autre unité 
     * @param autre unité à attaquer
     */
    void attaquer(Unite autre);


    /**
     * Permet a l'unité de se prendre des degats
     * @param degat le montant de degat subit
     */
    void prendreDegat(int degat);

    /**
     * methode de deplacement de l'unité sur une tuile
     * Pre : destination in getDeplacementPossible()
     * @param destination tuile de destination de l'unité
     */
    void seDeplacer(Tuile destination);

    /**
     * Permet d'obtenir l'ensemble des tuiles sur lesquelles l'unité peut se deplacer
     * @return l'ensemble des tuiles sur lesquelles l'unité peut se deplacer
     */
    Tuile[] getDeplacementPossible();

    /**
     * Permet d'obtenir l'ensemble des types d'unité contre lesquels l'unité a un avantage
     * @return l'ensemble des types d'unité contre lesquels l'unité a un avantage
     */
    Unite[] getAvantage();
    
}
