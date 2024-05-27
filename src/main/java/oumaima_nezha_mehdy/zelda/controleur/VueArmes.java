package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.Univers.Armes;
import oumaima_nezha_mehdy.zelda.Univers.Champ;

public class VueArmes {

    //private Acteur acteur;
    private Armes arme;
    @FXML
    private Pane vueArme;
    @FXML
    private ImageView ArmeVue;
    private int tailleTuile;

    // Constructeur
    public VueArmes(int tailleTuile,Image image,Armes arme,Pane VueArme) {
        this.tailleTuile = tailleTuile;
        this.vueArme = VueArme;
        creerArme(image,arme);
    }

    // Méthode pour créer une arme
    public void creerArme(Image image, Armes arme) {
        ImageView r = new ImageView();
        r.setImage(image);
        r.setFitWidth(15); // Ajuste selon la taille souhaitée
        r.setFitHeight(15); // Ajuste selon la taille souhaitée
        vueArme.getChildren().add(r);
        r.setId(arme.getId());
        this.ArmeVue = r;
    }

    public ImageView getArmeVue() {
        return ArmeVue;
    }
}
