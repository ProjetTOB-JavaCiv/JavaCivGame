package com.javaciv.builder;

import java.util.HashMap;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.type.LandType;

/** Classe permettant de construire une hashmap contenant l'ensemble des tuiles
 *  du jeu et de récupérer une tuile en fonction de son type pour avoir accès aux
 *  statistiques de cette dernière.
 */
public final class HashMapLand {
    
    static HashMap<LandType, Tile> map = new HashMap<LandType, Tile>() {{
        put(LandType.PLAINE, new Tile(
            0, 
            0, 
            LandType.PLAINE, 
            true, 
            false, 
            2, 
            0, 
            0, 
            0, 
            0, 
            0, 
            2
        ));

        put(LandType.COLLINE, new Tile(
            0, 
            0, 
            LandType.COLLINE, 
            true, 
            false, 
            1, 
            0, 
            0, 
            0, 
            0, 
            3, 
            3
        ));

        put(LandType.FORET, new Tile(
            0, 
            0, 
            LandType.FORET, 
            true, 
            false, 
            1, 
            0, 
            0, 
            1, 
            0, 
            2, 
            2
        ));

        put(LandType.DESERT, new Tile(
            0, 
            0, 
            LandType.DESERT, 
            true, 
            false, 
            0, 
            0, 
            0, 
            0, 
            1, 
            0, 
            1
        ));

        put(LandType.MONTAGNE, new Tile(
            0, 
            0, 
            LandType.MONTAGNE, 
            false, 
            false, 
            0, 
            0, 
            2, 
            2, 
            0, 
            0, 
            1
        ));

        put(LandType.MER, new Tile(
            0, 
            0, 
            LandType.MER, 
            true, 
            false, 
            1, 
            0, 
            0, 
            0, 
            1, 
            0, 
            2
        ));
    }};
    
    /** Retourne un objet de type Land qui est représente la tuille d'un biome
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
            tile.getFood(),
            tile.getCulture(),
            tile.getFaith(),
            tile.getScience(),
            tile.getGold(),
            tile.getProduction(),
            tile.getBaseLandValue()
        );
        

        //Copie de la tuile pour éviter les problèmes de références
        

    }
}
