package com.javaciv;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javaciv.client.Client;
import com.javaciv.client.ClientController;
import com.javaciv.client.ClientView;

public class JavaCiv extends Game {
	
	private Client client;
	private ClientController controller;
	private ClientView view;

	private SpriteBatch tileBatch;
	private Texture[] tileTextures;

    private int renderDistance = 40;
    private int zoom = 5;
    private int moveSpeed = 2000;

	public JavaCiv(Client client) {

		this.client = client;
		this.controller = new ClientController(client);
		//this.view = ;
	}

	public Client getClient() {
		return this.client;
	}

	/**
	 * This function is executed when the application is created.
	 */
	@Override
	public void create() {
		this.tileBatch = new SpriteBatch();
		this.setScreen(new ClientView(controller, client.getWorldMap(), this.tileBatch, tileTextures, renderDistance, zoom, moveSpeed));
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
