package com.javaciv.builder;
import java.util.HashMap;

import com.javaciv.client.Client;
import com.javaciv.gameElement.Civilian;
import com.javaciv.gameElement.Military;
import com.javaciv.type.UniteType;

/**
 * Classe permettant de construire une hashmap contenant l'ensemble des unités militaires
 * de l'application. 
 */
public class HashMapUnit {

    /** Objet étant un joueur non défini, ainsi on construit la hashmap selon un player
     * non défini.
     */
    static Client undefinedClient = null;

    static HashMap<UniteType, Military> militaryMap = new HashMap<UniteType, Military>() {{
        //Ajout de l'ensemble des unités à la hashmap
        put(UniteType.LANCIER, new Military(
            "Lancier",
            undefinedClient,
            2,
            100,
            10,
            100,
            25
        ));

        put(UniteType.ARCHER, new Military(
            "Archer",
            undefinedClient,
            2,
            140,
            15,
            50,
            10
        ));

        put(UniteType.CHEVALIER, new Military(
            "Chevalier",
            undefinedClient,
            4,
            140,
            10,
            100,
            20
        ));
    }};

    static HashMap<UniteType, Civilian> civilianMap = new HashMap<UniteType, Civilian>() {{
        //Ajout de l'ensemble des unités à la hashmap
        put(UniteType.OUVRIER, new Civilian(
            "Ouvrier",
            undefinedClient,
            2,
            100
        ));

        put(UniteType.COLON, new Civilian(
            "Colon",
            undefinedClient,
            2,
            250
        ));

    }};

    public static Military getMilitary(UniteType unitType) {
        Military unit = militaryMap.get(unitType);

        return new Military(
            unit.getName(),
            unit.getOwner(),
            unit.getBaseActionPoint(),
            unit.getCost(),
            unit.getAttack(),
            unit.getPV(),
            unit.getDefense()
        );
    }

    public static Civilian getCivilian(UniteType unitType) {
        Civilian unit = civilianMap.get(unitType);

        return new Civilian(
            unit.getName(),
            unit.getOwner(),
            unit.getBaseActionPoint(),
            unit.getCost()
        );
    }

}
