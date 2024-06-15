package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Outils {

    private String nom;
    private Champ c;
    private String id;
    public static int cpt= 0;

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    public Outils(String nom,Champ champ){
        this.nom = nom;
        this.c = champ;
        id = ""+cpt;
        cpt++;
        c.ajouterItem(this);
    }




    public  String getId() {
        return id;
    }
    public void setX(int a){
        x.setValue(a);
    }
    public void setY(int a){
        y.setValue(a);
    }

    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }
    public IntegerProperty getXProperty(){return x;}
    public IntegerProperty getYProperty(){return y;}
    public String getNom(){
        return nom;
    }
}
