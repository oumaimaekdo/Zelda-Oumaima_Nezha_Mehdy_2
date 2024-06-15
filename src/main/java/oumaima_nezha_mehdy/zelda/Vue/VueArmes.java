package oumaima_nezha_mehdy.zelda.Vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;
import oumaima_nezha_mehdy.zelda.modele.Armes.*;


public abstract class VueArmes extends VueOutils {

    private Armes arme;
    @FXML
    private ImageView ArmeVue;

    private Image armeInversé;

    private Image armeImage;

    private Image attaqueEpee = new Image("file:src/main/resources/images/Armes/attaqueEpee.gif");


    // Constructeur
    public VueArmes(Image image,Armes arme,Image invesré) {
        super(arme,invesré,image);
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

    public void vueAttaque(Acteur acteur,Armes arme){

        if(arme.getNom().equals("epee")){
            ArmeVue.setImage(attaqueEpee);
            ArmeVue.setFitWidth(30);
            ArmeVue.setFitHeight(30);
            ArmeVue.xProperty().bind(acteur.getXProperty().add(14));
            ArmeVue.yProperty().bind(acteur.getYProperty().add(1));
        }

    }

    public void vueRepos(Image image,Armes arme,Acteur acteur){
        ArmeVue.setImage(armeImage);
        ArmeVue.setImage(image);
        ArmeVue.setFitWidth(15); // Ajuste selon la taille souhaitée
        ArmeVue.setFitHeight(15); // Ajuste selon la taille souhaitée
        ArmeVue.setId(arme.getId());
        ArmeVue.xProperty().bind(acteur.getXProperty().add(17));
        ArmeVue.yProperty().bind(acteur.getYProperty().add(10));
    }
}