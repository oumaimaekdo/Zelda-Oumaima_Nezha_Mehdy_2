package oumaima_nezha_mehdy.zelda.controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import oumaima_nezha_mehdy.zelda.Univers.*;

public class MonControleurDeTouche implements EventHandler<KeyEvent> {

    private Champ champ;


    public MonControleurDeTouche(Champ champ) {
        this.champ = champ;
    }
    @Override
    public void handle(KeyEvent event) {
        System.out.println(event.getCode().toString());

    }
}
