package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import oumaima_nezha_mehdy.zelda.modele.Univers.Ennemi;

public class EnnemisObs {


    private ObservableList<Ennemi> ennemis;

    public EnnemisObs() {
        ennemis = FXCollections.observableArrayList();

        // Ajout d'un écouteur pour réagir aux changements dans la liste
        ennemis.addListener(new ListChangeListener<Ennemi>() {
            @Override
            public void onChanged(Change<? extends Ennemi> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        for (Ennemi e : change.getAddedSubList()) {
                            System.out.println(e.getNom() + " a été ajouté à la liste des ennemis.");
                        }
                    }
                    if (change.wasRemoved()) {
                        for (Ennemi e : change.getRemoved()) {
                            System.out.println(e.getNom() + " a été retiré de la liste des ennemis.");
                        }
                    }
                }
            }
        });
    }

    public ObservableList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public void ajouterEnnemi(Ennemi ennemi) {
        if (!ennemis.contains(ennemi)) {
            ennemis.add(ennemi);
        }
    }

    public void retirerEnnemi(Ennemi ennemi) {
        ennemis.remove(ennemi);
    }
}
