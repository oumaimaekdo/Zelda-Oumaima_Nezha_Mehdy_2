package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.Armes;

public class VueArmes {

    private Armes arme;
    @FXML
    private ImageView ArmeVue;

    @FXML
    private ImageView armeAramasser;
    // Constructeur
    public VueArmes(Image image,Armes arme,Image armeAramasser) {
        creerArme(image,arme,armeAramasser);
    }

    public ImageView getArmearamasser(){
        return this.armeAramasser;
    }

    // Méthode pour créer une arme
    public void creerArme(Image image, Armes arme,Image armeAramasser) {
        ImageView r = new ImageView();
        ImageView armeMap = new ImageView();
        r.setImage(image);
        armeMap.setImage(armeAramasser);
        r.setFitWidth(15); // Ajuste selon la taille souhaitée
        r.setFitHeight(15); // Ajuste selon la taille souhaitée
        r.setId(arme.getId());
        armeMap.setId(arme.getId());
        this.ArmeVue = r;
        this.armeAramasser = armeMap;
        this.arme=arme;
    }

    public ImageView getArmeVue() {
        return ArmeVue;
    }
    public Armes getArme(){return arme;}

    /*public String toString(){return "Arme";}*/
}
