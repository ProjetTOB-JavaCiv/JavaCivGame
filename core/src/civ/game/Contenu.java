package civ.game;

/**
 * Interface Contenu permettant de regrouper tout les types etant contenu dans une tuile.
 * @author Theophane Chollet
 * @version 0.0
 */
public interface Contenu {

    /**
     * permet d'obtenir comment la tuile influe sur la puissance de l'unité qui s'y trouve.
     * @return le modificateur de puissance
     */
    int getModificateurCombat();

    /**
     * permet d'obtenir comment la tuile influe sur le deplacement de l'unité qui y passe.
     * @return le modificateur de deplacement
     */
    int getModificateurDeplacement();

}
