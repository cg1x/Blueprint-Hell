package game.blueprinthell;

import game.view.GameUI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameUI.getINSTANCE(stage);
        GameUI.run();
    }

    public static void main(String[] args) {
        launch(args);
    }
}