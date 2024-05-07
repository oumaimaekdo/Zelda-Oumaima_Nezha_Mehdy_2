package oumaima_nezha_mehdy.zelda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vue.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        stage.setTitle("B-O Hazard");
        stage.setScene((scene));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
