package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Armes.*;
import oumaima_nezha_mehdy.zelda.modele.Univers.Outils;

public class ChampItemObs implements ListChangeListener<Outils> {

    private Pane VueArmeJeu;
    private VueActLink val;

    public ChampItemObs(Pane vueJeu,VueActLink val){
        VueArmeJeu=vueJeu;
        this.val=val;
    }
    @Override
    public void onChanged(Change<? extends Outils> change) {
        while(change.next()){
            if(change.wasRemoved()) {
                for (VueOutils va :val.getVueItem() ) {
                    if(change.getRemoved().contains(va.getVue())) {
                        VueArmeJeu.getChildren().remove(va.getVue());
                        System.out.println("supp");
                    }
                }
            }
            if(change.wasAdded()){
                for (Outils a : change.getAddedSubList()) {

                }
            }


        }
    }
}