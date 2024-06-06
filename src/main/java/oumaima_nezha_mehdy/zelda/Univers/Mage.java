package oumaima_nezha_mehdy.zelda.Univers;

import javafx.scene.paint.Color;

public class Mage extends Ennemi{

    public Mage(String nom, int x, int y, Champ m){
        super(nom,x,y,m);
    }

    public Mage(String nom, Champ m){
        super(nom,m);
    }

    @Override
    public void deplacer(){ }

    @Override
    public void attaquer(Acteur cible) {

    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }


}
