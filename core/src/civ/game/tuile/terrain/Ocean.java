package civ.game.tuile.terrain;

import java.util.ArrayList;
import java.util.List;

import civ.game.Production;
import civ.game.tuile.Terrain;
import civ.game.tuile.contenu.Caracteristique;

public class Ocean implements Terrain{
    
    public int getModificateurCombat() {
        return 0;
    }

    public int getModificateurDeplacement() {
        return 0;
    }

    public Production getProduction() {
        Production production = new Production();
        production.nourriture = 1;
        return production;
    }

    public Nature getNature() {
        return Nature.MARITIME;
    }

    public List<Caracteristique> getCaracteristiquesPossibles() {
        List<Caracteristique> possible = new ArrayList<Caracteristique>();
        return possible;
    }
}
