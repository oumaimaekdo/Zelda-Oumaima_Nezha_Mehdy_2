package oumaima_nezha_mehdy.zelda.controleur;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
import oumaima_nezha_mehdy.zelda.Vue.VueActLink;
import oumaima_nezha_mehdy.zelda.Vue.VueInventaire;

public class Clavier{

    private Pane vueActeur;
    private Pane univers;

    private HBox vueCaseinventaire;

    private VueInventaire vueInventaire;


    private VueActLink linkDeplacement;
    public Clavier(Pane pane,VueActLink vac,HBox vueCaseinventaire){
        this.vueActeur=pane;
        this.linkDeplacement=vac;
        this.vueCaseinventaire=vueCaseinventaire;
        this.vueInventaire = new VueInventaire(vueCaseinventaire, linkDeplacement);
    }

    public void handle(KeyEvent keyEvent) {
        switch(keyEvent.getCode().toString()) {
            case "Z":
            case "Q":
            case "S":
            case "D":
                linkDeplacement.ajouterTouche(keyEvent.getCode().toString());
                break;
            case "AMPERSAND":
            case "DIGIT1" :
            case "UNDEFINED":
            case "DIGIT2" :
            case "QUOTEDBL":
            case "DIGIT3" :
            case "QUOTE":
            case "DIGIT4" :
            case "LEFT_PARENTHESIS":
            case "DIGIT5" :
            case "G":
            case "E" :
            case "H" :
                vueInventaire.selectionerCase(keyEvent);
                break;
            case"F":linkDeplacement.getLink().interagirCoffre();


        }
    }

    public void toucheRelaché(KeyEvent e){
        linkDeplacement.toucheRelaché(e);
    }
}
