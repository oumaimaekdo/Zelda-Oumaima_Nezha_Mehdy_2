package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.Queue;

import static java.nio.file.Files.move;

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
