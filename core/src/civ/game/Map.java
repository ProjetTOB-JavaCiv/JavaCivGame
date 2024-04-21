package game;

import java.util.*;

import game.tuile.Terrain;
import game.tuile.contenu.Caracteristique;
import game.tuile.contenu.caracteristique.Base;
import game.tuile.terrain.Ocean;
import game.tuile.terrain.Plaine;
import game.tuile.terrain.Montagne;


public class Map{

    private List<List<Tuile>> ligne = new ArrayList<List<Tuile>>();

    /**
     * Constructeur du type map
     */
    public Map(List<List<Tuile>> ligne){
        this.ligne = ligne;
    }
    /**
     * Permet de créer la liste avec les germes placées aléatoirement
     * @param largeur la largeur de la map
     * @param hauteur la hauteur de la map
     * @return la liste avec les
     */
    public static ArrayList<Tuile> creerListeGermes(int largeur, int hauteur){
        ArrayList<Tuile> listeGermes = new ArrayList<Tuile>();
        int nombreGermes = (largeur * hauteur) / 20 ;
        for(int i = 0; i < nombreGermes; i++ ) {
            int x = (int)Math.floor(Math.random()*largeur);
            int y = (int)Math.floor(Math.random()*hauteur);

            Terrain terrain = Map.chooseRandomTerrain();

            Caracteristique c = new Base();

            listeGermes.add(new Tuile(x, y, c, terrain));
        }
        return listeGermes;
    }
    /**
     * Permet de créer une map aléatoire
     * @param largeur la largeur de la map
     * @param hauteur la hauteur de la map
     * @return la map aléatoire
     */
    public static Map creerMapAleatoire(int largeur, int hauteur){
        ArrayList<Tuile> listeGermes = creerListeGermes(largeur, hauteur);
        List<List<Tuile>> ligne = new ArrayList<List<Tuile>>();
        for(int x = 0; x < largeur; x++ ) {

            ligne.add(new ArrayList<Tuile>());

            for(int y = 0; y < hauteur; y++) {

                Terrain terrain = getGermeProche(x,y,listeGermes).getTerrain();

                Caracteristique c = new Base();

                ligne.get(x).add(new Tuile(x, y, c, terrain));
            }
        }
        return new Map(ligne);
    }
    /**
     * Permet d'obtenir le terrain de la germe la plus proche d'une map
     * @param x la coordonnée x de la tuile concidérée
     * @param y la coordonnée y de la tuile concidérée
     * @return le terrain le plus proche
     */
    public static Tuile getGermeProche(int x, int y, ArrayList<Tuile> listeGermes){
        Tuile tuileMin = listeGermes.get(0);
        double distanceMin = Distance(x,y, tuileMin.x(), tuileMin.y());
        for (Tuile t : listeGermes) {
            double distance = Distance(x,y, t.x(), t.y());
            if (distanceMin > distance) {
                distanceMin = distance;
                tuileMin = t;
            }
        }
        return tuileMin;
    }
    /** 
     * Permet d'obtenir la tuile située au coordonnée (x , y)
     * @param x la coordonnée x recherchée
     * @param y la coordonnée y recherchée
     */
    public Tuile get(int x, int y) {
        return this.ligne.get(x).get(y);
    }

    static public double Distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((y2 - y1),2) + Math.pow((x2 - x1),2));
    }

    public int[][] getTableau(){
        int row = this.ligne.size();
        int col = this.ligne.get(0).size();
        int[][] tab = new int [row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tab[i][j] = Map.getIntTerrain(get(i,j).getTerrain());
            }
        }
        return tab;
    }
    /**
     * Methode destiné a disparaitre a terme permettant d'obtenir un terrain aleatoire pour la generation d'une carte
     * @return un objet terrain de type aleatoire
     */
    private static Terrain chooseRandomTerrain() {
        int rand = (int)Math.floor(Math.random()*9);

        switch(rand) {
            case 0:
                //return new Desert(Map.chooseRandomColline());
            case 1:
                //return new Lac();
            case 2:
                //return new Montagne();
            case 3:
                return new Ocean();
            case 4: 
               // return new Plaine(Map.chooseRandomColline());
            case 5:
                return new Montagne();
                //return new Prairie(Map.chooseRandomColline());
            case 6:
               // return new Toundra(Map.chooseRandomColline());
            default:
                return new Plaine(false);

        }
    }
    public static int getIntTerrain(Terrain terrain) {
        switch(terrain.getClass().getSimpleName()) {
            case "Desert":
                return 0;
            case "Lac":
                return 1;
            case "Montagne":
                return 2;
            case "Ocean":
                return 3;
            case "Plaine":
                return 4;
            case "Prairie":
                return 5;
            case "Toundra":
                return 6;
            default:
                return 4;

        }
    }
    /**
     * Permet d'obtenir toute les tuiles adjacentes a celle ci
     * @return l'ensemble des tuiles adjacente
     */
    public Set<Tuile> getAdjacence(Tuile t) {
        Set<Tuile> adjacence = new HashSet<Tuile>();
        for (int i = -1; i>1; i++) {
            for(int j = -1; j>1; j++) {
                adjacence.add(get(t.x() + i, t.y() + j));
            }
        }

        adjacence.remove(t);

        return adjacence;
    }
    

    private static Boolean chooseRandomColline() {
        return Math.random() > 0.5;
    }
}
