/*
Cette classe à pour but de lancer la page d'accueil.
*/

package oumaima_nezha_mehdy.zelda;
import oumaima_nezha_mehdy.zelda.controleur.AccueilController;
import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import java.io.IOException;



public class Main extends Application {
    @Override
    public void start(Stage newWindow) throws IOException {
        newWindow.setTitle("Acceuil");
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Acceuil.fxml"));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
