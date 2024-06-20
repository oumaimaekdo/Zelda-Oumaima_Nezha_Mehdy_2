package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.ListChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Bombe;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.EpeeDeFer;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Clé;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs.Link;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Outils;

class InventaireObs implements ListChangeListener<Outils> {

        private Link link;

    private Pane vueArmesInventaire;

    private HBox vueCaseInventaire;
    private VueActLink val;

    InventaireObs(Link link,Pane vAI,HBox vCI,VueActLink val) {
        this.link = link;
        this.vueArmesInventaire=vAI;
        this.vueCaseInventaire=vCI;
        this.val=val;
    }

    @Override
    public void onChanged(Change<? extends Outils> change) {
        while(change.next()){
            if(change.wasAdded()){
                for(Outils a : change.getAddedSubList() ) {
                    if (a != null) {
                        System.out.println(val.getVueArmesJeu().getChildren().remove(val.getVueArmesJeu().lookup("#" + a.getId())));
                            VueOutils vA = null;
                        if (a instanceof EpeeDeFer) {
                             vA = new VueEpee((Armes) a);
                        }
                        if(a instanceof Clé){
                             vA = new VueClé((Clé)a);
                        }
                        if (a instanceof Bombe) {
                            vA = new VueBombe((Bombe)a);
                        }
                        a.getXProperty().bind(link.getXProperty());
                        a.getYProperty().bind(link.getYProperty());
                        ImageView armecase = new ImageView();
                        int indice = link.getInventaire().indexOf(a);
                        armecase.setImage(vA.getVue().getImage());
                        armecase.setFitWidth(80);
                        armecase.setFitHeight(80);
                        armecase.setId("case" + indice);
                        val.getVueInventaire().set(indice,vA);
                        vueArmesInventaire.getChildren().add(armecase);
                        armecase.setX(vueCaseInventaire.getLayoutX() + (100 * indice) + 65);
                        armecase.setY(40);


                    }
                }
            }
        }
        if (change.wasRemoved()){
            for (Outils a : change.getRemoved()) {
                if(a!=null) {
                    System.out.println(a.getX() + "............" + a.getY());
                    int indice = -1;
                    for (VueOutils vA : val.getVueInventaire())
                        if (vA != null && vA.getOutils().equals(a))
                            indice = val.getVueInventaire().indexOf(vA);
                    val.lacher();
                    vueArmesInventaire.getChildren().remove(vueArmesInventaire.lookup("#case" + indice));
                    val.getVueInventaire().set(indice, null);
                }
            }
        }
    }
}