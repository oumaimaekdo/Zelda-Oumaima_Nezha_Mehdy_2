package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Univers.Bloc;
import oumaima_nezha_mehdy.zelda.modele.Univers.Coffre;

public class ListObsBloc implements ListChangeListener<Coffre> {

    private Pane VueBloc;

    public ListObsBloc(Pane vueB){
        this.VueBloc=vueB;
    }
    @Override
    public void onChanged(Change<? extends Coffre> change) {
        while(change.next()){
            if(change.wasAdded())
                for(Coffre b : change.getAddedSubList()){
                       VueCoffre vc= new VueCoffre(b);
                        VueBloc.getChildren().add(vc.getVue());
                }
        }
    }
}
