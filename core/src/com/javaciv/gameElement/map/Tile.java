package com.javaciv.gameElement.map;


import com.javaciv.builder.HashMapFeature;
import com.javaciv.builder.HashMapRessource;
import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Military;
import com.javaciv.type.LandType;
import com.javaciv.type.FeatureType;
import com.javaciv.type.ProductionType;
import com.javaciv.type.RessourceType;
import com.javaciv.gameElement.Player;

/**
 * Cette classe représente une tuile.
 * Une tuile correspond à une case de la carte du monde,
 * elle contient des informations sur ce qui se trouve dessus
 * ainsi que du type de terrain qu'elle représente
 */
public class Tile {
    /** La position x de la tuile;
     */
    private int x;
    /** La position y de la tuile.
     */
    private int y;
    /** Le type de terrain de la tuile.
    */
    private LandType land;

    /** la caracteristique de terrain de la tuile */
    private Feature feature;

    /** la ressource de la tuile */
    private Ressource ressource = HashMapRessource.getRessource(RessourceType.BASE);

    /** Propriétaire de la case, null si la case est occupé par personne */
    Player owner = null;

    /** Production de la tuile */
    ProductionType production;

    /** Flotant indicateur de la valeur stratégique d'une tuille */
    double strategicValue;

    /** Booléen décrivant si la tuille est traversable par une unité terrestre
     * Ceci ne peut changer car c'est une propriété géographique du terrain
     */
    final boolean isTraversableByLandUnit;
    /** Booléen décrivant si la tuille est traversable par une unité maritime
     * Ceci ne peut changer car c'est une propriété géographique du terrain
     */
    final boolean isTraversableBySeaUnit;

    
    /** Modificateur de deplacement de la tuile */
    int movementModifier;
    /** Modificateur de combat de la tuile */
    int fightModifier;

    /** Variable stockant l'unité militaire étant sur la case.
     * Il n'est pas obligatoire pour une tuille d'avoir une unité militaire dessus
    */
    private Military miliratyOnTile;
    /** Variable stockant l'unité civile étant sur la case.
     * Il n'est pas obligatoire pour une tuille d'avoir une unité militaire dessus
    */
    private Civilian civilianOnTile;

    /** Booléen qui décrit si une tuille est occupé par une unité militaire
     */
    private boolean isMilitaryUnitOnTile = false;

    /** Booléen qui décrit si une tuille est occupé par une unité civile
     */
    private boolean isCivilianUnitOnTile = false;

    /**
     * Constructeur d'une tuile.
     *
     * @param x position x de la tuile
     * @param y position y de la tuile
     * @param terrain type de terrain de la tuile
     */
    public Tile(int x, int y, LandType land,
    boolean isTraversableByLandUnit, boolean isTraversableBySeaUnit, ProductionType production, double baseLandValue) {
        this.x = x;
        this.y = y;
        this.land = land;
        this.isTraversableByLandUnit = isTraversableByLandUnit;
        this.isTraversableBySeaUnit = isTraversableBySeaUnit;

        this.production = production;

        setFeature(); //assignation d'une caracteristique de terrain adaptée
        setResssource(); // assignation d'une ressource

        //Assignation de la valeur stratégique de la tuile TODO : Ajouter de la valeur si y'a une ressource stratégique
        this.strategicValue = baseLandValue;
    }

    /**
     * permet d'obtenir la position x de la tuile.
     * @return la position x de la tuile.
     */
    public int getX() {
        return this.x;
    }

    /**
     * permet d'obtenir la position y de la tuile.
     * @return la position y de la tuile.
     */
    public int getY() {
        return this.y;
    }

    /** permet d'avoir la production totale de la tuile 
     * @return la production totale de la tuile
    */
    public ProductionType getProduction() {
        return ProductionType.add(this.production, this.feature.getProduction(), this.ressource.getProduction());
    }

    /** Permet de recuperer le terrain de la tuile
     * @return le terrain de la tuile
     */
    public LandType getLand() {
        return this.land;
    }

    /** permet de recuperer la caracteristique de terrain de la tuile 
     * @return la caracteistique de terrain
    */
    public Feature getFeature() {
        return this.feature;
    }

    /** permet de recuperer la ressource persente sur la tuile
     * @return la ressource de la tuile
     */
    public Ressource  getRessource() {
        return this.ressource;
    }

    public void print() {
        System.out.print("[" + this.x + "," + this.y + "|" + this.land.toInt() + "]");
    }

    /**
     * Permet d'obtenir l'unite civile qui est sur la tuile
     * @return l'unite civile qui est sur la tuile
     */
    public Civilian getCivilianOnTile() {
        return this.civilianOnTile;
    }

    /**
     * Permet d'obtenir l'unite militaire qui est sur la tuile
     * @return l'unite militaire qui est sur la tuile
     */
    public Military getMilitaryOnTile() {
        return this.miliratyOnTile;
    }

