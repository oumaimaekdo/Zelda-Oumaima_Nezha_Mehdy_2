package oumaima_nezha_mehdy.zelda.Univers;

import java.util.*;

public class Volcanorax extends Ennemi{

    private Acteur link;
    private DeplacementBFS deplacementBFS;

    public Volcanorax(int x, int y, Champ m, Acteur link){

        super("Volcanorax",x,y,m);
        this.link = link;
        this.deplacementBFS = new DeplacementBFS();
    }

    public Volcanorax(Champ m, Acteur link){

        super("Volcanorax",m);
        this.link = link;
        this.deplacementBFS = new DeplacementBFS();
    }

    public void seDeplaceBoss(){

        ArrayList<int[]> chemin = deplacementBFS.deplacementBFS(link.getX()/64, link.getY()/64, super.champ, this);
        suivreChemin(chemin);

    }
}
