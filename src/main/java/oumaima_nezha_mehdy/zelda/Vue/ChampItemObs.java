package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Armes.*;

public class ChampItemObs implements ListChangeListener<Armes> {

    private Pane VueArmeJeu;

    public ChampItemObs(Pane vueJeu){
        VueArmeJeu=vueJeu;
    }
    @Override
    public void onChanged(Change<? extends Armes> change) {
        while(change.next()){
            if(change.wasRemoved()) {
                for (Armes a : change.getRemoved())
                    VueArmeJeu.getChildren().remove(VueArmeJeu.lookup("#" + a.getId()));
                System.out.println("supp");
            }


        }
    }
}