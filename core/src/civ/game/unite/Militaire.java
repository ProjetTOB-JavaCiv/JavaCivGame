package civ.game.unite;

import civ.game.Joueur;
import civ.game.Tuile;
import civ.game.Unite;

/**
 * Classe abstraite permettant de regrouper les actions effectuables par les unités militaires
 */
public abstract class Militaire extends Unite{

    /** La force en combat de mélée de l'unite */
    protected int forceMelee;
    /** Indicateur permettant de savoir si l'unite s'est deplacée ce tour */
    protected boolean indicateurCombat;


    /**
     * cf. constructeur Unite
     */
    public Militaire(String nom, Tuile position, Joueur proprietaire, int pm, int cout) {
        super(nom, position, proprietaire, pm, cout);
    }

    /**
     * Permet d'obtenir la puissance de l'unité en combat de mélée.
     * @return la puissance de l'unité en combat de mélée.
     */
    public int getPuissanceMelee(Militaire autre) {
        return (this.forceMelee + this.position.getModificateurCombat() + this.getAvantage(autre)) * (this.pv / 100);
    }


    /**
     * Permet a l'unité de se prendre des degats
     * @param degat le montant de degat subit
     * @return Vrai si l'attque lui a ete fatale, faux sinon
     */
    public boolean subirDegat(int degat) {
        this.pv -= degat;
        return this.pv <= 0;
    }

     /**
     * Methode d'attaque d'une autre unité en mélée
     * @param autre unité à attaquer
     */
    public void attaquerMelee(Unite autre) {
        if (pm > 0 && indicateurCombat) {
            if(autre instanceof Militaire) {
                boolean fatal = ((Militaire)autre).subirDegat(20*(3*this.getPuissanceMelee((Militaire)autre) + ((Militaire)autre).getPuissanceMelee(this))
                    /(3*((Militaire)autre).getPuissanceMelee(this) + this.getPuissanceMelee((Militaire)autre)));
                if (fatal){
                    this.experience ++;
                }else {
                    ((Militaire)autre).attaquerMelee(this);
                }
            } else if(autre instanceof Civil) {
                ((Civil)autre).capturer(this);
            }
            this.pm = 0;
            this.indicateurCombat = false;
        }
        
    }

    @Override
    /** 
     * Permet de condenser toute les actions de debut de tour de l'unité
    */
    public void debutTour() {
        this.pm = this.PORTEE_DEPLACEMENT;
        this.indicateurCombat = true;
        this.indicateurDeplacement = false;
    }

    /** 
     * Permet d'ajouter facilement des element a la Map avantage de l'unité
     * @param className le nom de la classe contre qui l'avatage se destine
     * @param avantage la valeur de l'avantage
    */
    @SuppressWarnings("unchecked")
    public void ajouterAvantage(String className, Integer avantage) {
        try {
            this.avantage.put((Class<Militaire>)Class.forName(className), avantage);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
