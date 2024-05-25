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

    private void move(Vector2 movement) {
        Vector2 newMovement = new Vector2(0, 0);

        newMovement.x = this.getMovement().x + movement.x;
        newMovement.y = this.getMovement().y + movement.y;

        this.movement = newMovement.nor();
    }

    private void stop(Vector2 movement) {
        Vector2 newMovement = new Vector2(0, 0);

        newMovement.x = this.getMovement().x - movement.x * Math.abs(this.getMovement().x);
        newMovement.y = this.getMovement().y - movement.y * Math.abs(this.getMovement().y);

        this.movement = newMovement.nor();
    }

    public ClientController(Client client) {
        this.client = client;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public float getZoom() {
        return this.zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public Vector2 getMovement() {
        return this.movement;
    }

    public boolean getDisplayTileMenu() {
        return this.displayTileMenu;
    }

    public boolean getDisplayCityMenu() {
        return this.displayCityMenu;
    }

    public Vector2 getClickCoordinates() {
        return this.coordinates;
    }

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
                System.out.println(keycode + " pressed");
                break;
        }
        return false;
    }

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
                System.out.println(keycode + " released");
                break;
        }
        return false;
    }

    public void setDisplayTileMenu(boolean displayTileMenu) {
        this.displayTileMenu = displayTileMenu;
    }

    public void setDisplayCityMenu(boolean displayCityMenu) {
        this.displayCityMenu = displayCityMenu;
    }

    @Override
    public boolean touchDown (int x, int y, int pointer, int button) {
        this.coordinates = new Vector2(x, y);
        if (button == Buttons.RIGHT){
            System.out.println("Touch down at (" + x + ", " + y + ")");

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

    public GameInterface getServer() {
		return this.client.getServer();
	}

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

    // Return game informations

    public HashMap<String, String> getGameInfos() {
        HashMap<String, String> gameInfos = new HashMap<String, String>();
        gameInfos.put("gold", this.intToStringNotation(this.client.getGoldPoint()));
        gameInfos.put("culture", this.intToStringNotation(this.client.getCulturePoint()));
        gameInfos.put("science", this.intToStringNotation(this.client.getSciencePoint()));
        gameInfos.put("faith", this.intToStringNotation(this.client.getFaithPoint()));
        return gameInfos;
    }

    public int getGoldPoint() {
        return this.client.getGoldPoint();
    }

    public int getCulturePoint() {
        return this.client.getCulturePoint();
    }

    public int getSciencePoint() {
        return this.client.getSciencePoint();
    }

    public int getFaithPoint() {
        return this.client.getFaithPoint();
    }

    private String intToStringNotation(int number) {
        String[] suffixes = new String[] { "", "K", "M", "B", "T", "Q" };
        int suffixIndex = 0;
        while (number >= 1000) {
            number /= 1000;
            suffixIndex++;
        }
        return number + suffixes[suffixIndex];
    }

    public void nextTurn() {
        this.client.nextTurn();
    }

    public int getCurrentClient() {
        return this.client.getClientId();
    }

    public WorldMap getWorldMap() {
        return this.client.getWorldMap();
    }

    public boolean addCity(Tile tile) {
        return this.client.createCity(tile);
    }

    public List<City> getCities() {
        return this.client.getCities();
    }

    public void buyTile(City city) {
        this.buyTile = true;
        this.selectedCity = city;
    }

}
