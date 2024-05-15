/**
 * @file ClientThread.java
 * @brief This file contains the ClientThread class.
 * @author Théo Bessel
 * @date 15/05/2024
 * @version 1.0
 */

package com.javaciv;

import com.javaciv.client.Client;
import com.javaciv.gameElement.map.WorldMap;

public class ClientThread extends Thread {
    private Client client;

    public ClientThread(Client client) {
        this.client = client;
    }

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
                System.out.println("==================================");
                System.out.println("[Client " + this.client.getClientId() + "]");
                System.out.println("Gold: " + this.client.getGoldPoint());
                System.out.println("Culture: " + this.client.getCulturePoint());
                System.out.println("Science: " + this.client.getSciencePoint());
                System.out.println("Faith: " + this.client.getFaithPoint());
                System.out.println("==================================");
                this.client.nextTurn();
            }
        }
    }
}
