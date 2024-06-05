package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Sbir extends Acteur {

    private int vie;
    private int vitesse;
    private IntegerProperty xDepart;
    private IntegerProperty xArrive;
    private IntegerProperty yDepart;
    private IntegerProperty yArrive;
    private boolean moveX; // Indicates if the Sbir moves on the X-axis or Y-axis
    private Champ champ;

    public Sbir(String nom, int x, int y, Champ m, boolean moveX) {
        super(nom, x, y, m);
        this.champ = m;
        this.xDepart = new SimpleIntegerProperty(x - 150);  // Arbitrary values for demonstration
        this.xArrive = new SimpleIntegerProperty(x + 150);  // You can customize these as needed
        this.yDepart = new SimpleIntegerProperty(y - 150);
        this.yArrive = new SimpleIntegerProperty(y + 150);
        this.vie = 100;
        this.vitesse = 10;
        this.moveX = moveX;
    }

    public void deplacementAleatoire() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (moveX) {
                    deplacementAleatoireX();
                } else {
                    deplacementAleatoireY();
                }
            }
        };
        timer.start();
    }

    private void deplacementAleatoireX() {
        if (getX() < xArrive.get()) {
            seDeplacer("est");
        } else if (getX() > xDepart.get()) {
            seDeplacer("ouest");
        }
    }

    private void deplacementAleatoireY() {
        if (getY() < yArrive.get()) {
            seDeplacer("sud");
        } else if (getY() > yDepart.get()) {
            seDeplacer("nord");
        }
    }

    public void seDeplacer(String direction) {
        switch (direction) {
            case "nord":
                this.yDepart.set(this.yDepart.getValue() - (1 * vitesse));
                break;
            case "sud":
                this.yDepart.set(this.yDepart.getValue() + (1 * vitesse));
                break;
            case "ouest":
                this.xDepart.set(this.xDepart.getValue() - (1 * vitesse));
                break;
            case "est":
                this.xDepart.set(this.xDepart.getValue() + (1 * vitesse));
                break;
            default:
        }
    }

    // Getter and setter methods for x and y using IntegerProperty
    @Override
    public int getX() {
        return super.getXProperty().get();
    }

    @Override
    public void setX(int x) {
        super.getXProperty().set(x);
    }

    @Override
    public IntegerProperty getXProperty() {
        return super.getXProperty();
    }

    @Override
    public int getY() {
        return super.getYProperty().get();
    }

    @Override
    public void setY(int y) {
        super.getYProperty().set(y);
    }

    @Override
    public IntegerProperty getYProperty() {
        return super.getYProperty();
    }

    public Champ getChamp() {
        return champ;
    }

    public void setChamp(Champ champ) {
        this.champ = champ;
    }
}

        /**/
