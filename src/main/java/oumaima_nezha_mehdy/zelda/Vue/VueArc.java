package oumaima_nezha_mehdy.zelda.Vue;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;

public class VueArc extends VueArmes{
    public VueArc(Armes arc) {
        super(new Image("file:src/main/resources/images/Armes/arc.png"),arc,new Image("file:src/main/resources/images/Armes/arcInversé.png"));
    }
}