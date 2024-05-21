package oumaima_nezha_mehdy.zelda.Univers;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class Gameloop extends AnimationTimer {

    private GraphicsContext gc;
    private long dernierUpdate = 0;

    public Gameloop(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void handle(long maintenant) {
        if (dernierUpdate == 0) {
            dernierUpdate = maintenant;
            return;
        }
        long tempsEcoule = maintenant - dernierUpdate;
        dernierUpdate = maintenant;

        update(tempsEcoule / 1_000_000.0); // Conversion millisecond
        render();
    }

    private void update(double Time) {
        // Mettre à jour l'état du jeu, y compris les positions des personnages,
        // les interactions, les collisions, etc.

        // Exemple: playerX += 1;

        //met a jour les positions des personnages

    }

    private void render() {
        // Effacez l'écran
        gc.clearRect(0, 0, 800, 600);

        // Dessinez les éléments du jeu
        // Exemple: gc.drawImage(playerImage, playerX, playerY);
    }
}

