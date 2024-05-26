package com.javaciv.builder;

import java.util.HashMap;
import com.javaciv.gameElement.map.Feature;
import com.javaciv.type.FeatureType;
import com.javaciv.type.ProductionType;

/** Classe permettant de construire une hashmap contenant l'ensemble des features
 * de tuile du jeu
 */
public final class HashMapFeature {
    
    static HashMap<FeatureType, Feature> map = new HashMap<FeatureType, Feature>() {{
        put(FeatureType.BASE, new Feature(FeatureType.BASE,
            new ProductionType(
                0,
                0,
                0,
                0,
                0,
                0
            ),
            0,
            0
        ));

        put(FeatureType.WOODS, new Feature(FeatureType.WOODS,
            new ProductionType(
                0,
                0,
                0,
                0,
                0,
                1
            ),
            1,
            3
        ));

        put(FeatureType.MARSH, new Feature(FeatureType.MARSH,
            new ProductionType(
                1,
                0,
                0,
                0,
                0,
                0
            ),
            1,
            -2
        ));

        put(FeatureType.OASIS, new Feature(FeatureType.OASIS,
            new ProductionType(
                3,
                0,
                0,
                0,
                1,
                0
            ),
            0,
            0
        ));

        put(FeatureType.RAINFOREST, new Feature(FeatureType.RAINFOREST,
            new ProductionType(
                1,
                0,
                0,
                0,
                0,
                0
            ),
            1,
            3
        ));
    }};

    /**
     * Permet d'obtenir une feature en fonction de son type
     * @param f le type de la feature
     * @return la feature
     */
    public static Feature getFeature(FeatureType f) {
        return map.get(f);
    }
}

//FLOODPLAIN(0), ICE(1), MARSH(2), OASIS(3), RAINFOREST(4), WOODS(5)
