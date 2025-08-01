package game.blueprinthell;

import game.controller.GameController;
import game.service.GameService;
import game.view.GameView;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        GameView gameView = new GameView(stage);
        GameController gameController = new GameController(gameView);
        gameView.setGameController(gameController);
        gameView.showMenu();

    }

    public static void main(String[] args) {
        launch(args);
    }
}