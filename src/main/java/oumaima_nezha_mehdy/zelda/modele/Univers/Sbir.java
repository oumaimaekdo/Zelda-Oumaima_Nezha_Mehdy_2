package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sbir extends Acteur{

    private IntegerProperty x = new SimpleIntegerProperty(100);
    private IntegerProperty y = new SimpleIntegerProperty(100);
    private int vie = 100;
    private int vitesse=10;
    private int xDepart;
    private int xArrive;
    public Sbir(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
        this.xDepart=150;
        this.xArrive = 250;
        id++;
    }

    public int getX(){return x.getValue();}

    public int getY(){return y.getValue();}

    public static String getId() {
        return "#"+id;
    }
    public IntegerProperty getXProperty(){return x;}
    public IntegerProperty getYProperty(){return y;}

    public void setX(int x){this.x.setValue(x);}
    public void setY(int y){this.y.setValue(y);}

    public int getVie(){ return this.vie; }

    public void setVie(int vie){ this.vie = vie;}

    public int getVitesse() {
        return vitesse;
    }

    public void deplacementAleatoireX(){
        if(this.x.getValue() < xArrive){
            seDeplacer("est");
        } else if(this.x.getValue() > xDepart){
            seDeplacer("ouest");
        }
    }
    public void seDeplacer(String direction) {
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
    }




}
