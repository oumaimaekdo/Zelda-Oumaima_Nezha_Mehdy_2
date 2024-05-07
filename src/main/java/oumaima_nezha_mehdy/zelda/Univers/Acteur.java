package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {
    private String nom;

    private Champ champ;

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    public Acteur(String nom, int x , int y, Champ m){
        this.nom=nom;
        this.x.set(x);
        this.y.set(y);
        this.champ=m;
    }
    public Acteur(String nom, Champ m){
        this.nom=nom;
        this.champ=m;
        this.x.set(m.getLongueur()/2);
        this.y.set(m.getLargeur()/2);
    }

    public void seDeplacer(String direction) {
        switch (direction) {
            case "nord":
                this.y.set(this.y.getValue()-1);
                break;
            case "sud":
                this.y.set(this.y.getValue()+1);
                break;
            case "ouest":
                this.x.set(this.x.getValue()-1);
                break;
            case "est":
                this.x.set(this.x.getValue()+1);
                break;
            default:
        }
    }

    public int getX(){return x.getValue();}

    public int getY(){return y.getValue();}

    public IntegerProperty getXProperty(){return x;}
    public IntegerProperty getYProperty(){return y;}
}
