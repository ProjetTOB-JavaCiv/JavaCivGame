package civ.game;

import java.util.*;

import civ.game.exception.DeplacementException;
import civ.game.tuile.terrain.Nature;

/**
 * Super class Unite permettant de definir les actions de base realisable par tout type d'unité
 * @author Theophane Chollet
 * @version 0.0
 */
public abstract class Unite implements ConstructionDeVille{

    /** Le nom de l'unité */
    protected String nom;
    /** Le joueur possedant l'unité */
    protected Joueur proprietaire;
    /** La tuile sur laquelle se trouve l'unite */
    protected Tuile position;
    /** Le nombre de pv actuel de l'unité */
    protected int pv = 100;
    /** Le nombre de point de mouvement restant a l'unité */
    protected int pm;
    /** indicateur permettant de savoir si l'unité s'est deja deplacer dans le tour */
    protected boolean indicateurDeplacement = false;
    protected int experience;
    /** La quantité de tuile que peut parcoourir une unité en un tour */
    protected final int PORTEE_DEPLACEMENT;
    /** le cout en materiel pour produire l'unité */
    protected final int COUT;


    /** Constructeur de la classe Unite
     * @param nom l'identifiant de l'unité
     * @param position la position de depart de l'unite 
     * @param proprietaire le proprietaire de l'unité
     * @param porteeDeplacement le nombre de point de mouvement disponible chaque tour pour l'unité
     * @param cout le cout de production de l'unité
     */
    public Unite(String nom, Tuile position, Joueur proprietaire, int porteeDeplacement, int cout) {
        this.nom = nom;
        this.position = position;
        this.proprietaire = proprietaire;
        this.PORTEE_DEPLACEMENT = porteeDeplacement;
        this.COUT = cout;
        this.position.setOccupant(this);
        this.pm = this.PORTEE_DEPLACEMENT;
    }

    /**
     * Permet d'obtenir le nom de l'unité.
    * @return le nom de l'unité.
    */
    public String getNom() {
        return this.nom;
    }

    /** 
     * Permet d'obtenir le proprietaire de l'unité
    * @return le proprietaire de l'unité
    */
    public Joueur getProprietaire() {
        return this.proprietaire;
    }

    /**
     * Permet d'obtenir la tuile sur laquelle se trouve l'unité.
    * @return la tuile sur laquelle se trouve l'unité.
    */
    public Tuile getPosition() {
        return this.position;
    }

    /**
     * Permet de recuperer le cout de l'unité.
     * @return le cout de l'unité
     */
    public int getCout() {
        return this.COUT;
    }

    
    /**
     * Permet d'obtenir les points de vie restant de l'unité.
     * @return les points de vie restant de l'unité.
     */
    public int getPV() {
        return this.pv;
    }
 
 

    /**
    * methode de deplacement de l'unité sur une tuile
    * Pre : destination in getDeplacementPossible()
    * @param destination tuile de destination de l'unité
    */
    public void seDeplacer(Carte map, Tuile destination) throws DeplacementException {
        if (this.getDeplacementPossible(map).contains(destination) && destination.getOccupant() == null) {
            this.position.setOccupant(null);
            this.position = destination;
            this.position.setOccupant(this);
        } else {
            if(!this.getDeplacementPossible(map).contains(destination)) {
                throw new DeplacementException("Point de mouvement insuffisant.");
            } else if(destination.getOccupant() != null) {
                throw new DeplacementException("La case de destination est deja occupée.");
            } else {
                throw new DeplacementException("Deplacement impossible.");
            }
            
        }
    }

    /**
     * Permet d'obtenir l'ensemble des tuiles sur lesquelles l'unité peut se deplacer
    * @param map la carte du jeu
    * @return l'ensemble des tuiles sur lesquelles l'unité peut se deplacer
    */
    public Set<Tuile> getDeplacementPossible(Carte map) {
        
        return calculDeplacement(map, this.position, this.pm, this.indicateurDeplacement);
    }

    /**
     * Permet de calculer les deplacement possible de maniere recursive
    * @param map la carte du jeu
    * @param t la tuile a partir de laquelle on lance le calcul
    * @param pMrestant le nombre de point de mouvement restant
    * @return les deplacements possible a partir d'une tuile et d'un certain nombre de point de mouvement
    */
    protected Set<Tuile> calculDeplacement(Carte map, Tuile t, int pMRestant, boolean iDeplacement) {
        Set<Tuile> deplacementPossible = new HashSet<Tuile>();
        Set<Tuile> adjacence = map.getAdjacence(t);
        adjacence.removeIf(e -> (e.getTerrain().getNature() != this.getNatureDeplacement()));

        // Si elle ne s'est pas deja deplacer ce tour ci
        if (!iDeplacement) {
            // on regarde tous les endroits ou on peut aller
            deplacementPossible.addAll(adjacence);

            // et on recommence à calculer les deplacements à partir de là
            Iterator<Tuile> i = adjacence.iterator();
            while (i.hasNext()) {
                Tuile next  = i.next();
                deplacementPossible.addAll(calculDeplacement(map, next, pMRestant-next.getModificateurDeplacement(), true));
            }
        } else if (pMRestant > 0) { // Sinon s'il nous reste des points de mouvement.
            // on retire tout les endroit qui sont trop loin
            adjacence.removeIf(e -> (e.getModificateurDeplacement() > pMRestant));
            deplacementPossible.addAll(adjacence);

            // et on recommence à calculer les deplacements à partir de là
            Iterator<Tuile> i = adjacence.iterator();
            while (i.hasNext()) {
                Tuile next  = i.next();
                deplacementPossible.addAll(calculDeplacement(map, next, pMRestant-next.getModificateurDeplacement(), true));
            }
            
        }
        return deplacementPossible;
    }
 
 
    /** 
     * Permet de condenser toute les actions de debut de tour de l'unité
    */
    public void debutTour() {
        this.pm = this.PORTEE_DEPLACEMENT;
        this.indicateurDeplacement = false;
    }

    /**
     * Permet d'obtenir la nature du terrain sur lequel cette unité peut se deplacer
     * @return la nature du terrain sur lequel cette unité peut se deplacer
     */
    abstract public Nature getNatureDeplacement();
    
}
