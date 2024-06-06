package oumaima_nezha_mehdy.zelda.controleur;

import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.Univers.Armes;

public class VueArc extends VueArmes{
    public VueArc() {
      super(new Image("file:src/main/resources/images/arc.png"),new Armes("arc",25),new Image("file:src/main/resources/images/arcInversé.png"));
    }
}
