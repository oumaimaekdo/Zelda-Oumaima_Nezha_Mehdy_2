package oumaima_nezha_mehdy.zelda.Vue;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Outils;

public class VueEpee extends VueArmes{
    public VueEpee(Armes epee) {
        super(new Image("file:src/main/resources/images/Armes/epeeFer.png"),epee, new Image("file:src/main/resources/images/Armes/epeeFerInversé.png"));
    }
}