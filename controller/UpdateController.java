package game.controller;


import game.model.GameStats;
import game.model.SquarePacket;
import game.model.TrianglePacket;
import game.model.collision.Collision;
import javafx.application.Platform;
import game.service.MovementService;
import game.model.GameState;

import static game.model.SquarePacket.squarePackets;
import static game.model.TrianglePacket.trianglePackets;
import static game.model.collision.Collidable.collidables;

public class UpdateController {

    public boolean first = true;
    public boolean running = false;
    public Thread animator;
    public GameStats gameStats;
    public GameController gameController;
    private MovementService movementService = new MovementService();

    public UpdateController(GameStats gameStats, GameController gameController) {
        this.gameStats = gameStats;
        this.gameController = gameController;
        initializeGameLoop();
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
        animator.start();
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void updateModel() {
        if (first) {
            gameStats.setTotalPacket(gameController.getGameService().getGameState().getTrianglePackets().size() +
                                    gameController.getGameService().getGameState().getSquarePackets().size());
            first = false;
        }
        movementService.updatePackets(gameController.getGameService().getGameState(), gameController);
        if (gameStats.inNetworkPacket == 0) {
            stop();
            gameStats.update();
            if (gameStats.getPacketLoss() >= 50) {
                gameController.levelFailed();
            } else {
                gameController.levelComplete();
            }
        }
    }

    public void updateView() {
        for (int i = 0; i < trianglePackets.size(); i++) {
            trianglePackets.get(i).getPacketView().update();
        }
        for (int i = 0; i < squarePackets.size(); i++) {
            squarePackets.get(i).getPacketView().update();
        }
        gameController.updateHud();
    }
}
