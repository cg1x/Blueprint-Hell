package game.controller;


import game.model.GameStats;
import game.model.SquarePacket;
import game.model.TrianglePacket;
import game.model.collision.Collision;
import javafx.application.Platform;
import game.service.CollisionService;
import game.service.GameService;
import game.service.MovementService;
import game.service.PacketService;
import game.model.GameState;

import static game.model.SquarePacket.squarePackets;
import static game.model.TrianglePacket.trianglePackets;
import static game.model.collision.Collidable.collidables;

public class UpdateController {

    public boolean first = true;
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
        //collisionService.detectCollisions(gameController.getGameService().getGameState()); 
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
