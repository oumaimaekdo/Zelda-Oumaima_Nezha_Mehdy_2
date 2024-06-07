package oumaima_nezha_mehdy.zelda.Vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.Sbir;

public class VueSbir {

    @FXML
    private Pane vueSbir;
    private Sbir sbir1;
    private Champ champ;
    private int tailleTuile;

    @FXML
    private ImageView vueSbir1;
    private Image SbirNord;
    private Image SbirSud;
    private Image SbirEst;
    private Image SbirOuest;

    public VueSbir(Pane pane, Champ c, int tailleTuile) {
        vueSbir=pane;
        this.champ=c;
        this.sbir1=champ.getSbir();
        this.tailleTuile=tailleTuile;
        creerSbir("file:src/main/resources/images/squeletteMarcheEst.gif",sbir1);
        /*SbirNord=new Image("file:src/main/resources/images/Link/NordDefault.png");
        SbirSud=new Image("file:src/main/resources/images/Link/SudDefault.png");
        SbirEst=new Image("file:src/main/resources/images/Link/EstDefault.png");
        SbirOuest=new Image("file:src/main/resources/images/Link/OuestDefault.png");*/
        deplacementSbir();
    }

    public void creerSbir(String path , Sbir s){
        ImageView r = new ImageView();
        Image Image = new Image(path);
        r.setImage(Image);
        r.setFitWidth(30);
        r.setFitHeight(30);
        vueSbir.getChildren().add(r);
        r.setId(s.getId());
        r.setTranslateX(s.getX());
        r.setTranslateY(s.getY());
        r.translateXProperty().bind(s.getXProperty());
        r.translateYProperty().bind(s.getYProperty());
        this.vueSbir1=r;
    }

    public void deplacementSbir(){
        sbir1.deplacementAleatoire();
    }

    public Sbir getSbir1(){
        return this.sbir1;
    }

    public void updateChamp(Champ champ) {
        this.champ = champ;
    }

}
