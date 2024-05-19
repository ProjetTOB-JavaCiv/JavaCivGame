package com.javaciv.builder;

import java.util.HashMap;
import com.javaciv.gameElement.Infrastructure;
import com.javaciv.type.InfrastructureType;

/** Classe "statique" représentant une hashMap de l'ensemble des batiment que l'on peut
 * construire dans une ville. Cette classe permet de récupérer de nombreux objet de la
 * classe "Infrastructure" en les appelant par leur nom défini par l'énumération
 * "Infrastructure".
 * Cette classe est specifique a chaque joueuer et est amenée a evlouer au cours d'une
 * partie au fur et a mesure que le joueur decouvre des technologies
 */
public class HashMapInfrastructure {
    
    public HashMap<InfrastructureType, Infrastructure> map = new HashMap<InfrastructureType, Infrastructure>();

    public HashMapInfrastructure() {
        map.put(InfrastructureType.GRENIER, new Infrastructure(
        200, 
        2, 
        0, 
        0, 
        0, 
        0,
        0));

        map.put(InfrastructureType.MONUMMENT_HISTORIQUE, new Infrastructure(
        150, 
        0, 
        3, 
        1, 
        0, 
        0,
        0));

        map.put(InfrastructureType.ATELIER, new Infrastructure(
        250, 
        2, 
        0, 
        0, 
        0, 
        0,
        3));
    }

    /** Méthode permettant de récupérer le batiment que l'on souhaite
     * Exemple d'utilisation: recupérer un grenier -> getInfrastructure(InfrastructureType.GRENIER)
     * @param infrastructure le batiment que l'on souhaite récupérer
     */
    public Infrastructure getInfrastructure(InfrastructureType infrastructure) {
        Infrastructure instrastructure = map.get(infrastructure);

        return new Infrastructure(
            instrastructure.getProductionCost(),
            instrastructure.getFood(),
            instrastructure.getProduction(),
            instrastructure.getScience(),
            instrastructure.getGold(),
            instrastructure.getCulture(),
            instrastructure.getFaith()
        );
    }
}
