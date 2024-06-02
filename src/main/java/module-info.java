module oumaima_nezha_mehdy.zelda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens oumaima_nezha_mehdy.zelda to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda;
    exports oumaima_nezha_mehdy.zelda.controleur;
    opens oumaima_nezha_mehdy.zelda.controleur to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda.Vue;
    opens oumaima_nezha_mehdy.zelda.Vue to javafx.fxml;
}