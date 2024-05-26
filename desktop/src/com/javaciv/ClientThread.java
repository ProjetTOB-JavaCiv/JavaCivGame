/**
 * @file ClientThread.java
 * @brief This file contains the ClientThread class.
 * @date 15/05/2024
 */

package com.javaciv;

import com.javaciv.client.Client;
import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.Utils;

public class ClientThread extends Thread {
    private Client client;

    /**
     * Constructor of the ClientThread class
     * @param client the client
     */
    public ClientThread(Client client) {
        this.client = client;
    }

    /**
     * This function is executed when the thread is started.
     */
    @Override
    public void run() {
        while (true) {
            try {
                // Sleep to simulate the time of the other players (1sec is okay for testing purpose)
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.client.canPassTurn()) {
                // Display the client's points
                System.out.println("==================================");
                System.out.println("[Client " + this.client.getClientId() + "]");
                System.out.println("Gold: " + this.client.getGoldPoint());
                System.out.println("Culture: " + this.client.getCulturePoint());
                System.out.println("Science: " + this.client.getSciencePoint());
                System.out.println("Faith: " + this.client.getFaithPoint());
                System.out.println("==================================");
                // Place random city on the map and pass the turn
                this.client.createCity(this.client.getWorldMap().at(
                    Utils.randomInt(0, this.client.getWorldMap().getWidth()),
                    Utils.randomInt(0, this.client.getWorldMap().getHeight())
                ));
                this.client.nextTurn();
            }
        }
    }
}
