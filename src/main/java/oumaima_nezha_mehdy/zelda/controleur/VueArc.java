package oumaima_nezha_mehdy.zelda.controleur;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.Univers.Arc;
import oumaima_nezha_mehdy.zelda.Univers.Armes;

public class VueArc extends VueArmes{
    public VueArc(Armes arc) {
      super(new Image("file:src/main/resources/images/arc.png"),arc,new Image("file:src/main/resources/images/arcInversé.png"));
    }
}
