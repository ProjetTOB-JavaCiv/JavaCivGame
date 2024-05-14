/**
 * @file Utils.java
 * @brief This file contains the Utils interface.
 * @author Théo Bessel
 * @date 23/04/2024
 * @version 1.0
 */


package com.javaciv;

import java.util.Random;
import java.util.Map;

public interface Utils {
    /**
     * Cette fonction renvoie un entier aléatoire entre min et max.
     * @param min la borne inférieure
     * @param max la borne supérieure
     * @return un entier aléatoire entre min et max
    */
    public static int randomInt(int min, int max) {
        assert(min <= max);
        Random rd = new Random();
        if (max > min) {
            return min + rd.nextInt(max - min);
        } else {
            return min;
        }
    }

    /**
     * Cette fonction renvoie un entier aléatoire entre 0 et max.
     * @param max la borne supérieure
     * @return un entier aléatoire entre 0 et max
    */
    public static int randomInt(int max) {
        return randomInt(0, max);
    }

    /**
     * Cette fonction renvoie un entier aléatoire en fonction d'une distribution de probabilités donnée.
     * @param distribution la distribution de probabilités donnée
     * Cette distribution est une map qui associe un entier à une probabilité.
     * Par exemple, si la distribution est {0: 0.3, 1: 0.1, 2: 0.1, 3: 0.1, 4: 0.2, 5: 0.2},
     * alors la fonction renverra 0 avec une probabilité de 0.3, 1 avec une probabilité de 0.1, etc.
     * @return un entier aléatoire en fonction de la distribution de probabilités donnée
     */
    public static int randomInt(Map<Integer, Double> distribution) {
        double randomDouble = Math.random();
        double sum = 0;
        for (Object key : distribution.keySet()) {
            sum += distribution.get(key);
            if (randomDouble < sum) {
                return (int) key;
            }
        }
        return -1;
    }
}

