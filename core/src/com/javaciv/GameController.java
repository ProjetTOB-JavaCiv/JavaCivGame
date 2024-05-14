/**
 * @file GameController.java
 * @brief This file contains the GameController class.
 * @author Théo Bessel
 * @date 21/04/2024
 * @version 1.0
 */

package com.javaciv;

import com.badlogic.gdx.Game;

import com.javaciv.client.Client;
import com.javaciv.client.ClientController;
import com.javaciv.client.ClientView;

public class GameController extends Game {
	
	private Client client;
	private ClientController controller;
	private ClientView view;

	public GameController(Client client) {

		this.client = client;
		this.controller = new ClientController(client);
	}

	public Client getClient() {
		return this.client;
	}

	/**
	 * This function is executed when the application is created.
	 */
	@Override
	public void create() {
		this.setScreen(new ClientView(controller, client.getWorldMap()));
	}

	/**
	 * This function is executed at each frame to render the game.
	 */
	@Override
	public void render() {
		super.render(); // On appelle la fonction render de screen
	}
	
	/**
	 * This function is executed when the application is destroyed.
	 */
	@Override public void dispose() {}
}
