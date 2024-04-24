package com.javaciv;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.javaciv.client.Client;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		/**
		 * The following lines are used to create the configuration of the application
		 * and to create this last with the JavaCiv object.
		 * //TODO : Move this to a Client class with a createClient method.
		 */
		
		
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("JavaCiv");

		ServerThread thread = new ServerThread();
		thread.start();

		// TODO : Replace lines below by a clean wait for the server to be ready
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Client client1 = new Client(thread.getServer());
		Client client2 = new Client(thread.getServer());
		Client client3 = new Client(thread.getServer());

		//System.out.println("Client1 world map is server world map : " + (client1.getWorldMap() == thread.getServer().getWorldMap()));
		//System.out.println("Client2 world map is server world map : " + (client2.getWorldMap() == thread.getServer().getWorldMap()));

		new Lwjgl3Application(new JavaCiv(client3), config);
	}
}
