package com.javaciv.builder;

import java.util.HashMap;
import com.javaciv.gameElement.Infrastructure;
import com.javaciv.type.InfrastructureType;

public class HashMapInfrastuctureBuilder {
    
    public static HashMap<InfrastructureType, Infrastructure> buildHashMapInfrastructure() {

        HashMap<InfrastructureType, Infrastructure> map = new HashMap<InfrastructureType, Infrastructure>();

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

        // TODO : Ajout des batiments avec des bonus

        return map;

    }
}
