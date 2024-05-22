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

    // Constructeur
    public VueArmes(Pane pane, Champ champ, int tailleTuile) {
        this.vueArme = pane;
        this.champ = champ;
        this.tailleTuile = tailleTuile;
        this.epee = champ.getArme();
        creerArme("file:src/main/resources/images/epeeFer.png", epee);
    }

    // Méthode pour créer une arme
    public void creerArme(String path, Armes epee) {
        ImageView r = new ImageView();
        Image image = new Image(path);
        r.setFitWidth(30); // Ajuste selon la taille souhaitée
        r.setFitHeight(30); // Ajuste selon la taille souhaitée
        vueArme.getChildren().add(r);
        r.setId(epee.getId());
        Acteur acteur = champ.getLink();
        acteur.equiperArme(epee);
        this.vueEpee = r;
    }
}
