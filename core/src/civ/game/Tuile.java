package civ.game;

import civ.game.tuile.Contenu;
import civ.game.tuile.Terrain;

/**
 * Classe Tuile permettant de contruire des objets tuile
 * @author Theophane Chollet
 * @version 0.0
 */
public class Tuile {
    
    /** position x de la tuile */
    private int x;
    /** position y de la tuile */
    private int y;
    /** contenu de la tuile */
    private Contenu contenu;
    /** le type de terrain de la tuile */
    private Terrain terrain;
    /** Vrai si une unité se trouve sur la tuile, faux sinon */
    private Boolean occupation = false;



    /**
     * Constructeur d'une tuile
     * 
     * @param x position x de la tuile 
     * @param y position y de la tuile
     * @param contenu contenu de la tuile
     * @param terrain type de terrain de la tuile
     */
    public Tuile(int x, int y, Contenu contenu, Terrain terrain) {
        this.x = x;
        this.y = y;
        this.contenu = contenu;
        this.terrain = terrain;
    }

    /**
     * permet d'obtenir la position x de la tuile.
     * @return la position x de la tuile.
     */
    public int x() {
        return this.x;
    }

    /**
     * permet d'obtenir la position y de la tuile.
     * @return la position y de la tuile.
     */
    public int y() {
        return this.y;
    }

    /**
     * permet d'obtenir le contenu de la tuile.
     * @return le contenu de la tuile.
     */
    public Contenu getContenu() {
        return this.contenu;
    }


    /**
     * Permet de changer le contenu d'une tuile.
     * @param newContenu le nouveau contenu de la tuile.
     */
    public void setContenu(Contenu newContenu) {
        this.contenu = newContenu;
    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    /**
     * permet d'obtenir l'etat d'occupation de la tuile.
     * @return l'etat d'occupation de la tuile.
     */
    public Boolean getOccupation() {
        return this.occupation;
    }

    /** 
     * Permet de changer l'etat d'occupation de la tuile.
     */
    public void changerOccupation() {
        this.occupation = !this.occupation;
    }

    /** Permet d'obtenir la production de la tuile
     * @return un objet production resumant la production de la tuile
     */
    public Production getProduction() {
        return Production.add(this.contenu.getProduction(), this.terrain.getProduction());
    }
}
