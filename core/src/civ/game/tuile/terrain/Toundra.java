package civ.game.tuile.terrain;

import java.util.ArrayList;
import java.util.List;

import civ.game.Production;
import civ.game.tuile.Terrain;
import civ.game.tuile.contenu.Caracteristique;
import civ.game.tuile.contenu.caracteristique.*;

public class Toundra implements Terrain{
    Boolean colline;

    public Toundra(Boolean colline) {
        this.colline = colline;
    }
    
    public int getModificateurCombat() {
        return this.colline ? 3 : 0;
    }

    public int getModificateurDeplacement() {
        return 0;
    }

    public Production getProduction() {
        Production production = new Production();
        production.nourriture = 1;
        production.materiel += this.colline ? 0: 1;
        return production;
    }

    public Nature getNature() {
        return Nature.TERRESTRE;
    }

    public List<Caracteristique> getCaracteristiquesPossibles() {
        List<Caracteristique> possible = new ArrayList<Caracteristique>();
        possible.add(new Bois());
        possible.add(new Marais());
        possible.add(new PlaineInnondable());
        return possible;
    }
}
