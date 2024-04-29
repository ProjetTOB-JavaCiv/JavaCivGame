package com.javaciv.gameElement;

import java.util.ArrayList;
import java.util.List;
import com.javaciv.server.Tile;

/**
 * Classe représentant une ville dans le jeu.
 * Une ville est capable de produire des ressources, de construire des unités et des batiments.
 * Une ville possède un certain nombre d'habitant qu'elle peut 
 */
public class City {

    /** Position de la ville */
    Tile position;
    
    /** Nombre de points de science produit par une ville chaque tour */
    int sciencePerTurnProd;
    /** Nombre de points de culture produit par une ville chaque tour */
    int culturePerTurnProd;
    /** Nombre de points de foi produit par une ville chaque tour */
    int faithPerTurnProd;
    /** Nombre de gold produit par une ville chaque tour */
    int goldPerTurnProd;
    /** Nombre de points de production porduit par une ville chaque tour */
    int prodPerTurnProd;

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

    //TODO : Ajouter des batiments.
    //Build infrastructure;

    /** Liste des tuilles appartenant à la ville */
    List<Tile> cityTiles = new ArrayList<Tile>();

    /**
     * Constructeur d'une ville
     * @param position position de la ville
     */
    public City(Tile cityPosition) {
        this.position = cityPosition;
        this.cityTiles.add(cityPosition);
        //Il manque l'ajout des tuiles adjacentes à la ville
        this.population = 1;
    }

    //TODO : Ecrire les méthodes d'actualisation des ressources prod par tour en fonction des batiments et des tuilles

    /** Méthode actualisant la nourriture produit par une ville en rab */
    private void setFoodProductedPerTurn() {
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
    private void refreshPopulation() {
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

    public int getCulturePerTurnProd() {
        return this.culturePerTurnProd;
    }

    public int getGoldPerTurnProd() {
        return this.goldPerTurnProd;
    }

    public int getFaithPerTurnProd() {
        return this.faithPerTurnProd;
    }

}
