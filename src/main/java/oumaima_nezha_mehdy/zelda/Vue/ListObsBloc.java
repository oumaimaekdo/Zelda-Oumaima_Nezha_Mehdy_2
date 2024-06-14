package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Univers.Bloc;
import oumaima_nezha_mehdy.zelda.modele.Univers.Coffre;

public class ListObsBloc implements ListChangeListener<Bloc> {

    private Pane VueBloc;

    public ListObsBloc(Pane vueB){
        this.VueBloc=vueB;
    }
    @Override
    public void onChanged(Change<? extends Bloc> change) {
        while(change.next()){
            if(change.wasAdded())
                for(Bloc b : change.getAddedSubList()){
                    if(b instanceof Coffre){
                        ImageView iv = new ImageView();
                        iv.setImage(new Image("file:src/main/resources/images/Bloc/giftNormal.png"));
                        iv.setFitWidth(50);
                        iv.setFitHeight(50);
                        iv.setX(700);
                        iv.setY(700);
                        VueBloc.getChildren().add(iv);

                    }
                }
        }
    }
}
