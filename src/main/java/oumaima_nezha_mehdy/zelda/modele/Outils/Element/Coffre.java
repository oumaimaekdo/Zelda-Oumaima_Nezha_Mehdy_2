package oumaima_nezha_mehdy.zelda.modele.Outils.Element;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Map.Bloc;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

import java.util.ArrayList;

public class Coffre extends Bloc {

    private ArrayList<Armes> contenu;

    private BooleanProperty ouvert = new SimpleBooleanProperty();

    private Champ champ;


    private int nbInteraction;

    private String cléRequise;

    public Coffre(ArrayList<Armes> contenu, String cléRequise, Champ c){
        super(false,700,700);
        this.contenu=contenu;
        this.cléRequise=cléRequise;
        this.champ=c;
        this.champ.ajouterCoffre(this);
        this.ouvert.set(false);
        nbInteraction=0;
    }


    public void interagir() {
        ouvert.set(true);
        nbInteraction++;
    }
    public boolean getouvert(){return ouvert.getValue();}
    public BooleanProperty getouvertProperty(){return ouvert;}

    public int getNbInteraction() {
        return nbInteraction;
    }

    public ArrayList<Armes> getContenu() {
        ArrayList<Armes> returneur  = contenu;
        contenu=null;
        return returneur;
    }
    public String getCléRequise(){return cléRequise;}
}
