package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {
    private String nom;

    private Champ champ;
    private static int id=0;
    private int vitesse=10;

    private String directionActuelle = "nord";

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

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

        this.directionActuelle = direction;

        int x2base,y2base;
        x2base = this.x.getValue();
        y2base = this.y.getValue();
        switch (direction) {
            case "nord":
                if (champ.gererLesCollisions(this.x.getValue(), this.y.getValue() - (1 * vitesse))) {
                    this.y.set(this.y.getValue() - (1 * vitesse));
                }
                break;
            case "sud":
                if (champ.gererLesCollisions(this.x.getValue(), this.y.getValue() + (1 * vitesse))) {
                    this.y.set(this.y.getValue() + (1 * vitesse));
                }
                break;
            case "ouest":
                if (champ.gererLesCollisions(this.x.getValue() - (1 * vitesse), this.y.getValue())) {
                    this.x.set(this.x.getValue() - (1 * vitesse));
                }
                break;
            case "est":
                if (champ.gererLesCollisions(this.x.getValue() + (1 * vitesse), this.y.getValue())) {
                    this.x.set(this.x.getValue() + (1 * vitesse));
                }
                break;
            default:
        }


        /*
        if(!coordonnéPossible(this.x.getValue(),this.y.getValue())) {
            this.x.set(x2base);
            this.y.set(y2base);
        }

         */

    }

    private boolean coordonnéPossible(int x,int y){
        //return x>=0&&y>=0&&x<this.champ.getLongueur()&&y<this.champ.getLargeur()&&this.champ.getChamp()[y][x]==0;
        return champ.peutTraverserBloc(x,y);

        // pour gérer les collisions


    }

    public int getX(){return x.getValue();}

    public int getY(){return y.getValue();}

    public String getDirection(){ return this.directionActuelle; }

    public IntegerProperty getXProperty(){return x;}
    public IntegerProperty getYProperty(){return y;}

    public static String getId(){ return "#"+id;}
}

/*
package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Acteur {





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
      /*  if(!coordonnéPossible(this.x.getValue(),this.y.getValue())) {
            this.x.set(x2base);
            this.y.set(y2base);
        }

    }*/

    //x>=0&&y>=0&&x<this.champ.getLongueur()&&y<this.champ.getLargeur()

/*private boolean coordonnéPossible(int x,int y){
    return champ.peutTraverserBloc(x,y);
}

public int getX(){return x.getValue();}

public int getY(){return y.getValue();}

public static String getId() {
    return "#"+id;
}

public IntegerProperty getXProperty(){return x;}
public IntegerProperty getYProperty(){return y;}
}
 */
