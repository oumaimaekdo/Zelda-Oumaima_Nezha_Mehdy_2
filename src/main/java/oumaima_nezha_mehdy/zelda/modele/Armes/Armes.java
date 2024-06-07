package oumaima_nezha_mehdy.zelda.modele.Armes;

import javafx.beans.property.IntegerProperty;

public class Armes {
    private String nom;
    private int degats;
    public static int id= 0;
    private IntegerProperty x;
    private IntegerProperty y;

    public Armes(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
        id++;
    }

    public Armes(String nom, int degats, int x, int y) {
        this.nom = nom;
        this.degats = degats;
        this.x.set(x);
        this.y.set(y);
        id++;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats){
        this.degats = degats;
    }

    public int getX() {
        return x.get();
    }
    public int getY() {
        return y.get();
    }
    public IntegerProperty xProperty(){
        return x;
    }
    public IntegerProperty yProperty(){
        return y;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }
    public String getNom() {
        return nom;
    }

    public static String getId() {
        return "#"+id;
    }



}
