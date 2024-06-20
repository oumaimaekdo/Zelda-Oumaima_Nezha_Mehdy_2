package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public abstract class Ennemi extends Acteur {



    public Ennemi(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);

        setVie(1000);
    }

    public abstract void seDirigerVersLink();

    public void attaquerLink(){

        int rayon = 20;

        for(int i=0; i < champ.getListActeur().size(); i++){
            if(champ.getListActeur().get(i).getX() < rayon && champ.getListActeur().get(i).getY() < rayon){
                champ.getListActeur().get(i).setVie(10);
            }
        }

    }

    public boolean estmort(){
        return getVie()<=0;
    }

}
