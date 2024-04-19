public class Batiment {

    /** Décrit le cout nécessaire en production pour produire le batiment */
    int coutConstructionBuild;

    /** Nombre de point de nourriture aporté par le batiment à une ville */
    int nourriture;

    /** Nombre de point de culture aporté par le baitment à une ville */
    int culture;

    /** */
    int foi;

    int science;

    int or;



    public void Batiment( int coutConstructionBuild, int nourriture, int culture,
                          int foi, int science, int or) {
        this.coutConstructionBuild = coutConstructionBuild;
        this.nourriture = nourriture;
        this.culture = culture;
        this.foi = foi;
        this.or = or;
        this.science = science;
    }

    public int getCoutConstructionBuild() {
        return this.coutConstructionBuild;
    }

    public int getCulture() {
        return this.culture;
    }

    public int getNourriture() {
        return this.nourriture
    }

    public int getFoi() {
        return this.foi
    }

    public int getOr() {
        return this.or;
    }

    public int getScience() {
        return this.science;
    }
}