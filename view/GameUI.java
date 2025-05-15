package game.view;

import game.model.PortType;
import game.model.SquarePort;
import game.model.TrianglePort;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static game.controller.Constants.*;
import game.model.SystemModel;
import static game.controller.Constants.SYSTEMS_BOX_COLOR;
import static game.controller.Constants.SYSTEMS_BOX_HEIGHT;
import static game.controller.Constants.SYSTEMS_BOX_WIDTH;

public final class GameUI {
    public static Stage stage;
    private static GameUI INSTANCE;

    private GameUI(Stage stage) {
        GameUI.stage = stage;
    }

    public static GameUI getINSTANCE(Stage stage) {
        if (INSTANCE == null) {
            INSTANCE = new GameUI(stage);
        }
        return INSTANCE;
    }

    public static void run() {
        Scene scene = new Scene(Root.getINSTANCE(), STAGE_WIDTH, STAGE_HEIGHT, BACKGROUND_COLOR);

        SystemModel systemModel = new SystemModel(50, 50);
        new SquarePort(systemModel, PortType.INPUT);
        new TrianglePort(systemModel, PortType.OUTPUT);
        new SystemView(systemModel);
        SystemModel systemModel2 = new SystemModel(50, 250);
        new SquarePort(systemModel2, PortType.INPUT);
        new TrianglePort(systemModel2, PortType.INPUT);
        new SquarePort(systemModel2, PortType.OUTPUT);
        new SystemView(systemModel2);


        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
