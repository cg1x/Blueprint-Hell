package game.view;

import game.controller.GameController;
import game.model.*;
import game.view.manager.*;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

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
    private Scene mainScene;
    private PacketViewManager packetViewManager;
    private SystemViewManager systemViewManager;
    private WireViewManager wireViewManager;
    private PortViewManager portViewManager;
    private ViewManager viewManager;
    private Button shopButton;
    private HUD hud;
    private Shop shop;

    public GameView(Stage stage) {
        this.stage = stage;
        viewUtil = new ViewUtil();
        packetViewManager = new PacketViewManager();
        systemViewManager = new SystemViewManager();
        wireViewManager = new WireViewManager();
        portViewManager = new PortViewManager();
        viewManager = new ViewManager(packetViewManager, wireViewManager, portViewManager, systemViewManager);
    }

    public void showShop() {
        setupStage(shop.showShop(gameController.getGameService().getGameState().getGameStats().getCoins()));
        gameController.pauseGame();
    }

    public void showMenu() {
        Button startButton = viewUtil.createButton("START GAME");
        startButton.setOnAction(e -> gameController.startLevel(1));

        Button exitButton = viewUtil.createButton("EXIT");
        exitButton.setOnAction(e -> Platform.exit());

        VBox vbox = new VBox(50, startButton, exitButton);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(vbox);
        root.setPrefSize(1600, 900);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        setupStage(new Scene(root));
    }

    public void showLevelComplete(int level, GameStats gameStats) {
        Label winText = viewUtil.createLabel("NICE JOB!");

        String packetLossStr = String.format("PACKET LOSS: %.2f",
                (double) gameStats.getLostPackets()/gameStats.getTotalPackets()*100) + "%";
        Label packetLossText = viewUtil.createLabel(packetLossStr);

        Button playNextLevelButton = viewUtil.createButton("PLAY LEVEL ");
        playNextLevelButton.setOnAction(e -> gameController.startLevel(level + 1));

        Button backButton = viewUtil.createButton("BACK TO MENU");
        backButton.setOnAction(e -> showMenu());

        VBox vbox = new VBox(50, winText, packetLossText, playNextLevelButton, backButton);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(vbox);
        root.setPrefSize(1600, 900);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        setupStage(new Scene(root));
    }

    public void showLevelFailed(int level, GameStats gameStats) {
        Label loseText = viewUtil.createLabel("YOU LOST");

        String packetLossStr = String.format("PACKET LOSS: %.2f",
                (double) gameStats.getLostPackets()/gameStats.getTotalPackets()*100) + "%";
        Label packetLossText = viewUtil.createLabel(packetLossStr);

        Button playAgainButton = viewUtil.createButton("PLAY AGAIN");
        playAgainButton.setOnAction(e -> gameController.startLevel(level));

        Button backButton = viewUtil.createButton("BACK TO MENU");
        backButton.setOnAction(e -> showMenu());

        VBox vbox = new VBox(50, loseText, packetLossText, playAgainButton, backButton);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(vbox);
        root.setPrefSize(1600, 900);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        setupStage(new Scene(root));
    }

    public void initializeLevel(GameState gameState) {
        Root.reset();
        mainScene = new Scene(Root.getINSTANCE(), STAGE_WIDTH, STAGE_HEIGHT, Color.BLACK);
        Root.getINSTANCE().setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        shopButton = viewUtil.createButton("Shop");
        shopButton.setOnAction(e -> showShop());
        VBox vbox = new VBox(shopButton);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setPrefSize(STAGE_WIDTH, STAGE_HEIGHT);

        gameController.getGameService().getSystemService().paintAllSystems(gameController);

        hud = new HUD(gameController.getGameService().getGameState().getGameStats());
        shop = new Shop(gameController.getGameService().getAbilityService(), this);

        Root.getINSTANCE().getChildren().addFirst(vbox);

        setupStage(mainScene);
    }

    public void resumeGame() {
        setupStage(mainScene);
        gameController.resumeGame();
    }

    public void updateView() {
        packetViewManager.updateAll();
        hud.update();
    }

    private void setupStage(Scene scene) {
        stage.setWidth(1600);
        stage.setHeight(900);
        stage.setResizable(false);
        Platform.runLater(() -> {
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
