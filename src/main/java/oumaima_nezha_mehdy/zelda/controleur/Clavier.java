package oumaima_nezha_mehdy.zelda.controleur;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
public class Clavier{



    private VueInventaire vueInventaire;


    private VueActLink linkDeplacement;
    public Clavier(Pane pane,VueActLink vac,HBox vueCaseinventaire){
        this.linkDeplacement=vac;
        this.vueInventaire = new VueInventaire(vueCaseinventaire);
    }

    public void handle(KeyEvent keyEvent) {
        linkDeplacement.DeplacementLink(keyEvent.getCode().toString());
        vueInventaire.selectionerCase(keyEvent);
    }

}