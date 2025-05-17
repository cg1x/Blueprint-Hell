package game.view;

import game.model.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static game.controller.Constants.*;

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
        Scene scene = new Scene(Root.getINSTANCE(), STAGE_WIDTH, STAGE_HEIGHT);
        Root.getINSTANCE().setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        SystemModel generalSystem = new SystemModel(50, 50);
        new SquarePort(generalSystem, PortType.INPUT);
        new TrianglePort(generalSystem, PortType.OUTPUT);
        new SystemView(generalSystem);
        SystemModel generalSystem2 = new SystemModel(250, 50);
        new SquarePort(generalSystem2, PortType.INPUT);
        new TrianglePort(generalSystem2, PortType.INPUT);
        new SquarePort(generalSystem2, PortType.OUTPUT);
        new SystemView(generalSystem2);
        StartSystem generalSystem3 = new StartSystem(200, 390);
        new TrianglePort(generalSystem3, PortType.OUTPUT);
        new SquarePort(generalSystem3, PortType.OUTPUT);
        new StartSystemView(generalSystem3);
        EndSystem generalSystem4 = new EndSystem(1300, 390);
        new TrianglePort(generalSystem4, PortType.INPUT);
        new SquarePort(generalSystem4, PortType.INPUT);
        new EndSystemView(generalSystem4);




        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
