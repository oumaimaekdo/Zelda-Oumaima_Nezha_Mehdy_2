package oumaima_nezha_mehdy.zelda.Vue.Outils.Armes;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;

public class VueBombe extends VueArmes {
    public VueBombe(Armes bombe) {
        super(new Image("file:src/main/resources/images/Armes/bombe.png"), bombe, new Image("file:src/main/resources/images/Armes/bombeInversé.png"));
    }
}
