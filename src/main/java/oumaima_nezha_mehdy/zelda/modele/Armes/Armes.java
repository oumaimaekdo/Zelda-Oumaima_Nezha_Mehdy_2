package oumaima_nezha_mehdy.zelda.modele.Armes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

public abstract class Armes {
    private String nom;
    private int degats;

    private String id;

    public static int cpt= 0;
    private Champ champ;

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);


    public Armes(String nom, int degats,Champ champ) {
        this.nom = nom;
        this.degats = degats;
        this.champ = champ;
        champ.ajouterItem(this);
        id = ""+cpt;
        cpt++;
    }

    public String getNom() {
        return nom;
    }

    public int getDegats() {
        return degats;
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

    public void utiliser() {
        System.out.println("L'arme " + nom + " a été utilisée, infligeant " + degats + " dégâts.");
    }
    public IntegerProperty getXProperty(){return x;}
    public IntegerProperty getYProperty(){return y;}

    @Override
    public String toString() {
        return "Armes{" +
                "nom='" + nom + '\'' +
                ", degats=" + degats +
                ", champ=" + champ +
                ", x=" + x +
                ", y=" + y +
                '}';
    }


}