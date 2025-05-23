package game.blueprinthell;

import game.view.GameUI;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        GameUI.getINSTANCE(stage);
        GameUI.run();
    }

    public static void main(String[] args) {
        launch(args);
    }
}