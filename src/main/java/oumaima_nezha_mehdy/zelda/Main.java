package oumaima_nezha_mehdy.zelda;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import oumaima_nezha_mehdy.zelda.controleur.Controleur;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        Controleur controller = fxmlLoader.getController();
        stage.setTitle("B-O Hazard");
        stage.setScene((scene));
        scene.setOnKeyPressed(e->controller.getClavier().handle(e));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
