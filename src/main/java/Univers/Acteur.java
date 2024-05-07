package Univers;

public class Acteur {
    private String nom;

    private Map champ;

    private int x;
    private int y;

    public Acteur(String nom,int x , int y,Map m){
        this.nom=nom;
        this.x=x;
        this.y=y;
        this.champ=m;
    }
    public Acteur(String nom,Map m){
        this.nom=nom;
        this.champ=m;
        this.x=m.getLongueur()/2;
        this.y=m.getLargeur()/2;
    }

    public void seDeplacer(String direction) {
        switch (direction) {
            case "nord":
                this.y -= 1;
                break;
            case "sud":
                this.y += 1;
                break;
            case "ouest":
                this.x -= 1;
                break;
            case "est":
                this.x += 1;
                break;
            default:
        }
    }

    public int getX(){return x;}

    public int getY(){return y;}
}
