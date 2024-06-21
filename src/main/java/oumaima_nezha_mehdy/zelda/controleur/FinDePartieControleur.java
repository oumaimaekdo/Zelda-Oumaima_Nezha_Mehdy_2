package oumaima_nezha_mehdy.zelda.controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oumaima_nezha_mehdy.zelda.Main;
import oumaima_nezha_mehdy.zelda.Vue.VueActLink;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinDePartieControleur implements Initializable {

    private VueActLink linkControl;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //this.linkControl = new VueActLink(vueActeur, champ, champ.gettT(), vueArmes, vueInventaire, vueArmesInventaire,vuePointsDeVie);
    }

    public void Quitter(ActionEvent actionEvent) {
        Platform.exit();
    }

    /*
    private void showFinDePartie() {
        // Assuming you have a method in FinDePartieControleur to handle the transition
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FinDePartie.fxml"));
                Parent root = loader.load();
                FinDePartieControleur controller = loader.getController();
                controller.setupEndScreen(); // assuming you have this method
                Stage stage = (Stage) vueActLink.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

     */
}
