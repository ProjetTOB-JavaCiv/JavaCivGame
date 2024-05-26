package com.javaciv.type;

/**
 * Cette énumération représente un type de terrain pour les tuiles
 */
public enum LandType {
    PLAINE(0), DESERT(1), PRAIRIE(2), MONTAGNE(3), TOUNDRA(4), EAU(5);

    int land = 0;

    /**
     * Constructeur d'un type de terrain
     * @param i un entier, le type de terrain
     */
    LandType(int i) {
        this.land = i;
    }

    /**
     * Obtenir le type de terrain
     * @return un entier, le type de terrain
     */
    public int toInt() {
        return land;
    }

    /**
     * Obtenir le type de terrain sous forme de lettre
     * @return une chaîne de caractères, le type de terrain sous forme de lettre
     */
    public String toFirstLetter() {
        return this.toString().substring(0, 1);
    }
}
