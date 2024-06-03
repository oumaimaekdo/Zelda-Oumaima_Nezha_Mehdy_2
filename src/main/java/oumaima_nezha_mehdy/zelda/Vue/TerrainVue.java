package oumaima_nezha_mehdy.zelda.Vue;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import oumaima_nezha_mehdy.zelda.Main;
import oumaima_nezha_mehdy.zelda.modele.Univers.MapPossible;

import java.net.URL;


public class TerrainVue {
    public TerrainVue(MapPossible MapPossible, TilePane map){
        int[] tab = MapPossible.test3.getCarte();

        for (int i = 0; i < tab.length; i++) {

            Image image = null;

            if (tab[i] == 12) {
                URL urlImageSol = Main.class.getResource("file:images/herbe2.png");
                image = new Image(String.valueOf(urlImageSol));
            }/* else if (tab[i] == 1) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/pointDep.jpg");
                image = new Image(String.valueOf(urlImageSol));
            } else if (tab[i] == 2) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/pointArv.jpg");
                image = new Image(String.valueOf(urlImageSol));
            } else if (tab[i] == 7) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/contour.png");
                image = new Image(String.valueOf(urlImageSol));
            } else if (tab[i] == 9) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/chemin.png");
                image = new Image(String.valueOf(urlImageSol));
            } else if (tab[i] == 3) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/eauu.png");
                image = new Image(String.valueOf(urlImageSol));
            } else if (tab[i] == 8) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/pont.png");
                image = new Image(String.valueOf(urlImageSol));
            } else if (tab[i] == 10) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/rocheEau.png");
                image = new Image(String.valueOf(urlImageSol));
            } else if (tab[i] == 5) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/mur.png");
                image = new Image(String.valueOf(urlImageSol));
            }  else if (tab[i] == 88) {
                URL urlImageSol = LancementJeu.class.getResource("ImageTuile/lave.png");
                image = new Image(String.valueOf(urlImageSol));
            }*/

            ImageView imageView = new ImageView(image);
            map.getChildren().add(imageView);

        }
    }
}