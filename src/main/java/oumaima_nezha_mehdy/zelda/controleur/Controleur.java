package oumaima_nezha_mehdy.zelda.controleur;

import javafx.scene.layout.TilePane;
import oumaima_nezha_mehdy.zelda.modele.Environnement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private TilePane map;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Environnement env = new Environnement(30, 30);
        env.afficherMap();
        env.creationCase();
        afficherMap(env.getMap());
    }


    public void afficherMap(int[][] map) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                Rectangle rectangle = new Rectangle(20, 20);
                switch (map[x][y]) {
                    case 0:
                        rectangle.setFill(Color.OLIVE);
                        break;
                    case 1:
                        rectangle.setFill(Color.BEIGE);
                }
            }
        }
    }
}

