package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.Armes;

public abstract class VueArmes {

    private Armes arme;
    @FXML
    private ImageView ArmeVue;

    private Image armeInversé;

    private Image armeImage;


    // Constructeur
    public VueArmes(Image image,Armes arme,Image inveré) {
        armeInversé=inveré;
        armeImage=image;
        creerArme(image,arme);
    }

    // Méthode pour créer une arme
    public void creerArme(Image image, Armes arme) {
        ImageView r = new ImageView();
        r.setImage(image);
        r.setFitWidth(15); // Ajuste selon la taille souhaitée
        r.setFitHeight(15); // Ajuste selon la taille souhaitée
        r.setId(arme.getId());
        this.ArmeVue = r;
        this.arme=arme;
    }

    public ImageView getArmeVue() {
        return ArmeVue;
    }
    public Armes getArme(){return arme;}

    public Image getArmeInversé(){
        return armeInversé;
    }

    public Image getArmeImage() {
        return armeImage;
    }
    /*public String toString(){return "Arme";}*/
}
