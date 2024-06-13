package oumaima_nezha_mehdy.zelda.Vue;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

public class VueBombe extends VueArmes{
    public VueBombe(Armes bombe) {
        super(new Image("file:src/main/resources/images/Armes/bombe.png"), bombe, new Image("file:src/main/resources/images/Armes/bombeInversé.png"));
    }
}
