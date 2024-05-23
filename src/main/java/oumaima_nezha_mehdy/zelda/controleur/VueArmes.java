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
    private Armes epee;
    @FXML
    private Pane vueArme;
    @FXML
    private ImageView vueEpee;
    private Champ champ;
    private int tailleTuile;

    private ImageView imageView;

    // Constructeur
    public VueArmes(Pane pane, Champ champ, int tailleTuile) {
        this.vueArme = pane;
        this.champ = champ;
        this.tailleTuile = tailleTuile;
        this.epee = creerArme("file:src/main/resources/images/epeeFer.png", epee);

    }

    // Méthode pour créer une arme
    public Armes creerArme(String path, Armes epee) {
        this.imageView = new ImageView(new Image(path));
        this.imageView.setFitWidth(22); // Ajuste selon la taille souhaitée
        this.imageView.setFitHeight(22);
        vueArme.getChildren().add(this.imageView);
        this.imageView.setId(epee.getId());
        Acteur acteur = champ.getLink();
        acteur.equiperArme(epee);
        this.vueEpee = this.imageView;

        return epee;
    }

    public void choixArme(String path, Armes nouvelleArme) {
        if (vueArme != null) {
            vueArme.getChildren().remove(vueEpee);
        }
        this.epee = nouvelleArme;
        creerArme(path, nouvelleArme);
    }
}
