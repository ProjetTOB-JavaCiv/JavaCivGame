package com.javaciv.builder;

import java.util.HashMap;
import com.javaciv.gameElement.Infrastructure;
import com.javaciv.type.InfrastructureType;

/** Classe "statique" représentant une hashMap de l'ensemble des batiment que l'on peut
 * construire dans une ville. Cette classe permet de récupérer de nombreux objet de la
 * classe "Infrastructure" en les appelant par leur nom défini par l'énumération
 * "Infrastructure".
 */
public final class HashMapInfrastructure {
    
    static HashMap<InfrastructureType, Infrastructure> map = new HashMap<InfrastructureType, Infrastructure>();

    private HashMapInfrastructure() {
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
    public static Infrastructure getInfrastructure(InfrastructureType infrastructure) {
        return map.get(infrastructure);
    }
}
