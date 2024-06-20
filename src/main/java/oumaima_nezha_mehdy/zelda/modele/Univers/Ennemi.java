package oumaima_nezha_mehdy.zelda.modele.Univers;

public abstract class Ennemi extends Acteur {



    public Ennemi(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);

        setVie(1000);
    }


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

    public abstract int getDegat();

    public void attaquerLink(){

        if(linkAutour()){
            champ.getLink().setVie(champ.getLink().getVie()-this.getDegat());
        }


    }

    public boolean linkAutour() {

        int rayon = 50;
        Link link = champ.getLink();
        boolean present = false;
            if ((this.getY() - rayon <= link.getY() && link.getY() <= this.getY() + rayon) && (this.getX() - rayon <= link.getX() && link.getX() <= this.getX() + rayon)){
                present = true;
            }
        return present;
    }

    public boolean estmort(){
        return getVie()<=0;
    }

    public abstract boolean estUnSbire();

}
