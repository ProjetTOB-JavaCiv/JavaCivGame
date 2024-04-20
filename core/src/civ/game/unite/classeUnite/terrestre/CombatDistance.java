package civ.game.unite.classeUnite.terrestre;

import civ.game.Carte;
import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.exception.AttaqueException;
import civ.game.unite.Militaire;
import civ.game.unite.classeUnite.ClasseTerrestre;

public abstract class CombatDistance extends ClasseTerrestre{

    
    /** La porté de l'unité (1 pour les unité de mélée, 2+ pour les unité à distance) */
    protected int portee;
    /** La force en combat a distance de l'unite */
    protected int forceDistance;


    /**
     * Constructeur de la classe abstraite CombatDistance
     * @param nom l'identifiant de l'unité
     * @param position la position de depart de l'unité
     * @param proprietaire le joueur proprietaire de l'unité
     * @param cout le cout de production de l'unité
     */
    public CombatDistance(String nom, Tuile position, Joueur proprietaire, int cout, int portee) {
        super(nom, position, proprietaire, 2, cout);
        this.portee = portee;
    }

    
    /**
     * Methode d'attaque d'une autre unité à distance 
     * @param autre unité à attaquer
     */
    public void attaquerDistance(Carte carte, Militaire autre) throws AttaqueException {
        if (pm > 0 && !indicateurCombat && carte.distanceAbsolue(this.position, autre.getPosition()) <= this.portee) {
            boolean fatal = autre.subirDegat(20*(3*this.getPuissanceDistance() + autre.getPuissanceMelee(this))/(3*autre.getPuissanceMelee(this) + this.getPuissanceDistance()));
            if (fatal){
                this.experience ++;
            }
            this.pm = 0;
            this.indicateurCombat = true;
        }else {
            if (pm <= 0) {
                throw new AttaqueException("Aucune action restante.");
            } else if(indicateurCombat) {
                throw new AttaqueException("Attaque impossible car l'unité a deja attaqué ce tour-ci");
            } else if(carte.distanceAbsolue(this.position, autre.getPosition()) > portee) {
                throw new AttaqueException("L'unité ciblée est trop loin");    
            } else {   
                throw new AttaqueException("Attaque impossible.");
            }
            
        }
        
    }

    

    /**
     * Permet d'obtenir la portée de l'unité.
     * @return la portée de l'unité.
     */
    public int getPortee() {
        return this.portee;
    }


    /**
     * Permet d'obtenir la puissance de l'unité en combat à distance.
     * @return la puissance de l'unité en combat à distance.
     */
    public int getPuissanceDistance() {
        return this.forceDistance * (this.pv / 100);
    }

    
}
