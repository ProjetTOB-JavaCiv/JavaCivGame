package com.javaciv.builder;
import java.util.HashMap;

import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Military;
import com.javaciv.gameElement.Player;
import com.javaciv.type.UniteType;
import com.javaciv.type.UniteType;

/**
 * Classe permettant de construire une hashmap contenant l'ensemble des unités militaires
 * de l'application. 
 */
public class HashMapUnitBuilder {

    /** Objet étant un joueur non défini, ainsi on construit la hashmap selon un player
     * non défini.
     */
    static Player undefinedPlayer = new Player("undefined");

    static HashMap<UniteType, Military> buildHashMapMilitary() {
        HashMap<UniteType, Military> map = new HashMap<UniteType, Military>();

        //Ajout de l'ensemble des unités à la hashmap
        map.put(UniteType.LANCIER, new Military(
            "Lancier",
            undefinedPlayer,
            2,
            100,
            10,
            100,
            25
        ));

        map.put(UniteType.ARCHER, new Military(
            "Archer",
            undefinedPlayer,
            2,
            140,
            15,
            50,
            10
        ));

        map.put(UniteType.CHEVALIER, new Military(
            "Chevalier",
            undefinedPlayer,
            4,
            140,
            10,
            100,
            20
        ));

        return map;
    }

    static HashMap<UniteType, Civilian> buildHashMapCivilan() {
        HashMap<UniteType, Civilian> map = new HashMap<UniteType, Civilian>();

        //Ajout de l'ensemble des unités à la hashmap
        map.put(UniteType.OUVRIER, new Civilian(
            "Ouvrier",
            undefinedPlayer,
            2,
            100
        ));

        map.put(UniteType.COLON, new Civilian(
            "Colon",
            undefinedPlayer,
            2,
            250
        ));

        return map;
    }
}
