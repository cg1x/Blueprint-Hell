package game.view;

import game.model.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static game.controller.Constants.*;

public final class GameUI {
    public static boolean first = true;
    public static int level = 1;
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

    public static void runLvl1() {
        level = 1;
        if (first) {
            first = false;
        } else {
            Root.reset();
            Operator.getINSTANCE().reset();
        }
        Scene scene = new Scene(Root.getINSTANCE(), STAGE_WIDTH, STAGE_HEIGHT);
        Root.getINSTANCE().setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        StartSystem generalSystem3 = new StartSystem(200, 390);
        new SquarePort(generalSystem3, PortType.INPUT);
        new SquarePort(generalSystem3, PortType.OUTPUT);
        new SquarePort(generalSystem3, PortType.OUTPUT);
        new StartSystemView(generalSystem3);

        SystemModel generalSystem = new SystemModel(600, 500);
        new SquarePort(generalSystem, PortType.INPUT);
        new SquarePort(generalSystem, PortType.INPUT);
        new TrianglePort(generalSystem, PortType.OUTPUT);
        new TrianglePort(generalSystem, PortType.OUTPUT);
        new SystemView(generalSystem);
        SystemModel generalSystem2 = new SystemModel(1200, 390);
        new TrianglePort(generalSystem2, PortType.INPUT);
        new TrianglePort(generalSystem2, PortType.INPUT);
        new SquarePort(generalSystem2, PortType.OUTPUT);
        new SystemView(generalSystem2);


        Operator.getINSTANCE();

        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void runLvl2() {
        level = 2;
        if (first) {
            first = false;
        } else {
            Root.reset();
            Operator.getINSTANCE().reset();
        }
        Scene scene = new Scene(Root.getINSTANCE(), STAGE_WIDTH, STAGE_HEIGHT);
        Root.getINSTANCE().setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        StartSystem generalSystem3 = new StartSystem(200, 390);
        new SquarePort(generalSystem3, PortType.INPUT);
        new TrianglePort(generalSystem3, PortType.INPUT);
        new SquarePort(generalSystem3, PortType.OUTPUT);
        new TrianglePort(generalSystem3, PortType.OUTPUT);
        new StartSystemView(generalSystem3);

        SystemModel generalSystem = new SystemModel(600, 600);
        new SquarePort(generalSystem, PortType.INPUT);
        new TrianglePort(generalSystem, PortType.INPUT);
        new SquarePort(generalSystem, PortType.OUTPUT);
        new TrianglePort(generalSystem, PortType.OUTPUT);
        new SystemView(generalSystem);
        SystemModel generalSystem2 = new SystemModel(1000, 600);
        new SquarePort(generalSystem2, PortType.INPUT);
        new TrianglePort(generalSystem2, PortType.INPUT);
        new SquarePort(generalSystem2, PortType.OUTPUT);
        new TrianglePort(generalSystem2, PortType.OUTPUT);
        new SystemView(generalSystem2);
        SystemModel generalSystem4 = new SystemModel(1300, 200);
        new SquarePort(generalSystem4, PortType.INPUT);
        new TrianglePort(generalSystem4, PortType.INPUT);
        new SquarePort(generalSystem4, PortType.OUTPUT);
        new TrianglePort(generalSystem4, PortType.OUTPUT);
        new SystemView(generalSystem4);


        Operator.getINSTANCE();

        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void showLvl1fail() {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1600, 900, Color.BLACK);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        Text loseText = new Text("YOU LOST");
        loseText.setFill(Color.WHITE);
        loseText.setFont(new Font("Verdana",26));
        loseText.setX(750);
        loseText.setY(250);
        root.getChildren().add(loseText);

        Text packetLossText = new Text(String.format("PACKET LOSS: %.2f",
                (double)Operator.getINSTANCE().lostPacket/Operator.getINSTANCE().totalPacket*100) + "%");
        packetLossText.setFill(Color.WHITE);
        packetLossText.setFont(new Font("Verdana",26));
        packetLossText.setX(650);
        packetLossText.setY(350);
        root.getChildren().add(packetLossText);

        Button playAgainButton = new Button("PLAY AGAIN");
        playAgainButton.setPrefWidth(200);
        playAgainButton.setPrefHeight(50);
        playAgainButton.setLayoutX(700);
        playAgainButton.setLayoutY(450);
        playAgainButton.setFont(new Font("Verdana", 16));
        playAgainButton.setOnAction(e -> runLvl1());
        root.getChildren().add(playAgainButton);

        Button backButton = new Button("BACK TO MENU");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(50);
        backButton.setLayoutX(700);
        backButton.setLayoutY(550);
        backButton.setFont(new Font("Verdana", 16));
        backButton.setOnAction(e -> showMenu());
        root.getChildren().add(backButton);

        stage.setWidth(1600);
        stage.setHeight(900);
        stage.setResizable(false);
        Platform.runLater(() ->{
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void showLvl1win() {
        Pane root = new Pane();
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT, Color.BLACK);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        Text winText = new Text("NICE JOB!");
        winText.setFill(Color.WHITE);
        winText.setFont(new Font("Verdana",26));
        winText.setX(750);
        winText.setY(250);
        root.getChildren().add(winText);

        Text packetLossText = new Text(String.format("PACKET LOSS: %.2f",
                (double)Operator.getINSTANCE().lostPacket/Operator.getINSTANCE().totalPacket*100) + "%");
        packetLossText.setFill(Color.WHITE);
        packetLossText.setFont(new Font("Verdana",26));
        packetLossText.setX(650);
        packetLossText.setY(350);
        root.getChildren().add(packetLossText);

        Button playAgainButton = new Button("PLAY LEVEL 2");
        playAgainButton.setPrefWidth(200);
        playAgainButton.setPrefHeight(50);
        playAgainButton.setLayoutX(700);
        playAgainButton.setLayoutY(450);
        playAgainButton.setFont(new Font("Verdana", 16));
        playAgainButton.setOnAction(e -> runLvl2());
        root.getChildren().add(playAgainButton);

        Button backButton = new Button("BACK TO MENU");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(50);
        backButton.setLayoutX(700);
        backButton.setLayoutY(550);
        backButton.setFont(new Font("Verdana", 16));
        backButton.setOnAction(e -> showMenu());
        root.getChildren().add(backButton);

        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        Platform.runLater(() ->{
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void showLvl2fail() {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1600, 900, Color.BLACK);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        Text loseText = new Text("YOU LOST");
        loseText.setFill(Color.WHITE);
        loseText.setFont(new Font("Verdana",26));
        loseText.setX(750);
        loseText.setY(250);
        root.getChildren().add(loseText);

        Text packetLossText = new Text(String.format("PACKET LOSS: %.2f",
                (double)Operator.getINSTANCE().lostPacket/Operator.getINSTANCE().totalPacket*100) + "%");
        packetLossText.setFill(Color.WHITE);
        packetLossText.setFont(new Font("Verdana",26));
        packetLossText.setX(650);
        packetLossText.setY(350);
        root.getChildren().add(packetLossText);

        Button playAgainButton = new Button("PLAY AGAIN");
        playAgainButton.setPrefWidth(200);
        playAgainButton.setPrefHeight(50);
        playAgainButton.setLayoutX(700);
        playAgainButton.setLayoutY(450);
        playAgainButton.setFont(new Font("Verdana", 16));
        playAgainButton.setOnAction(e -> runLvl2());
        root.getChildren().add(playAgainButton);

        Button backButton = new Button("BACK TO MENU");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(50);
        backButton.setLayoutX(700);
        backButton.setLayoutY(550);
        backButton.setFont(new Font("Verdana", 16));
        backButton.setOnAction(e -> showMenu());
        root.getChildren().add(backButton);

        stage.setWidth(1600);
        stage.setHeight(900);
        stage.setResizable(false);
        Platform.runLater(() ->{
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void showLvl2win() {
        Pane root = new Pane();
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT, Color.BLACK);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        Text winText = new Text("CONGRATS!");
        winText.setFill(Color.WHITE);
        winText.setFont(new Font("Verdana",26));
        winText.setX(750);
        winText.setY(250);
        root.getChildren().add(winText);

        Text packetLossText = new Text(String.format("PACKET LOSS: %.2f",
                (double)Operator.getINSTANCE().lostPacket/Operator.getINSTANCE().totalPacket*100) + "%");
        packetLossText.setFill(Color.WHITE);
        packetLossText.setFont(new Font("Verdana",26));
        packetLossText.setX(650);
        packetLossText.setY(350);
        root.getChildren().add(packetLossText);

        Button backButton = new Button("BACK TO MENU");
        backButton.setPrefWidth(200);
        backButton.setPrefHeight(50);
        backButton.setLayoutX(700);
        backButton.setLayoutY(450);
        backButton.setFont(new Font("Verdana", 16));
        backButton.setOnAction(e -> showMenu());
        root.getChildren().add(backButton);

        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        Platform.runLater(() ->{
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void showMenu() {
        Pane root = new Pane();
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT, Color.BLACK);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        Button startButton = new Button("START GAME");
        startButton.setPrefWidth(200);
        startButton.setPrefHeight(50);
        startButton.setLayoutX(700);
        startButton.setLayoutY(250);
        startButton.setFont(new Font("Verdana", 16));
        startButton.setOnAction(e -> runLvl1());
        root.getChildren().add(startButton);


        Button exitButton = new Button("EXIT");
        exitButton.setPrefWidth(200);
        exitButton.setPrefHeight(50);
        exitButton.setLayoutX(700);
        exitButton.setLayoutY(330);
        exitButton.setFont(new Font("Verdana", 16));
        exitButton.setOnAction(e -> Platform.exit());
        root.getChildren().add(exitButton);

        stage.setWidth(STAGE_WIDTH);
        stage.setHeight(STAGE_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void run() {
        showMenu();
    }
}
