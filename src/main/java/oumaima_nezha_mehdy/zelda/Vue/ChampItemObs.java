package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Armes.*;

public class ChampItemObs implements ListChangeListener<Armes> {

    private Pane VueArmeJeu;
    private VueActLink val;

    public ChampItemObs(Pane vueJeu,VueActLink val){
        VueArmeJeu=vueJeu;
        this.val=val;
    }
    @Override
    public void onChanged(Change<? extends Armes> change) {
        while(change.next()){
            if(change.wasRemoved()) {
                for (VueArmes va :val.getVueItem() ) {
                    if(change.getRemoved().contains(va.getArmeVue())) {
                        VueArmeJeu.getChildren().remove(va.getArmeVue());
                        System.out.println("supp");
                    }
                }
            }
            if(change.wasAdded()){
                for (Armes a : change.getAddedSubList()) {

                }
            }


        }
    }
}