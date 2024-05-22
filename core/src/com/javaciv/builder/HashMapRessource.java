package com.javaciv.builder;

import java.util.HashMap;
import com.javaciv.gameElement.map.Ressource;
import com.javaciv.type.RessourceType;
import com.javaciv.type.ProductionType;

/** Classe permettant de construire une hashmap contenant l'ensemble des ressources
 * pour acceder à leur statistiques
 */
public final class HashMapRessource {

    static HashMap<RessourceType, Ressource> map = new HashMap<RessourceType, Ressource>() {{
        put(RessourceType.BASE, new Ressource(RessourceType.BASE, 
                                                new ProductionType(0, 0, 0, 0, 0, 0)));
        put(RessourceType.CRABE, new Ressource(RessourceType.CRABE, 
                                                new ProductionType(0, 0, 0, 0, 2, 0)));
        put(RessourceType.BETAIL, new Ressource(RessourceType.BETAIL, 
                                                new ProductionType(1, 0, 0, 0, 0, 0)));
        put(RessourceType.BLE, new Ressource(RessourceType.BLE, 
                                                new ProductionType(1, 0, 0, 0, 0, 0)));
        put(RessourceType.CERVIDE, new Ressource(RessourceType.CERVIDE, 
                                                new ProductionType(0, 0, 0, 0, 0, 1)));
        put(RessourceType.CACAO, new Ressource(RessourceType.CACAO, 
                                                new ProductionType(0, 0, 1, 0, 0, 0)));
    }};

    /**
     * Permet de recuperer une ressource a mettre sur une tuile.
     * @param r le type de la ressource
     * @return l'objet representant la ressource
     */
    public static Ressource getRessource(RessourceType r) {
        return map.get(r);
    }

}
