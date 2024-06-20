package oumaima_nezha_mehdy.zelda.Vue.Outils.Armes;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;

public class VueEpee extends VueArmes {
    public VueEpee(Armes epee) {
        super(new Image("file:src/main/resources/images/Armes/epeeFer.png"),epee, new Image("file:src/main/resources/images/Armes/epeeFerInversé.png"));
    }
}