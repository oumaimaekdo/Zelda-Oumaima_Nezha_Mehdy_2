package oumaima_nezha_mehdy.zelda.controleur;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.Univers.Armes;

public class VueEpee extends VueArmes{
    public VueEpee() {
        super(new Image("file:src/main/resources/images/epeeFer.png"), new Armes("Epee",20), new Image("file:src/main/resources/images/epeeFerInversé.png"));
    }
}
