package game.controller;


import game.model.SquarePacket;
import game.model.TrianglePacket;
import game.model.collision.Collision;
import javafx.application.Platform;

import static game.model.SquarePacket.squarePackets;
import static game.model.TrianglePacket.trianglePackets;
import static game.model.collision.Collidable.collidables;

public class Update {
    public Update() {
        Thread animator = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(16);
                    updateModel();
                    Platform.runLater(() -> updateView());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        animator.setDaemon(true);
        animator.start();
    }

    public void updateModel() {
        for (TrianglePacket packet : trianglePackets) {
            if (packet.getWire().getEndPort().getSystem().getPendingPackets().contains(packet)) {
                continue;
            }
            packet.move();
            if (packet.reachedEndPort()) {
                packet.getWire().getNewPacket();
                packet.getWire().getEndPort().getSystem().decideForPacket(packet);
            }
        }
        for (SquarePacket packet : squarePackets) {
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
    }

    public void updateView() {
        for (TrianglePacket packet : trianglePackets) {
            packet.getPacketView().update();
        }
        for (SquarePacket packet : squarePackets) {
            packet.getPacketView().update();
        }
    }
}
