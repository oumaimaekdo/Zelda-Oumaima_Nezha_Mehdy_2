package oumaima_nezha_mehdy.zelda.controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.Univers.Arc;
import oumaima_nezha_mehdy.zelda.Univers.Armes;

class InventaireObs implements ListChangeListener<Armes> {

    private Acteur link;

    private Pane vueArmesInventaire;

    private HBox vueCaseInventaire;
    private VueActLink val;

    InventaireObs(Acteur link,Pane vAI,HBox vCI,VueActLink val) {
        this.link = link;
        this.vueArmesInventaire=vAI;
        this.vueCaseInventaire=vCI;
        this.val=val;
    }

    @Override
    public void onChanged(Change<? extends Armes> change) {
        while(change.next()){
            if(change.wasAdded()){
                for(Armes a : change.getAddedSubList() ) {
                    if(a!=null) {
                        VueArmes vA = null;
                        if (a.getNom().equals("Epee")) {
                            vA = new VueEpee(a);
                        }
                        if (a.getNom().equals("Arc")) {
                            vA = new VueArc(a);
                        }
                        ImageView armecase = new ImageView();
                        int indice = link.getInventaire().indexOf(a);
                        armecase.setImage(vA.getArmeVue().getImage());
                        System.out.println(vA.getArmeVue().getImage().getHeight() + ".........." + vA.getArmeVue().getImage().getWidth());
                        armecase.setFitWidth(80);
                        armecase.setFitHeight(80);
                        armecase.setId("case" + indice);
                        vueArmesInventaire.getChildren().add(armecase);
                        armecase.setX(vueCaseInventaire.getLayoutX() + (100 * indice) + 65);
                        armecase.setY(40);
                        val.getVueInventaire().set(indice, vA);
                    }
                }
            }
            if (change.wasRemoved()){
                for (Armes a : change.getRemoved()) {
                   int indice = -1;
                    for(VueArmes vA : val.getVueInventaire())
                        if(vA!=null&&vA.getArme().equals(a))
                            indice = val.getVueInventaire().indexOf(vA);
                    val.lacher();
                   vueArmesInventaire.getChildren().remove(vueArmesInventaire.lookup("#case" + indice));
                    val.getVueInventaire().set(indice,null);
                }
            }
        }
    }
}
