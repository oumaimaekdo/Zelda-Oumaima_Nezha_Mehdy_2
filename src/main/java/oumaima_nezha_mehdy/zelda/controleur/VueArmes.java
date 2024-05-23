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

    public void choixArme(String path, Armes nouvelleArme) {
        if (vueArme != null) {
            vueArme.getChildren().remove(vueEpee);
        }
        this.epee = nouvelleArme;
        creerArme(path, nouvelleArme);
    }


    /*public void DeplacementLink(String key){
        System.out.println("\n \n \n" );
        System.out.println(key);
        switch (key) {
            case"Z" :
            case "UP":
                if(coordonnéPossible(this.link.getX(),this.link.getY()-(1*link.getVitesse())))
                    link.seDeplacer("nord");
                this.vueLink.setImage(linkNord);
                break;
            case "Q":
            case "LEFT":
                if(coordonnéPossible(this.link.getX()-(1*link.getVitesse()),this.link.getY()))
                    link.seDeplacer("ouest");
                this.vueLink.setImage(linkOuest);
                break;
            case "S":
            case "DOWN":
                if(coordonnéPossible(this.link.getX(),this.link.getY()+(1*link.getVitesse())))
                    link.seDeplacer("sud");
                this.vueLink.setImage(linkSud);
                break;
            case "D":
            case "RIGHT":
                if(coordonnéPossible(this.link.getX()+(1*link.getVitesse()),this.link.getY()))
                    link.seDeplacer("est");
                this.vueLink.setImage(linkEst);
                break;
        }


        System.out.println(link.getX()+","+link.getY());
        System.out.println(link.getX()/tT+","+link.getY()/tT);
    }*/
}
