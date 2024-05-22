package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Armes {
    private String nom;
    private int degats;

    public static int id= 0;

    private Champ champ;
    private ImageView imageView;


    public Armes(String nom, int degats,Champ champ,String imagePath) {
        this.nom = nom;
        this.degats = degats;
        this.champ = champ;
        this.imageView = new ImageView(new Image(imagePath));
        this.imageView.setFitWidth(30); // Ajuste selon la taille souhaitée
        this.imageView.setFitHeight(30); // Ajuste selon la taille souhaitée
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

    public ImageView getImageView() {
        return imageView;
    }

}
