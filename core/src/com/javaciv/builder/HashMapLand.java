package com.javaciv.builder;

import java.util.HashMap;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.type.LandType;
import com.javaciv.type.ProductionType;

/** Classe permettant de construire une hashmap contenant l'ensemble des tuiles
 *  du jeu et de récupérer une tuile en fonction de son type pour avoir accès aux
 *  statistiques de cette dernière.
 */
public final class HashMapLand {
    
    static HashMap<LandType, Tile> map = new HashMap<LandType, Tile>() {{
        put(LandType.PLAINE, new Tile(
            0, 0,
            LandType.PLAINE,
            true, false,
            new ProductionType(1,0,0,0,0,1),
            2
        ));

        put(LandType.PRAIRIE, new Tile(
            0, 0,
            LandType.PRAIRIE,
            true, false,
            new ProductionType(2,0,0,0,0,0),
            3
        ));

        put(LandType.TOUNDRA, new Tile(
            0, 0,
            LandType.TOUNDRA,
            true, false,
            new ProductionType(1,0,0,0,0,0),
            2
        ));

        put(LandType.DESERT, new Tile(
            0, 0,
            LandType.DESERT,
            true, false,
            new ProductionType(0,0,0,0,0,0),
            1
        ));

        put(LandType.MONTAGNE, new Tile(
            0, 0,
            LandType.MONTAGNE,
            false, false,
            new ProductionType(0,0,0,0,0,0),
            1
        ));

        put(LandType.EAU, new Tile(
            0, 0,
            LandType.EAU,
            true, false,
            new ProductionType(1,0,0,0,1,0),
            2
        ));
    }};
    
    /** Retourne un objet de type Land qui est représente la tuile d'un biome
     * avec ces statistiques
     */
    public static Tile getLand(LandType land) {
        Tile tile = map.get(land);
        
        return new Tile(
            tile.getX(),
            tile.getY(),
            tile.getLand(),
            tile.getIsTraversableByLandUnit(),
            tile.getIsTraversableBySeaUnit(),
            tile.getProduction(),
            tile.getBaseLandValue()
        );
        
        //Copie de la tuile pour éviter les problèmes de références
    }
}
