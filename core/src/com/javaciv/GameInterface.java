/**
 * @file GameInterface.java
 * @brief This file contains the GameInterface interface.
 * @author Théo Bessel
 * @date 21/04/2024
 * @version 1.0
 */

package com.javaciv;

import com.javaciv.gameElement.map.WorldMap;

/**
 * Cette interface représente un jeu
 */
public interface GameInterface {
    WorldMap getWorldMap();
}
