package com.javaciv.gameElement;

import java.util.ArrayList;
import java.util.List;

import com.javaciv.gameElement.map.Tile;

/**
 * Classe représentant une ville dans le jeu.
 * Une ville est capable de produire des ressources, de construire des unités et des batiments.
 * Une ville possède un certain nombre d'habitant qu'elle peut 
 */
public class City {

    /** Position de la ville */
    Tile position;
    /** Nom de la ville */
    String name = "ville";
    /** Point de vie de la ville */
    int health = 100;
    /** Puissance d'attaque de la ville */
    int attack = 10;
    
    /** Nombre de points de science produit par une ville chaque tour */
    int sciencePerTurnProd;
    /** Nombre de points de culture produit par une ville chaque tour */
    int culturePerTurnProd;
    /** Nombre de points de foi produit par une ville chaque tour */
    int faithPerTurnProd;
    /** Nombre de gold produit par une ville chaque tour */
    int goldPerTurnProd;
    /** Nombre de points de production porduit par une ville chaque tour */
    int productionPerTurnProd;

    /** Nombre de points de nourriture produit par une ville chaque tour */
    int foodPerTurnProd;
    /** Population de la ville */
    int population;
    /** Nombre de points de nourriture nécessaire pour obtenir un nouveau citoyen */
    int foodNeededForNewCitizen = 50;
    /** Nourriture produite par une ville depuis la dernière croissance de la population */
    int producedFood = 0;
    /** Nombre de points de nourriture supplémentaire en plus pour chaque augementation du nombre d'habitant */
    int foodNeededForNewCitizenPlus = 25 + 5 * population;
    /** Nombre de nourriture en dette autorisé avant de tuer un citoyen en cas de dette */
    final int foodDebt = -5;

    /** Culture produite par une ville depuis lfoodNeededForNewCitizenPlusa dernière extension de territoire */
    int producedCulture = 0;
    /** Nombre de point de culture nécessaire pour obtenir une nouvelle tuile */
    int cultureNeededForNewTile = 50;

    /** Liste de l'ensemble des infrastructures construisent dans une ville */
    List<Infrastructure> infrastructures;
    
    /** Liste des tuilles appartenant à la ville */
    List<Tile> cityTiles = new ArrayList<Tile>();

    /**
     * Constructeur d'une ville
     * @param cityPosition position de la ville
     */
    public City(Tile cityPosition) {
        this.position = cityPosition;
        this.cityTiles.add(cityPosition);
        //Il manque l'ajout des tuiles adjacentes à la ville
        this.population = 1;
    }

    // TODO : Faire les méthodes de production d'unité.

    /* ============================== METHODE LIEE AUX TUILLES ============================== */

    private void addTile() {
        
    }


    /* ============================== METHODE LIEE AUX HABITANTS ============================ */

    /** Méthode actualisant la nourriture produit par une ville en rab */
    private void setFoodProduced() {
        this.producedFood += this.foodPerTurnProd - population;
    }

    /** Méthode calculant le nombre de tour nécessaire pour produire un nouvel
     * habitant dans la ville en fonction de la production de nourriture par tour
     * et de la quantité de nourriture nécessaire.
     */
    private int numberTurnForNewCitizen() {
        return (int) (this.foodNeededForNewCitizen / this.foodPerTurnProd - this.producedFood);
    }

    /** Méthode capable de change le nombre d'habitant d'une ville
     * en fonction de la quantité de nourriture disponible.
     */
    public void refreshPopulation() {
        //cas où le rab de nourriture est suffisant pour produire un nouvel habitant
        if (this.producedFood >= this.foodNeededForNewCitizen) {
            this.population++;
            this.foodNeededForNewCitizen += foodNeededForNewCitizenPlus;
        }

        //Cas de la famine : On tue un habitant.
        if (this.producedFood <= foodDebt) {
            this.population--;
            this.producedFood = 0;
        }
    }

    /** Méthode pour obtenir le nombre d'habitants.
     * @return population
     */
    public int getPopulation() {
        return this.population;
    }

    /* ================================================================================ */
    
    /** Méthode permettant de construire une infrastructure dans une ville
     * @param infrastructure infrastructure à construire
     */
    private void buildInfrastructure(Infrastructure infrastructure) {
        this.infrastructures.add(infrastructure);
        this.updatePointPerTurnProd(infrastructure.getFood(), infrastructure.getCulture(), 
        infrastructure.getFaith(), infrastructure.getScience(), infrastructure.getGold(), infrastructure.getProduction());
    }



    /** Méthode actualisant les points de culture produit depuis la dernière expension du territoire
     * @return producedCulure
     */
    private void setProducedCulture() {
        this.producedCulture += this.culturePerTurnProd;
    }

    /** Méthode actualisant la valeurs des points produits par tour par une ville
     * @param foodPoint point de nourriture qu'on ajoute/retire
     * @param culturePoint point de culture qu'on ajoute/retire
     * @param faithPoint point de foi qu'on ajoute/retire
     * @param sciencePoint point de science qu'on ajoute/retire
     * @param goldPoint point d'or qu'on ajoute/retire
     * @param productionPoint point de production qu'on ajoute/retire
     */
    private void updatePointPerTurnProd(int foodPoint, int culturePoint, 
    int faithPoint, int sciencePoint, int goldPoint, int productionPoint) {
        this.foodPerTurnProd += foodPoint;
        this.culturePerTurnProd += culturePoint;
        this.faithPerTurnProd += faithPoint;
        this.sciencePerTurnProd += sciencePoint;
        this.goldPerTurnProd += goldPoint;
        this.productionPerTurnProd += productionPoint;
    }

    /** Méthode renvoyant le nombre de point de culture produit par tour dans une ville
     * @return culturePerTurnProd
     */
    public int getCulturePerTurnProd() {
        return this.culturePerTurnProd;
    }

    public int getGoldPerTurnProd() {
        return this.goldPerTurnProd;
    }

    public int getFaithPerTurnProd() {
        return this.faithPerTurnProd;
    }

    public int getSciencePerTurnProd() {
        return this.sciencePerTurnProd;
    }

    public void renameCity(String newName) {
        this.name = newName;
    }
}
