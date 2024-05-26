package oumaima_nezha_mehdy.zelda.controleur;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Clavier{

    private Pane vueActeur;
    private Pane univers;


    private VueActLink linkDeplacement;


    public Clavier(Pane pane,VueActLink vac){
        this.vueActeur=pane;
        this.linkDeplacement=vac;
    }

    public void touchePressé(KeyEvent keyEvent) {
        linkDeplacement.DeplacementLink(keyEvent.getCode().toString());
    }
    public void toucheRelaché(KeyEvent keyEvent){
        linkDeplacement.toucheRelaché(keyEvent);
    }
}
