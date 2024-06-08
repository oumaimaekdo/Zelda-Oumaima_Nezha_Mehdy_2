package oumaima_nezha_mehdy.zelda.Vue;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

public class VueEpeeRouge extends VueArmes{
    public VueEpeeRouge(Armes epee) {
        super(new Image("file:src/main/resources/images/Armes/epeerouge.png"),epee, new Image("file:src/main/resources/images/Armes/epeerougeInversé.png"));
    }
}