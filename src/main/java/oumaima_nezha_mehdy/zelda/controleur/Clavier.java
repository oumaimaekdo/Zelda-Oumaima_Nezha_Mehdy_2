package oumaima_nezha_mehdy.zelda.controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
import oumaima_nezha_mehdy.zelda.Univers.Acteur;

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
        this.vueInventaire = new VueInventaire(vueCaseinventaire);
    }

    public void handle(KeyEvent keyEvent) {
        linkDeplacement.DeplacementLink(keyEvent.getCode().toString());
        vueInventaire.selectionerCase(keyEvent);

    }
}