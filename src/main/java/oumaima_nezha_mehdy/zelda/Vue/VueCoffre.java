package oumaima_nezha_mehdy.zelda.Vue;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Coffre;

public class VueCoffre {


    private ImageView Vue;
    private Image coffreFermé;
    private Image coffreOuvert;

    private BooleanProperty coffreouvert = new SimpleBooleanProperty();

    private Coffre c;



    public VueCoffre(Coffre c){

        coffreFermé = new Image("file:src/main/resources/images/Bloc/giftNormal.png");
        coffreOuvert = new Image("file:src/main/resources/images/Bloc/giftopen.png");
        coffreouvert.bind(c.getouvertProperty());
        this.Vue = new ImageView();
        Vue.setImage(coffreFermé);
        Vue.setFitWidth(50);
        Vue.setFitHeight(50);
        Vue.setX(700);
        Vue.setY(700);
        this.c = c;
        this.c.getouvertProperty().addListener((observable -> {
            Interagir();
        }));
    }
    public ImageView getVue() {
        return Vue;
    }

    public void Interagir(){
        if(coffreouvert.getValue()){
            Vue.setImage(coffreOuvert);
        }
    }
}
