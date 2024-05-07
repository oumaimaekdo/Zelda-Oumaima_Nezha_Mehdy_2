module oumaima_nezha_mehdy.zelda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens oumaima_nezha_mehdy.zelda to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda;
}