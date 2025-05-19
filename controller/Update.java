package game.controller;


import game.model.SquarePacket;
import game.model.TrianglePacket;
import javafx.application.Platform;

import static game.model.SquarePacket.squarePackets;
import static game.model.TrianglePacket.trianglePackets;

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
            packet.move();
            if (packet.reachedEndPort()) {
                packet.setPort(packet.getWire().getEndPort().getSystem().getOutputPorts().get(trianglePackets.indexOf(packet)));
            }

        }
    }

    public void updateView() {
        for (TrianglePacket packet : trianglePackets) {
            packet.getPacketView().update();
        }
    }
}
