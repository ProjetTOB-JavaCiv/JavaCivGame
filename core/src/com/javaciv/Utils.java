package com.javaciv;

import java.util.Random;

public interface Utils {
    /**
     * Cette fonction renvoie un entier aléatoire entre 0 et max.
    */
    public static int randomInt(int max) {
        Random rd = new Random();
        return rd.nextInt(max);
    }
}

