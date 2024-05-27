package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Armes {
    private String nom;
    private int degats;

    public static int id= 0;




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

    public void utiliser() {
        System.out.println("L'arme " + nom + " a été utilisée, infligeant " + degats + " dégâts.");
    }


}
