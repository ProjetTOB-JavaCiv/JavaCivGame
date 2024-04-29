package com.javaciv.builder;
import java.util.HashMap;

import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Military;
import com.javaciv.gameElement.Player;
import com.javaciv.type.CivilianUniteType;
import com.javaciv.type.MilitaryUniteType;

/**
 * Classe permettant de construire une hashmap contenant l'ensemble des unités militaires
 * de l'application. 
 */
public class HashMapUnitBuilder {

    /** Objet étant un joueur non défini, ainsi on construit la hashmap selon un player
     * non défini.
     */
    static Player undefinedPlayer = new Player("undefined");

    static HashMap<MilitaryUniteType, Military> buildHashMapMilitary() {
        HashMap<MilitaryUniteType, Military> map = new HashMap<MilitaryUniteType, Military>();

        //Ajout de l'ensemble des unités à la hashmap
        map.put(MilitaryUniteType.LANCIER, new Military(
            "Lancier",
            undefinedPlayer,
            2,
            100,
            10,
            100,
            25
        ));

        map.put(MilitaryUniteType.ARCHER, new Military(
            "Archer",
            undefinedPlayer,
            2,
            140,
            15,
            50,
            10
        ));

        map.put(MilitaryUniteType.CHEVALIER, new Military(
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

    static HashMap<CivilianUniteType, Civilian> buildHashMapCivilan() {
        HashMap<CivilianUniteType, Civilian> map = new HashMap<CivilianUniteType, Civilian>();

        //Ajout de l'ensemble des unités à la hashmap
        map.put(CivilianUniteType.OUVRIER, new Civilian(
            "Ouvrier",
            undefinedPlayer,
            2,
            100
        ));

        map.put(CivilianUniteType.COLON, new Civilian(
            "Colon",
            undefinedPlayer,
            2,
            250
        ));

        return map;
    }
}
