package com.javaciv.client;

import java.util.HashMap;
import java.util.List;

import com.javaciv.gameElement.map.WorldMap;
import com.javaciv.gameElement.map.Tile;
import com.javaciv.gameElement.City;
import com.javaciv.GameInterface;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;


public class ClientController extends InputAdapter {

    private ClientView clientView;

    public ClientController(ClientView clientView) {
        this.clientView = clientView;
    }

    private Client client;

    private Vector2 movement = new Vector2(0, 0);

    private float zoom = 1.0f;

    private boolean displayTileMenu = false;

    private boolean displayCityMenu = false;

    private Vector2 coordinates = new Vector2(0, 0);

    private boolean buyTile = false;

    private City selectedCity;

    /**
     * This is the constructor of the ClientController class.
     * @param client the client
     */
    public ClientController(Client client) {
        this.client = client;
    }

    /**
     * This function handles the key down event.
     */
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                this.move(new Vector2(0, 1));
                break;
            case Input.Keys.DOWN:
                this.move(new Vector2(0, -1));
                break;
            case Input.Keys.LEFT:
                this.move(new Vector2(-1, 0));
                break;
            case Input.Keys.RIGHT:
                this.move(new Vector2(1, 0));
                break;
            /*case Input.Keys.Q:
                this.zoom = 0.01f;
                break;
            case Input.Keys.W:
                this.zoom = -0.01f;
                break;*/
            default:
                //System.out.println(keycode + " pressed");
                break;
        }
        return false;
    }

    /**
     * This function handles the key up event.
     */
    @Override
    public boolean keyUp (int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                this.stop(new Vector2(0, 1));
                break;
            case Input.Keys.DOWN:
                this.stop(new Vector2(0, -1));
                break;
            case Input.Keys.LEFT:
                this.stop(new Vector2(-1, 0));
                break;
            case Input.Keys.RIGHT:
                this.stop(new Vector2(1, 0));
                break;
            /*case Input.Keys.Q:
                this.zoom = 0.0f;
                break;
            case Input.Keys.W:
                this.zoom = 0.0f;
                break;*/
            default:
                //System.out.println(keycode + " released");
                break;
        }
        return false;
    }

    /**
     * This function handles the touch down event.
     */
    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        this.coordinates = new Vector2(x, y);
        if (button == Buttons.RIGHT){
            //System.out.println("Touch down at (" + x + ", " + y + ")");

            this.coordinates = new Vector2(x, y);
            //Information sur la tuile que l'on a cliqué
            clientView.openTileMenuAt(this.coordinates);
            displayTileMenu = true;

            return false;
        } else {
            if (this.buyTile) {
                if (this.client.buyItem(10, 0, 0, 0)) {
                    this.buyTile = false;
                    this.selectedCity.addCityTile(this.clientView.getTileAt(this.clientView.getClickCoordinates()));
                    this.clientView.updateMap();
                }
            } else {
                this.clientView.closeAllSelectedTiles();
                displayTileMenu = false;
            }

            return false;
        }
    }

    /**
     * This function handles the scrolled event.
     */
    @Override
    public boolean scrolled (float amountX, float amountY) {
        //displayTileMenu = false;
        if (amountY > 0.7 && amountY < 14) {
            this.zoom = 1.06f;
        } else if (amountY < -0.7 && amountY > -14) {
            this.zoom = 0.94f;
        } else {
            this.zoom = 1.0f;
        }
        return false;
    }

    /**
     * This function moves the camera.
     * @param movement the movement
     */
    private void move(Vector2 movement) {
        Vector2 newMovement = new Vector2(0, 0);

        newMovement.x = this.getMovement().x + movement.x;
        newMovement.y = this.getMovement().y + movement.y;

        this.movement = newMovement.nor();
    }

    /**
     * This function stops the camera movement.
     * @param movement the movement
     */
    private void stop(Vector2 movement) {
        Vector2 newMovement = new Vector2(0, 0);

        newMovement.x = this.getMovement().x - movement.x * Math.abs(this.getMovement().x);
        newMovement.y = this.getMovement().y - movement.y * Math.abs(this.getMovement().y);

        this.movement = newMovement.nor();
    }

    /**
     * This function sets the client view.
     * @param clientView the client view
     */
    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    /**
     * This function returns the zoom.
     * @return the zoom
     */
    public float getZoom() {
        return this.zoom;
    }

    /**
     * This function sets the zoom.
     * @param zoom the zoom
     */
    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    /**
     * This function returns the movement.
     * @return the movement
     */
    public Vector2 getMovement() {
        return this.movement;
    }

    /**
     * This function returns if the tile menu is displayed.
     * @return true if the tile menu is displayed, false otherwise
     */
    public boolean getDisplayTileMenu() {
        return this.displayTileMenu;
    }

    /**
     * This function returns if the city menu is displayed.
     * @return true if the city menu is displayed, false otherwise
     */
    public boolean getDisplayCityMenu() {
        return this.displayCityMenu;
    }

    /**
     * This function returns the click coordinates.
     * @return the click coordinates
     */
    public Vector2 getClickCoordinates() {
        return this.coordinates;
    }

    /**
     * This function sets the display tile menu.
     * @param displayTileMenu the display tile menu
     */
    public void setDisplayTileMenu(boolean displayTileMenu) {
        this.displayTileMenu = displayTileMenu;
    }

    /**
     * This function sets the display city menu.
     * @param displayCityMenu the display city menu
     */
    public void setDisplayCityMenu(boolean displayCityMenu) {
        this.displayCityMenu = displayCityMenu;
    }

    /**
     * This function sets the server.
     */
    public GameInterface getServer() {
		return this.client.getServer();
	}

    /**
     * This function returns the game infos.
     * @return the game infos
     */
    public HashMap<String, String> getGameInfos() {
        HashMap<String, String> gameInfos = new HashMap<String, String>();
        gameInfos.put("gold", this.intToStringNotation(this.client.getGoldPoint()));
        gameInfos.put("culture", this.intToStringNotation(this.client.getCulturePoint()));
        gameInfos.put("science", this.intToStringNotation(this.client.getSciencePoint()));
        gameInfos.put("faith", this.intToStringNotation(this.client.getFaithPoint()));
        return gameInfos;
    }

    /**
     * This function returns the gold points infos.
     * @return the gold points infos
     */
    public int getGoldPoint() {
        return this.client.getGoldPoint();
    }

    /**
     * This function returns the culture points infos.
     * @return the culture points infos
     */
    public int getCulturePoint() {
        return this.client.getCulturePoint();
    }

    /**
     * This function returns the science points infos.
     * @return the science points infos
     */
    public int getSciencePoint() {
        return this.client.getSciencePoint();
    }

    /**
     * This function returns the faith points infos.
     * @return the faith points infos
     */
    public int getFaithPoint() {
        return this.client.getFaithPoint();
    }

    /**
     * This function converts an integer to a string notation.
     * @param number the number
     */
    private String intToStringNotation(int number) {
        String[] suffixes = new String[] { "", "K", "M", "B", "T", "Q" };
        int suffixIndex = 0;
        while (number >= 1000) {
            number /= 1000;
            suffixIndex++;
        }
        return number + suffixes[suffixIndex];
    }

    /**
     * This function passes the turn.
     */
    public void nextTurn() {
        this.client.nextTurn();
    }

    /**
     * This function returns the current client.
     * @return the current client
     */
    public int getCurrentClient() {
        return this.client.getClientId();
    }

    /**
     * This function returns the world map.
     * @return the world map
     */
    public WorldMap getWorldMap() {
        return this.client.getWorldMap();
    }

    /**
     * This function adds a city on a tile.
     * @param tile the tile
     * @return true if the city has been added, false otherwise
     */
    public boolean addCity(Tile tile) {
        return this.client.createCity(tile);
    }

    /**
     * This function returns the cities.
     * @return the cities
     */
    public List<City> getCities() {
        return this.client.getCities();
    }

    /**
     * This function add a tile to a city.
     * @param city the city
     */
    public void buyTile(City city) {
        this.buyTile = true;
        this.selectedCity = city;
    }

    /**
     * This function returns the log.
     * @return the log
     */
    public String getLog() {
        return this.client.getLog();
    }

    /**
     * This function returns the next client id.
     * @return the client id
     */
    public int getNextClientId() {
        return this.client.getNextClientId();
    }

    /**
     * This function returns the client.
     * @param clientId the client id
     * @return the client
     */
    public Client getClient(int clientId) {
        return this.client.getClient(clientId);
    }

    /**
     * This function saves the game.
     */
    public void saveGame() {
        this.client.saveGame();
    }
}
