package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

public class Coffre extends Bloc{

    private Armes contenu;

    private BooleanProperty ouvert = new SimpleBooleanProperty();

    private Champ champ;

    private String clé;

    public Coffre(Armes contenu,String clé,Champ c){
        super(false,700,700);
        this.contenu=contenu;
        this.clé=clé;
        this.champ=c;
        this.champ.ajouterCoffre(this);
        this.ouvert.set(false);
    }


    @Override
    public void interagir() {
        ouvert.set(true);
    }
    public boolean getouvert(){return ouvert.getValue();}
    public BooleanProperty getouvertProperty(){return ouvert;}
}
