package com.javaciv;

import com.javaciv.server.Server;
import com.javaciv.server.WorldMap;

public class ServerThread extends Thread {
    private Server server;

    void printMap(WorldMap map) {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                map.at(i, j).print();
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        this.server = new Server();
        //printMap(server.getWorldMap());
    }

    public Server getServer() {
        return this.server;
    }
}
