package oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs;

import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

public class DonneurQuetes extends Acteur {


    public DonneurQuetes(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);

    }
    public boolean linkAutour() {
        Acteur link = getChamp().getLink();
        boolean present = false;
        int rayon = 30;
            if ((this.getY() - rayon <= link.getY() && link.getY() <= this.getY() + rayon) && (this.getX() - rayon <= link.getX() && link.getX() <= this.getX() + rayon)){
                present = true;
            }
        //System.out.println("Link est present");
        return present;
    }

}