    /**
     * Permet de savoir si une unité civile est sur la tuile
     * @return boolean indiquant si une unité civile est sur la tuile
     */
    public boolean getIsCivilianUnitOnTile() {
        return this.isCivilianUnitOnTile;
    }

    /**
     * Permet de savoir si une unité militaire est sur la tuile
     * @return boolean indiquant si une unité militaire est sur la tuile
     */
    public boolean getIsMilitaryUnitOnTile() {
        return this.isMilitaryUnitOnTile;
    }

    public boolean getIsTraversableByLandUnit() {
        return this.isTraversableByLandUnit;
    }

    public boolean getIsTraversableBySeaUnit() {
        return this.isTraversableBySeaUnit;
    }

    public double getBaseLandValue() {
        return this.strategicValue;
    }

    /** Renvoie le propriétaire de la case */
    public Player getOwner() {
        return this.owner;
    }

    /** Permet de changer de position une tuille sur l'axe X, est utile UNIQUEMENT lors de la création de
     * la map, après cela, la position d'une tuile ne doit pas changer
     * TODO : Changer le degré de liberté de cette méthode ??
     */
    public void setX(int x) {
        this.x = x;
    }

    /** Permet de changer de position une tuille sur l'axe Y, est utile UNIQUEMENT lors de la création de
     * la map, après cela, la position d'une tuile ne doit pas changer  */
    public void setY(int y) {
        this.y = y;
    }

    /** Permet de choisir une caracteristique a la creation de la tuile */
    private void setFeature() {
        double rand = Math.random();
        switch(this.land){
            case DESERT :
                this.feature = rand > 0.95 ? 
                               HashMapFeature.getFeature(FeatureType.OASIS): 
                               HashMapFeature.getFeature(FeatureType.BASE);
            case PLAINE :
                if(rand < 0.7) { 
                    this.feature = HashMapFeature.getFeature(FeatureType.BASE); 
                } else if(rand < 0.8) { 
                    this.feature = HashMapFeature.getFeature(FeatureType.WOODS);
                } else { 
                    this.feature = HashMapFeature.getFeature(FeatureType.RAINFOREST);
                }
            case PRAIRIE :
                if(rand < 0.7) { 
                    this.feature = HashMapFeature.getFeature(FeatureType.BASE); 
                } else if(rand < 0.9) { 
                    this.feature = HashMapFeature.getFeature(FeatureType.WOODS);
                } else { 
                    this.feature = HashMapFeature.getFeature(FeatureType.MARSH);
                }
            case TOUNDRA :
                this.feature = rand > 0.8 ? 
                               HashMapFeature.getFeature(FeatureType.WOODS):
                               HashMapFeature.getFeature(FeatureType.BASE);
            default :
                this.feature = HashMapFeature.getFeature(FeatureType.BASE);
        }
    }

    /** Permet de choisir une ressource pour la tuile. Utilisable uniquement lors de la creation de la tuile
     * TODO : A modifier pour ajouter des ressources
    */
    private void setResssource() {
        double rand = Math.random();
        switch(this.feature.getFeatureType()) {
            case BASE :
                
                if(this.isTraversableByLandUnit) {
                    if (rand < 0.3) { // modifier la valeur pour une generation plus ou moins dense
                        this.ressource = HashMapRessource.getRessource(RessourceType.BETAIL);
                    }
                }else if (this.isTraversableBySeaUnit) {
                    if (rand < 0.3) {
                        this.ressource = HashMapRessource.getRessource(RessourceType.CRABE);
                    }
                }

            case MARSH :
                if (rand < 0.3) {
                    this.ressource = HashMapRessource.getRessource(RessourceType.BLE);
                }
            case RAINFOREST :
                if (rand < 0.3) {
                    this.ressource = HashMapRessource.getRessource(RessourceType.CACAO);
                }
            case WOODS :
                if (rand < 0.3) {
                    this.ressource = HashMapRessource.getRessource(RessourceType.CERVIDE);
                }
            default :             
        }
    }

    /** Set la caracteristique de terrain de la tuile et la ressource a BASE, utile lors de la construction d'une ville 
     * ou d'un quartier sur la tuile faisant disparaitre les caracteristique de la tuile*/
    public void resetFeatureAndRessource() {
        this.feature = HashMapFeature.getFeature(FeatureType.BASE);
        this.ressource = HashMapRessource.getRessource(RessourceType.BASE);
    }

    /**
     * Set une unité militaire sur la tuille
     * @param unit Unité militaire
     */
    public void setMilitaryUnitOnTile(Military unit) {
        this.isMilitaryUnitOnTile = true;
        this.miliratyOnTile = unit;
    }
    /**
     * Set une unité civile sur la tuille
     * @param unit Unité civile
     */
    public void setCivilianUnitOnTile(Civilian unit) {
        this.isCivilianUnitOnTile = true;
        this.civilianOnTile = unit;
    }

    /** Set la propriété d'un joueur sur une tuille 
     * @param owner la civilisation qui va posséder la tuille
    */
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}