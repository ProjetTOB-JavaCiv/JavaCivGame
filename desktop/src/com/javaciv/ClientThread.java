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
                Thread.sleep(1000);
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
