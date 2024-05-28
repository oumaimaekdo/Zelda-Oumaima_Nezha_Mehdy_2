package oumaima_nezha_mehdy.zelda.controleur;

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

    public Clavier(Pane pane,VueActLink vac){
        this.vueActeur=pane;
        this.linkDeplacement=vac;
        this.vueActeur.setOnKeyPressed(this::handle);
        this.vueCaseinventaire=vueCaseinventaire;
        this.vueInventaire = new VueInventaire(vueCaseinventaire);
    }

    public void touchePressé(KeyEvent keyEvent) {
        linkDeplacement.DeplacementLink(keyEvent.getCode().toString());
        vueInventaire.selectionerCase(keyEvent);
    }
    public void toucheRelaché(KeyEvent keyEvent){
        linkDeplacement.toucheRelaché(keyEvent);
    }
}
