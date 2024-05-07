package oumaima_nezha_mehdy.zelda.modele;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TileMap extends Application {

    // Tableau 2D d'entiers représentant le tilemap
    int[][] tilemap = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0}
    };

    @Override
    public void start(Stage primaryStage) {
        // Créer une grille pour afficher le tilemap
        GridPane gridPane = new GridPane();

        // Parcourir le tilemap et afficher chaque case
        for (int i = 0; i < tilemap.length; i++) {
            for (int j = 0; j < tilemap[i].length; j++) {
                Rectangle rectangle = new Rectangle(50, 50);
                if (tilemap[i][j] == 0) {
                    rectangle.setFill(Color.GREEN);
                } else {
                    rectangle.setFill(Color.BROWN);
                }
                // Ajouter le carré à la grille
                gridPane.add(rectangle, j, i);
            }
        }

        // Créer une scène et ajouter la grille
        Scene scene = new Scene(gridPane);
        // Redimensionner la scène pour s'adapter à la grille
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tilemap");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
