package oumaima_nezha_mehdy.zelda.Vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;

public class VueArmes {

    private Armes arme;
    @FXML
    private ImageView ArmeVue;
    private Image armeInversé;

    private Image armeImage;

    private Image attaqueEpee = new Image("file:src/main/resources/images/Armes/attaqueEpee.gif");
    private Image epee = new Image("file:src/main/resources/images/epeeFer.png");

    //Constructeur
    public VueArmes(Image image,Armes arme) {
        creerArme(image,arme);
    }
    public VueArmes(Image image,Armes arme,Image inveré) {
        armeInversé=inveré;
        armeImage=image;
        creerArme(image,arme);
    }
    public void creerArme(Image image, Armes arme) {
        ImageView r = new ImageView();
        r.setImage(image);
        r.setFitWidth(15); // Ajuste selon la taille souhaitée
        r.setFitHeight(15); // Ajuste selon la taille souhaitée
        r.setId(arme.getId());
        this.ArmeVue = r;
        this.arme=arme;
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
        ArmeVue.setImage(epee);
        ArmeVue.setImage(image);
        ArmeVue.setFitWidth(15); // Ajuste selon la taille souhaitée
        ArmeVue.setFitHeight(15); // Ajuste selon la taille souhaitée
        ArmeVue.setId(arme.getId());
        ArmeVue.xProperty().bind(acteur.getXProperty().add(17));
        ArmeVue.yProperty().bind(acteur.getYProperty().add(10));
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
}
