package oumaima_nezha_mehdy.zelda.modele.Univers;


import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;

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
