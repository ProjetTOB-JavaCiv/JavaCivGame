package civ.game;

import java.util.*;

import civ.game.tuile.Terrain;
import civ.game.tuile.contenu.Caracteristique;
import civ.game.tuile.terrain.*;

/** Classe decrivant la carte du jeu */
public class Carte{

    private List<List<Tuile>> ligne = new ArrayList<List<Tuile>>();


    /** 
     * Constructeur du type carte retournant une carte aleatoire
     */
    public Carte(int largeur, int hauteur) {
        for(int x = 0; x < largeur; x++ ) {

            ligne.add(new ArrayList<Tuile>());

            for(int y = 0; y < hauteur; y++) {

                Terrain terrain = Carte.chooseRandomTerrain();

                Class<Caracteristique> carac = terrain.getCaracteristiquesPossibles().get(
                        (int)(Math.random()*terrain.getCaracteristiquesPossibles().size()));

                try {
                    Caracteristique c = carac.getConstructor().newInstance();
                    ligne.get(x).add(new Tuile(x, y, c, terrain));
                } catch (Exception e) {
                    System.out.println("Probleme d'instanciation caracteristiques");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Permet d'obtenir la tuile située au coordonnée (x , y)
     * @param x la coordonnée x recherchée
     * @param y la coordonnée y recherchée
     */
    public Tuile get(int x, int y) {
        return this.ligne.get(x).get(y);
    }

    /**
     * Methode destiné a disparaitre a terme permettant d'obtenir un terrain aleatoire pour la generation d'une carte
     * @return un objet terrain de type aleatoire
     */
    private static Terrain chooseRandomTerrain() {
        int rand = (int)Math.floor(Math.random()*7);

        switch(rand) {
            case 0:
                return new Desert(Carte.chooseRandomColline());
            case 1:
                return new Lac();
            case 2:
                return new Montagne();
            case 3:
                return new Ocean();
            case 4: 
                return new Plaine(Carte.chooseRandomColline());
            case 5: 
                return new Prairie(Carte.chooseRandomColline());
            case 6:
                return new Toundra(Carte.chooseRandomColline());
            default:
                return new Plaine(false);

        }
    }


    /**
     * Permet d'obtenir toute les tuiles adjacentes a celle ci
     * @return l'ensemble des tuiles adjacente
     */
    public Set<Tuile> getAdjacence(Tuile t) {
        Set<Tuile> adjacence = new HashSet<Tuile>();
        for (int i = -1; i<=1; i++) {
            for(int j = -1; j<=1; j++) {
                adjacence.add(get(Math.min(Math.max(t.x() + i, 0), this.ligne.size() - 1) , Math.min(Math.max(t.y() + j, 0), this.ligne.get(1).size() - 1))); // les min et max sont la pour contenir les effets de bords
            }
        }

        adjacence.remove(t);

        return adjacence;
    }


    private static Boolean chooseRandomColline() {
        return Math.random() > 0.5;
    }

    /**
     * Permet de calculer la distance entre 2 tuile sans prendre en compte les modifications de terrain.
     * @param t1 la premiere tuile
     * @param t2 la 2e tuile
     * @return la distance entre les 2
     */
    public int distanceAbsolue(Tuile t1, Tuile t2) {
        Set<Tuile> visites = new HashSet<Tuile>();
        Set<Tuile> bords = new HashSet<Tuile>();
        int distance = 0;
        visites.add(t1);
        bords.add(t1);

        while(!visites.contains(t2)) {
            Set<Tuile> bordsIterable = new HashSet<Tuile>(bords);
            for (Tuile t : bordsIterable) {
                bords.addAll(this.getAdjacence(t));
            }
            bords.removeAll(visites);
            visites.addAll(bords);
            distance++;
        }
        return distance;
    }

}