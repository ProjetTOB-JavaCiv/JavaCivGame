package civ.game.unite.classeUnite.terrestre.cavalerieLourde;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.unite.classeUnite.terrestre.CavalerieLourde;


/** Unité de cavalerie lourde de l'Antiquité, aux dégâts importants. */
public class CharLourd extends CavalerieLourde{


    /**
     * Constructeur de la classe CharLourd
     * @param nom l'identifiant de l'unité
     * @param position la position de depart de l'unité
     * @param proprietaire le joueur proprietaire de l'unité
     */
    public CharLourd(String nom, Tuile position, Joueur proprietaire) {
        super(nom, position, proprietaire, 2, 65);
        this.forceMelee = 28;
    }

    @Override
    public void debutTour(){
        this.pm = this.PORTEE_DEPLACEMENT;
        this.indicateurCombat = true;
        this.indicateurDeplacement = false;
        if(this.position.getModificateurDeplacement() == 1) {
            this.pm += 1;
        } 
    }
    
}
