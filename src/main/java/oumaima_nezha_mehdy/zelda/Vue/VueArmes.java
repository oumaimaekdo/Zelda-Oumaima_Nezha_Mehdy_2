package oumaima_nezha_mehdy.zelda.Vue;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs.Link;


public abstract class VueArmes extends VueOutils {

    private Armes arme;
    @FXML
    private ImageView ArmeVue;

    private Image armeInversé;

    private Image armeImage;

    private Image attaqueEpee = new Image("file:src/main/resources/images/Armes/attaqueEpee.gif");
    private Image attaqueBombe = new Image("file:src/main/resources/images/Armes/explosionBombe.gif");


    // Constructeur
    public VueArmes(Image image,Armes arme,Image invesré) {

        super(arme,invesré,image);
        creerArme(image,arme);
        this.arme = arme;
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
        ArmeVue.xProperty().bind(arme.getXProperty());
        ArmeVue.yProperty().bind(arme.getYProperty());
    }

    public ImageView getArmeVue() {
        return super.getVue();
    }
    public Armes getArme(){return arme;}

    public Image getArmeInversé(){
        return armeInversé;
    }

    public Image getArmeImage() {
        return armeImage;
    }

    public void vueAttaque(VueActLink vueActeur, Link acteur, Armes arme){

        if(arme.getNom().equals("epee")){
            super.getVue().setImage(attaqueEpee);
            super.getVue().setFitWidth(30);
            super.getVue().setFitHeight(30);
            super.getVue().xProperty().bind(acteur.getXProperty().add(14));
            super.getVue().yProperty().bind(acteur.getYProperty().add(1));
        }
        else if(arme.getNom().equals("bombe")){
            super.getVue().setImage(attaqueBombe);
            super.getVue().setFitWidth(30);
            super.getVue().setFitHeight(30);
            super.getVue().xProperty().bind(acteur.getXProperty().add(140));
            super.getVue().yProperty().bind(acteur.getYProperty().add(1));
            acteur.lacher();
        }

    }

    public void vueRepos(Image image, Armes arme, Acteur acteur){
        super.getVue().setImage(armeImage);
        super.getVue().setImage(image);
        super.getVue().setFitWidth(15); // Ajuste selon la taille souhaitée
        super.getVue().setFitHeight(15); // Ajuste selon la taille souhaitée
        super.getVue().setId(arme.getId());
        super.getVue().xProperty().bind(acteur.getXProperty().add(17));
        super.getVue().yProperty().bind(acteur.getYProperty().add(10));
    }
}