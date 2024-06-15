package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Armes.Bombe;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Armes.EpeeDeFer;


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
                    if (a != null) {
                        System.out.println(val.getVueArmesJeu().getChildren().remove(val.getVueArmesJeu().lookup("#" + a.getId())));
                        VueArmes vA = null;
                        if (a instanceof EpeeDeFer) {
                            vA = new VueEpee(a);
                        } else if (a instanceof Bombe) {
                            vA = new VueBombe(a);
                        }
                        a.getXProperty().bind(link.getXProperty());
                        a.getYProperty().bind(link.getYProperty());
                        ImageView armecase = new ImageView();
                        int indice = link.getInventaire().indexOf(a);
                        armecase.setImage(vA.getArmeVue().getImage());
                        armecase.setFitWidth(80);
                        armecase.setFitHeight(80);
                        armecase.setId("case" + indice);
                        val.getVueInventaire().set(indice, vA);
                        vueArmesInventaire.getChildren().add(armecase);
                        armecase.setX(vueCaseInventaire.getLayoutX() + (100 * indice) + 65);
                        armecase.setY(40);
                        System.out.println("zaza");
                    }
                }
            }
        }
        if (change.wasRemoved()){
            for (Armes a : change.getRemoved()) {
                if(a!=null) {
                    System.out.println(a.getX() + "............" + a.getY());
                    int indice = -1;
                    for (VueArmes vA : val.getVueInventaire())
                        if (vA != null && vA.getArme().equals(a))
                            indice = val.getVueInventaire().indexOf(vA);
                    val.lacher();
                    vueArmesInventaire.getChildren().remove(vueArmesInventaire.lookup("#case" + indice));
                    val.getVueInventaire().set(indice, null);
                }
            }
        }
    }
}