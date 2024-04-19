package civ.game.tuile;

import java.util.List;

import civ.game.Production;
import civ.game.tuile.contenu.Caracteristique;
import civ.game.tuile.terrain.Nature;

public interface Terrain{
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

     /** 
     * Permet d'obtenir la nature du contenu.
     * @return la nature du contenu.
     */
    Nature getNature();

    /** 
     * Permet d'obtenir la production du terrain.
     * @return Un objet Production resumant la production du terrain.
     */
    Production getProduction();

    /**
     * Permet d'obtenir les classes de caracteristique possible pour ce type de terrain
     * @return une liste contenant toutes les caracteristiques possible pour ce terrain
     */
    List<Class<Caracteristique>> getCaracteristiquesPossibles();
}
