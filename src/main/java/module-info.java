module game.blueprinthell {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens game.blueprinthell to javafx.fxml;
    exports game.blueprinthell;
}