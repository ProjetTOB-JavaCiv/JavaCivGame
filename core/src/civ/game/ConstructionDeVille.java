package game;

/**
 * Interface ConstructionDeVille permettant de regrouper tout ce qui peut etre construit au 
 * sein du menu ville.
 * @author Theophane Chollet
 * @version 0.0
 */
public interface ConstructionDeVille {

    /**
     * permet d'obtenir le montant de ressource "materiel" etant necessaire a sa construction.
     * @return le montant de ressource "materiel" etant necessaire a sa construction.
     */
    int getCout();
    
}
