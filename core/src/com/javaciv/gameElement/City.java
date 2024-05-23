package com.javaciv.gameElement;

import java.util.ArrayList;
import java.util.List;

import com.javaciv.client.Client;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.map.WorldMap;

/**
 * Classe représentant une ville dans le jeu.
 * Une ville est capable de produire des ressources, de construire des unités et des batiments.
 * Une ville possède un certain nombre d'habitant qu'elle peut 
 */
public class City {

    /** Tuille ou se situe la ville */
    Tile position;
    /** Coordonnée x de la ville */
    int x;
    /** Coordonnée y de la ville */
    int y;
    /** Nom de la ville */
    String name = "ville";
    /** Faction ayant le contrôle sur la ville */
    Client owner;
    /** Point de vie de la ville */
    int health = 100;
    /** Puissance d'attaque de la ville */
    int attack = 10;
    /** Rayon max de l'extension du territoire de la ville */
    double territoryRange = 5.0;

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
    int foodNeededForNewCitizenPlus = 25 + 5 * this.population;
    /** Nombre de nourriture en dette autorisé avant de tuer un citoyen en cas de dette */
    final int foodDebt = -5;

    /** Culture produite par une ville depuis lfoodNeededForNewCitizenPlusa dernière extension de territoire */
    int producedCulture = 0;
    /** Nombre de point de culture nécessaire pour obtenir une nouvelle tuile */
    int cultureNeededForNewTile = 50;

    /** Liste de l'ensemble des infrastructures construisent dans une ville */
    List<Infrastructure> infrastructures = new ArrayList<Infrastructure>();
    
    /** Liste des tuiles appartenant à la ville */
    List<Tile> cityTiles = new ArrayList<Tile>();
    /** Liste des tuiles voisines au territoire de la ville */
    List<Tile> neighbourTiles = new ArrayList<Tile>();

    //TODO : Ajouter le fait de prendre en compte les bordures pour faire les textures du territoire de la ville
    ///** Liste des tuiles qui sont des bordures de la ville */
    //List<String> borderSides = new ArrayList<String>(); // "top", "bottom", "left", "right"

    /**
     * Constructeur d'une ville
     * @param cityPosition position de la ville
     */
    public City(Tile cityPosition, Client owner) {
        this.position = cityPosition;
        this.owner = owner;
        
        this.x = this.position.getX();
        this.y = this.position.getY();
        //Ajout des cases appartenant à la ville : La tuile centrale
        this.cityTiles.add(cityPosition);

        // Ajout des cases voisines à la ville
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(i == 0 && j == 0) continue; // Skip the center tile
                Tile newTile = this.owner.getWorldMap().at(x + i, y + j);
                this.cityTiles.add(newTile);
                addNeighbourTiles(newTile);
            }
        }
        this.population = 1;
    }

    // TODO : Faire les méthodes de production d'unité.

    /* ============================== METHODE LIEE AUX TUILLES ============================== */

    public void checkForNewTile(){
        if(this.producedCulture >= this.cultureNeededForNewTile) {
            this.addTile();
            this.cultureNeededForNewTile += 20;
            this.producedCulture = 0;
        }
    }
 
    private void addTile() {
        //Tuille candidate à l'expension du territoire
        Tile possibleTile;
        Tile chosenTile = null;
        int indexChosenTile = 0;
        double maxTileStrategicValue = 0;

        //Coefficient de réduction de la valeur d'une tuille en fonction de la distance entre la tuile et le centre ville
        double coeffReductionDist = 0.3;

        /** Variable qui donne la valeur stratégique d'une tuile en fonction de sa valeur de base ET
         * de sa distance par rapport à la ville. L'objectif est d'éviter d'avoir des villes qui se répendent à
         * l'infini vers une direction.
         */
        double localTileStrategicValue;

        //On ajoute les tuilles adjacentes à la ville en checkant l'état d'occupation des voisins de toutes les tuilles.
        for (int i = 0; i < neighbourTiles.size(); i++){

            possibleTile = neighbourTiles.get(i);

            //Formule un peu compliqué pour juste réduire la valeur strategique d'une tuile plus elle est loin.
            localTileStrategicValue = possibleTile.getBaseLandValue() 
            - this.position.distance(owner.getWorldMap().at(possibleTile.getX(), possibleTile.getY()))* coeffReductionDist;

            if(localTileStrategicValue > maxTileStrategicValue && possibleTile.getOwner() == null) {

                maxTileStrategicValue = possibleTile.getBaseLandValue();
                chosenTile = possibleTile;
                indexChosenTile = i;
                
            }
        }

        if(chosenTile != null) {
            chosenTile.setOwner(owner);
            neighbourTiles.remove(indexChosenTile);
            addNeighbourTiles(chosenTile);
        }

    }

    /**Méthode permettant d'ajouter les voisins d'une tuille à la liste des tuilles voisine à
     * la frontière de la ville pour une potentielle ecityPositionxtension future, à la condition de ne pas
     * être déjà posédé par une autre ville.
     * @param tile tuille dont on veut ajouter les voisins
     */
    private void addNeighbourTiles(Tile tile) {
        //Récupération de la map
        WorldMap map = this.owner.getWorldMap();

        //Ajout des cases voisines à la ville
        Tile leftTile = map.at(this.x - 1, this.y);
        Tile rightTile = map.at(this.x + 1, this.y);
        Tile downTile = map.at(this.x, this.y - 1);
        Tile upTile = map.at(this.x, this.y + 1);


        if(this.x - 1 >= 0 && leftTile.distance(this.position) < this.territoryRange) {
            neighbourTiles.add(leftTile);
        }
        //Case de droite
        if(this.x + 1 <= map.getWidth() && rightTile.distance(this.position) < this.territoryRange) {
            neighbourTiles.add(rightTile);
        }
        //Case du dessus
        if(this.y - 1 >= 0 && downTile.distance(this.position) < this.territoryRange) {
            neighbourTiles.add(downTile);
        }
        //Case du dessous
        if(this.y + 1 <= map.getHeight() && upTile.distance(this.position) < this.territoryRange) {
            neighbourTiles.add(upTile);
        }
    }


    /* ============================== METHODE LIEE AUX HABITANTS ============================ */

    /** Méthode actualisant la nourriture produit par une ville en rab */
    private void setFoodProduced() {
        this.producedFood += this.foodPerTurnProd - this.population;
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
            this.foodNeededForNewCitizen += this.foodNeededForNewCitizenPlus;
        }

        //Cas de la famine : On tue un habitant.
        if (this.producedFood <= this.foodDebt) {
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
    public void buildInfrastructure(Infrastructure infrastructure) {
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

    public String getName() {
        return this.name;
    }
    
    public Client getOwner() {
        return this.owner;
    }

    public Tile getPosition() {
        return this.position;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public List<Tile> getCityTiles() {
        return this.cityTiles;
    }

    public List<Tile> getNeighbourTiles() {
        return this.neighbourTiles;
    }

    public int getProducedCulture() {
        return this.producedCulture;
    }

    public int getCultureNeededForNewTile() {
        return this.cultureNeededForNewTile;
    }
}
