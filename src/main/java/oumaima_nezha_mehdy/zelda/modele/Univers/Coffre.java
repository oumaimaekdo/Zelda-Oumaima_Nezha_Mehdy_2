package oumaima_nezha_mehdy.zelda.modele.Univers;

import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

public class Coffre extends Bloc{

    private Armes contenu;

    private boolean ouvert;

    private Champ champ;

    private String clé;

    public Coffre(Armes contenu,String clé,Champ c){
        super(false,700,700);
        this.contenu=contenu;
        this.clé=clé;
        this.champ=c;
        this.champ.ajouterBloc(this);
    }


    @Override
    public void interagir() {

    }
}
