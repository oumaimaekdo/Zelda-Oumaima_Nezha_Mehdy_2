package oumaima_nezha_mehdy.zelda.modele.Univers;

public class Boss extends Ennemi{


    public Boss(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
    }

    @Override
    public void seDirigerVersLink() {

    }

/*
    public void seDirigerVersLink() {
        Acteur link = this.getChamp().getLink();
        if (link == null) {
            return;
        }

        int linkX = link.getX();
        int linkY = link.getY();
        int deltaX = linkX - this.getX();
        int deltaY = linkY - this.getY();

        String direction = "";
        if(!estmort()){
            if (!estEnCollisionAvec(getChamp().getLink())) {
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX > 0) {
                        direction = "est";
                    } else {
                        direction = "ouest";
                    }
                } else {
                    if (deltaY > 0) {
                        direction = "sud";
                    } else {
                        direction = "nord";
                    }
                }
                this.seDeplacer(direction);
            } else {
                direction = "ouest";
            }
        }else{
            getChamp().mortActeur(this);
        }

    }

 */
}
