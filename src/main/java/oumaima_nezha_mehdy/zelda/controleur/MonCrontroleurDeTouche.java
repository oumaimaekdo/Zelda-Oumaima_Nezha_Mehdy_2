package oumaima_nezha_mehdy.zelda.controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import oumaima_nezha_mehdy.zelda.Univers.*;

public class MonCrontroleurDeTouche implements EventHandler<KeyEvent> {

    private Champ champ;


    public MonCrontroleurDeTouche(Champ champ) {
        this.champ = champ;
    }
    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCode().toString());

    }
}
