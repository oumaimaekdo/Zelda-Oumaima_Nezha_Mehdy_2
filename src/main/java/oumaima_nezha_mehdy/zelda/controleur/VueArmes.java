package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.Univers.Armes;
import oumaima_nezha_mehdy.zelda.Univers.Champ;

public class VueArmes {

    private Armes arme;
    @FXML
    private ImageView ArmeVue;

    // Constructeur
    public VueArmes(Image image,Armes arme) {
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

    /*public String toString(){return "Arme";}*/
}
