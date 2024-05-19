package com.javaciv.gameElement;

import java.util.ArrayList;
import java.util.List;

import com.javaciv.type.TechnologyID;

public abstract class Technology {
    
    /** l'ID de la technologie */
    private final TechnologyID ID; 
    /** le nom de la technologie */
    private String name;
    /** le cout en science de la technologie */
    private int cost;
    /** Les IDs des technologies venant immediatement avant dans l'arbre */
    private List<TechnologyID> previous;
    /** Les IDs des technologies venant immediatement apres dans l'arbre */
    private List<TechnologyID> next;
    /** Les differents etats dans lesquels une technologie peut etre*/
    public static enum TechnologyStates {
        DISCOVERED, DISCOVERABLE, UNKNOWN;
    }
    /** L'etat actuel de la technologie */
    private TechnologyStates state;


    // CONSTRUCTEUR //
    public Technology(TechnologyID ID, String name, int cost, TechnologyID[] previous, TechnologyID[] next, TechnologyStates state) {
        this.name = name;
        this.cost = cost;
        this.ID = ID;
        this.previous = new ArrayList<>(List.of(previous));
        this.next = new ArrayList<>(List.of(next));
        this.state = state;
    }

    /** Permet de recuperer le nom de la technologie */
    public String getName() {
        return name;
    }

    /** permet de recuperer le cout de la technologie */
    public int getCost() {
        return cost;
    }

    /** permet de recuprer les IDs des technologies suivantes */
    public List<TechnologyID> getNext() {
        return this.next;
    }

    /** Permet de recuperer l'ID de la technologie */
    public TechnologyID getID() {
        return this.ID;
    }

    public TechnologyStates getState() {
        return this.state;
    }

    /** Permet de changer l'etat de decouverte d'une technologie 
     * @param newState le noouvel etat de la technologie
    */
    public void setState(TechnologyStates newState) {
        this.state = newState;
    }

    /** Permet de rendre effectif le debloquage de la technology */
    public abstract void discover();

    /** Permet de supprimer une technologie precedente des technologies requises lorsqu'elle est decouverte */
    public void prevDiscover(TechnologyID ID) {
        this.previous.remove(ID);
        if (this.previous.size() == 0) {
            this.state = TechnologyStates.DISCOVERABLE;
        }
    }
    
}
