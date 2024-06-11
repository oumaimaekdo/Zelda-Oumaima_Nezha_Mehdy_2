package oumaima_nezha_mehdy.zelda.Univers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class MapInt {

    public static int tT = 64;

    private int[]  carte;

    private int longueur;
    private int largeur;



    public MapInt(int[] map,int longueur,int largeur){
        carte=map;
        this.longueur=longueur;
        this.largeur=largeur;
    }
    public void CreationMap(TilePane map) {
        Image eau = new Image("file:src/main/resources/images/Bloc/Eau.jpg");
        Image terre = new Image("file:src/main/resources/images/Bloc/Herbe.jpg");
        Image arbre = new Image("file:src/main/resources/images/Bloc/arbre.png");
        Image maison = new Image("file:src/main/resources/images/Bloc/maison.png");
        Image pont = new Image("file:src/main/resources/images/pont.png");


        for (int i = 0; i < carte.length; i++) {
            ImageView imageView = new ImageView();
            map.getChildren().add(imageView);
            imageView.setId(String.valueOf(i));
            imageView.setFitHeight(tT);
            imageView.setFitWidth(tT);
            double col = i % largeur;
            double lig = Math.floor(i/longueur);
            double x = col * largeur;
            double y = lig * longueur;
            imageView.setX(x);
            imageView.setY(y);
            switch (carte[i]) {
                case 0:
                    imageView.setImage(terre);
                    break;
                case 1:
                    imageView.setImage(arbre);
                    break;
                case 2:
                    imageView.setImage(eau);
                    break;
                case 3:
                    imageView.setImage(pont);
                    break;

            }
        }
    }

    public int[] getCarte() {
        return carte;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLongueur() {
        return longueur;
    }
}
