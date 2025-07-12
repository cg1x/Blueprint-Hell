package game.controller;


import game.model.GameStats;
import game.model.SquarePacket;
import game.model.TrianglePacket;
import game.model.collision.Collision;
import javafx.application.Platform;

import static game.model.SquarePacket.squarePackets;
import static game.model.TrianglePacket.trianglePackets;
import static game.model.collision.Collidable.collidables;

public class UpdateController {

    public boolean first = true;
    public boolean running = true;
    public Thread animator;
    public GameStats gameStats;
    public GameController gameController;

    public UpdateController(GameStats gameStats, GameController gameController) {
        this.gameStats = gameStats;
        this.gameController = gameController;
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
    }

    public void stop() {
        running = false;
    }

    public void updateModel() {
        if (first) {
            gameStats.setTotalPacket(trianglePackets.size() + squarePackets.size());
            first = false;
        }
        for (int i = 0; i < trianglePackets.size(); i++) {
            TrianglePacket packet = trianglePackets.get(i);
            if (packet.getWire().getEndPort().getSystem().getPendingPackets().contains(packet)) {
                continue;
            }
            packet.move();
            if (packet.reachedEndPort()) {
                packet.getWire().getNewPacket();
                packet.getWire().getEndPort().getSystem().decideForPacket(packet);
            }
        }
        for (int i = 0; i < squarePackets.size(); i++) {
            SquarePacket packet = squarePackets.get(i);
            if (packet.getWire().getEndPort().getSystem().getPendingPackets().contains(packet)) {
                continue;
            }
            packet.move();
            if (packet.reachedEndPort()) {
                packet.getWire().getNewPacket();
                packet.getWire().getEndPort().getSystem().decideForPacket(packet);

            }
        }
        for (int i = 0; i < collidables.size(); i++) {
            for (int j = i + 1; j < collidables.size(); j++) {
                Collision collision = collidables.get(i).collides(collidables.get(j));
                if (collision != null) {
                    collision.applyImpact();
                }
            }
        }
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
        gameStats.hud.update();
    }
}
