package game.controller;

import javafx.application.Platform;
import game.service.CollisionService;
import game.service.GameService;
import game.service.PacketService;

public class UpdateController {
    public boolean running = false;
    public Thread animator;
    public GameService gameService;
    public GameController gameController;
    private PacketService packetService;
    private CollisionService collisionService;

    public UpdateController(GameService gameService, GameController gameController) {
        this.gameService = gameService;
        this.packetService = gameService.getPacketService();
        this.collisionService = gameService.getCollisionService();
        this.gameController = gameController;
    }

    private void initializeGameLoop() {
        animator = new Thread(() -> {
            try {
                while (running) {
                    Thread.sleep(16);
                    updateModel();
                    Platform.runLater(() -> updateView());
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

    public void stop() {
        running = false;
    }

    public void updateModel() {
        packetService.movePackets(gameController.getGameService().getGameState());
        collisionService.detectCollisions(gameController.getGameService().getGameState()); 
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
