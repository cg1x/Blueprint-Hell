package game.view;

import game.controller.GameController;
import game.model.*;
import game.view.manager.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static game.controller.Constants.*;
import static game.controller.Constants.STAGE_HEIGHT;
import static game.controller.Constants.STAGE_WIDTH;

public class GameView {
    private GameController gameController;
    private final Stage stage;
    private final ViewUtil viewUtil;
    private PacketViewManager packetViewManager;
    private SystemViewManager systemViewManager;
    private WireViewManager wireViewManager;
    private PortViewManager portViewManager;
    private ViewManager viewManager;
    private HUD hud;

    public GameView(Stage stage) {
        this.stage = stage;
        viewUtil = new ViewUtil();
        packetViewManager = new PacketViewManager();
        systemViewManager = new SystemViewManager();
        wireViewManager = new WireViewManager();
        portViewManager = new PortViewManager();
        viewManager = new ViewManager(packetViewManager, wireViewManager, portViewManager);
    }

    public void showMenu() {
        Pane root = new Pane();
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT, Color.BLACK);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        Button startButton = viewUtil.createButton("START GAME", 700, 250, root);
        startButton.setOnAction(e -> gameController.startLevel(1));

        Button exitButton = viewUtil.createButton("EXIT", 700, 350, root);
        exitButton.setOnAction(e -> Platform.exit());

        setupStage(scene);
    }

    public void showLevelComplete(int level, GameStats gameStats) {
        Pane root = new Pane();
        Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT, Color.BLACK);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        Text winText = viewUtil.createText("NICE JOB!", 750, 250, root);

        String packetLossStr = String.format("PACKET LOSS: %.2f",
                (double) gameStats.getLostPackets()/gameStats.getTotalPackets()*100) + "%";
        Text packetLossText = viewUtil.createText(packetLossStr, 650, 350, root);

        Button playNextLevelButton = viewUtil.createButton("PLAY LEVEL " + (level+1), 700, 450, root);
        playNextLevelButton.setOnAction(e -> gameController.startLevel(level + 1));

        Button backButton = viewUtil.createButton("BACK TO MENU", 700, 550, root);
        backButton.setOnAction(e -> showMenu());

        setupStage(scene);
    }

    public void showLevelFailed(int level, GameStats gameStats) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1600, 900, Color.BLACK);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        Text loseText = viewUtil.createText("YOU LOST", 750, 250, root);

        String packetLossStr = String.format("PACKET LOSS: %.2f",
                (double) gameStats.getLostPackets()/gameStats.getTotalPackets()*100) + "%";
        Text packetLossText = viewUtil.createText(packetLossStr, 650, 350, root);

        Button playAgainButton = viewUtil.createButton("PLAY AGAIN", 700, 450, root);
        playAgainButton.setOnAction(e -> gameController.startLevel(level));

        Button backButton = viewUtil.createButton("BACK TO MENU", 700, 550, root);
        backButton.setOnAction(e -> showMenu());

        setupStage(scene);
    }

    public void initializeLevel(GameState gameState) {
        Root.reset();
        Scene scene = new Scene(Root.getINSTANCE(), STAGE_WIDTH, STAGE_HEIGHT, Color.BLACK);
        Root.getINSTANCE().setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        gameController.getGameService().getSystemService().paintAllSystems(gameController);

        hud = new HUD(gameController.getGameService().getGameState().getGameStats());

        

        setupStage(scene);
    }

    public void updateView() {
        packetViewManager.updateAll();
        hud.update();
    }

    private void setupStage(Scene scene) {
        stage.setWidth(1600);
        stage.setHeight(900);
        stage.setResizable(false);
        Platform.runLater(() ->{
            stage.setScene(scene);
            stage.show();
        });
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void resetView() {
        packetViewManager.clear();
        wireViewManager.clear();
        portViewManager.clear();
        systemViewManager.clear();
    }
    
    public HUD getHud() { return hud; }
    public ViewManager getViewManager() { return viewManager; }
    public PacketViewManager getPacketViewManager() { return packetViewManager; }
    public SystemViewManager getSystemViewManager() { return systemViewManager; }
    public WireViewManager getWireViewManager() { return wireViewManager; }
    public PortViewManager getPortViewManager() { return portViewManager; }
}
