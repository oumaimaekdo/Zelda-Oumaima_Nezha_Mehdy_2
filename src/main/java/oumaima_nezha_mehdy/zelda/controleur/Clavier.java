package oumaima_nezha_mehdy.zelda.controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.Acteur;

public class Clavier{

    private Pane vueActeur;
    private Pane univers;


    private VueActLink linkDeplacement;
    public Clavier(Pane pane,VueActLink vac){
        this.vueActeur=pane;
        this.linkDeplacement=vac;
        this.vueActeur.setOnKeyPressed(this::handle);
    }

    public void handle(KeyEvent keyEvent) {
        linkDeplacement.DeplacementLink(keyEvent.getCode().toString());
    }
}
