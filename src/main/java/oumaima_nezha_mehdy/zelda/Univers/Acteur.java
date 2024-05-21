package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {

    public static int id= 0;;
    private String nom;

    private Champ champ;

    private int vitesse=10;

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    private int xprecedent,yprecedent;

    public Acteur(String nom, int x , int y, Champ m){
        this.nom=nom;
        this.x.set(x);
        this.y.set(y);
        this.champ=m;
        id += 1;
    }
    public Acteur(String nom, Champ m){
        this.nom=nom;
        this.champ=m;
        this.x.set(m.getLongueur()/2);
        this.y.set(m.getLargeur()/2);
    }

    public void seDeplacer(String direction) {
        int x2base,y2base;
        x2base = this.x.getValue();
        y2base = this.y.getValue();
        switch (direction) {
            case "nord":
                this.y.set(this.y.getValue()-(1*vitesse));
                break;
            case "sud":
                this.y.set(this.y.getValue()+(1*vitesse));
                break;
            case "ouest":
                this.x.set(this.x.getValue()-(1*vitesse));
                break;
            case "est":
                this.x.set(this.x.getValue()+(1*vitesse));
                break;
            default:
        }
        xprecedent=x2base;
        yprecedent=y2base;
        if(!coordonnéPossible(this.x.getValue(),this.y.getValue())) {
            this.x.set(x2base);
            this.y.set(y2base);
        }
    }
    private boolean coordonnéPossible(int x,int y){
        boolean retourneur = x>=0&&y>=0&&x<=this.champ.getLongueur()*64&&y<=this.champ.getLargeur()*64;
        int haut,bas,gauche,droite;
        haut = 25;
        gauche = 15;
        bas = -20;
        droite = -10;
        boolean collisionhautgauche =champ.getChamp()[((x+gauche)/64) + ((y+haut)/64)*(champ.getLongueur())]!=2;
        boolean collisionbasdroite =champ.getChamp()[(x-droite)/64 + ((y-bas)/64)*(champ.getLongueur())]!=2;
        return retourneur&&(collisionhautgauche&&collisionbasdroite);
    }

    public int getXprecedent(){return xprecedent;}
    public int getYprecedent(){return yprecedent;}

    public int getX(){return x.getValue();}

    public int getY(){return y.getValue();}

    public static String getId() {
        return "#"+id;
    }

    public IntegerProperty getXProperty(){return x;}
    public IntegerProperty getYProperty(){return y;}

    public void setX(int x){this.x.setValue(x);}
    public void setY(int y){this.y.setValue(y);}
}
