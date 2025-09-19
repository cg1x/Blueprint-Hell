package game.controller;

import game.service.AbilityService;
import game.service.system.SystemService;
import javafx.application.Platform;
import game.service.CollisionService;
import game.service.GameService;
import game.service.PacketService;

public class UpdateController {
    public volatile boolean running = false;
    public volatile boolean paused = false;
    public Thread animator;
    public GameService gameService;
    public GameController gameController;
    private PacketService packetService;
    private CollisionService collisionService;
    private SystemService systemService;
    private AbilityService abilityService;

    public UpdateController(GameService gameService, GameController gameController) {
        this.gameService = gameService;
        this.packetService = gameService.getPacketService();
        this.collisionService = gameService.getCollisionService();
        this.systemService = gameService.getSystemService();
        this.abilityService = gameService.getAbilityService();
        this.gameController = gameController;
    }

    private void initializeGameLoop() {
        animator = new Thread(() -> {
            try {
                while (running) {
                    Thread.sleep(16);
                    if (!paused) {
                        updateModel();
                        Platform.runLater(() -> updateView());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        animator.setDaemon(true);
    }

    public void start() {
        initializeGameLoop();
        running = true;
        animator.start();
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void stop() {
        running = false;
    }

    public void updateModel() {
        systemService.updateInactiveSystems();
        packetService.movePackets(gameController.getGameService().getGameState());
        collisionService.detectCollisions(gameController.getGameService().getGameState());
        abilityService.updateAbilities();
        if (gameService.isGameOver()) {
            stop();
            gameService.getGameState().getGameStats().calculatePacketLoss();
            if (gameService.isLevelComplete()) {
                gameController.levelFailed();
            } else {
                gameController.levelComplete();
            }
        }
    }

    public void updateView() {
        gameController.updateView();
    }
}
