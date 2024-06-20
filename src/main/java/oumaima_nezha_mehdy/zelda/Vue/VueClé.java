package oumaima_nezha_mehdy.zelda.Vue;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Clé;

public class VueClé extends VueOutils {

    private Clé clé;


    public VueClé(Clé c){
        super(c,new Image("file:src/main/resources/images/Armes/clé.png"),new Image("file:src/main/resources/images/Armes/clé.png"));
        this.clé=c;
    }

}
