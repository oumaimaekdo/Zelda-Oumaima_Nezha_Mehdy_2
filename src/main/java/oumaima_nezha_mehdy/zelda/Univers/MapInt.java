package oumaima_nezha_mehdy.zelda.Univers;

public class MapInt {

    private int[]  carte;

    private int longueur;
    private int largeur;



    public MapInt(int[] map,int longueur,int largeur){
        carte=map;
        this.longueur=longueur;
        this.largeur=largeur;
    }

    public int[] getCarte() {
        return carte;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }
}
