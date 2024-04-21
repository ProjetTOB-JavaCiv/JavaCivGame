package game;


import game.tuile.Contenu;
import game.tuile.Terrain;

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
    /** l'unite se trouvant sur la tuile, null si aucune */
    private Unite occupant = null;



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
     * Permet d'obtenir l'unité occupant de la tuile.
     * @return l'occupant de la tuile, null si aucun.
     */
    public Unite getOccupant() {
        return this.occupant;
    }

    /** Permet de definir l'occupant de la tuile
     * @param unite l'unité occupant nouvellement la tuile
     */
    public void setOccupant(Unite unite) {
        this.occupant = unite;
    }

    /** Permet d'obtenir la production de la tuile
     * @return un objet production resumant la production de la tuile
     */
    public Production getProduction() {
        return Production.add(this.contenu.getProduction(), this.terrain.getProduction());
    }

    /**Permet d'obtenir la quantité de point de mouvement a depenser pour sortir de cette case
     * @return la quantité de point de mouvement
     */
    public int getModificateurDeplacement() {
        return 1 + this.contenu.getModificateurDeplacement() + this.terrain.getModificateurDeplacement();
    }

    /** Permet d'obtenir le modificateur de combat de la tuile
     * @return le modificateur de force mélée de la tuile
     */
    public int getModificateurCombat() {
        return this.contenu.getModificateurCombat() + this.terrain.getModificateurCombat();
    }
}
