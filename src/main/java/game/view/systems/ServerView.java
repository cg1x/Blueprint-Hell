package game.view.systems;

import game.controller.GameController;
import game.model.ports.Port;
import game.model.systems.Server;
import game.view.Root;
import javafx.scene.Group;
import javafx.scene.shape.Shape;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static game.controller.Constants.INDICATOR_HEIGHT;
import static game.controller.Constants.INDICATOR_MARGIN;
import static game.controller.Constants.INDICATOR_WIDTH;
import static game.controller.Constants.SYSTEM_COLOR;
import static game.controller.Constants.SYSTEM_SIZE;
import static game.controller.Constants.SYSTEM_TOP_COLOR;
import static game.controller.Constants.SYSTEM_TOP_HEIGHT;

public class ServerView extends GeneralSystemView<Server> {
    public Button runButton;
    private GameController gameController;

    public ServerView(Server system) {
        super(system);
    }

    @Override
    public void paint() {
        super.paint();
        //paint run button
        runButton = new Button("Run");
        runButton.setPrefWidth(60);
        runButton.setPrefHeight(20);
        runButton.setLayoutX(x + 30);
        runButton.setLayoutY(y + (blockCnt + 1) * SYSTEM_TOP_HEIGHT/2 + 5);
        runButton.setFont(new Font("Verdana", 12));
        runButton.setDisable(true);
        runButton.setOnAction(e -> {
            gameController.generatePackets();
            gameController.startGame();
            runButton.setDisable(true);
        });
        shape.getChildren().add(runButton);

        Root.getINSTANCE().getChildren().add(runButton);
    }

    public void updateButton() {
        runButton.setDisable(!gameController.getGameService().canStartGame());
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
