/**
 * @file ServerThread.java
 * @brief This file contains the ServerThread class.
 * @date 18/04/2024
 */

package com.javaciv;

import com.javaciv.server.Server;
import com.javaciv.gameElement.map.WorldMap;

public class ServerThread extends Thread {
    private Server server;

    /**
     * Constructor of the ServerThread class
     */
    void printMap(WorldMap map) {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                map.at(i, j).print();
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * This function is executed when the thread is started.
     */
    @Override
    public void run() {
        this.server = new Server();
        //printMap(server.getWorldMap());
    }

    /**
     * This function returns the server.
     * @return the server
     */
    public Server getServer() {
        return this.server;
    }
}
