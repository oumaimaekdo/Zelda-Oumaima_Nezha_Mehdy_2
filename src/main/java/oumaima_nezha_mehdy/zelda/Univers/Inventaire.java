package oumaima_nezha_mehdy.zelda.Univers;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Armes> inventaire;


    public Inventaire(){
        this.inventaire = new ArrayList<>();
    }

    public ArrayList<Armes> ajouterArme(Armes arme){
        this.inventaire.add(arme);
        return this.inventaire;
    }


}
