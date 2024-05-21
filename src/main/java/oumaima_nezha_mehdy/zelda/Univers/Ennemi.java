package oumaima_nezha_mehdy.zelda.Univers;

public class Ennemi extends Acteur{

    private int x, y;

    public Ennemi(String nom, int x, int y, Champ m){
        super(nom,x,y,m);
    }
    public Ennemi(String nom, Champ m){
        super(nom,m);
    }

    public int getX(){ return x;}
    public int getY(){ return y;}


}
