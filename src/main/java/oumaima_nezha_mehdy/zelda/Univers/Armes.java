package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Armes {
    private String nom;
    private int degats;


    public static int id= 0;

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);


    public Armes(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
        id++;
    }

    public String getNom() {
        return nom;
    }

    public int getDegats() {
        return degats;
    }

    public static String getId() {
        return "#"+id;
    }

    public void setId(int id){
        this.id = id;
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

    public void utiliser() {
        System.out.println("L'arme " + nom + " a été utilisée, infligeant " + degats + " dégâts.");
    }
    public IntegerProperty getXProperty(){return x;}
    public IntegerProperty getYProperty(){return y;}


}
