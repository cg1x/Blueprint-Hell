package game.controller;


import game.model.Operator;
import game.model.SquarePacket;
import game.model.StartSystem;
import game.model.TrianglePacket;
import game.model.collision.Collision;
import javafx.application.Platform;

import static game.model.SquarePacket.squarePackets;
import static game.model.TrianglePacket.trianglePackets;
import static game.model.collision.Collidable.collidables;

public class Update {

    public boolean first = true;

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
        if (first) {
            Operator.getINSTANCE().setTotalPacket(trianglePackets.size() + squarePackets.size());
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
    }

    public void updateView() {
        for (int i = 0; i < trianglePackets.size(); i++) {
            trianglePackets.get(i).getPacketView().update();
        }
        for (int i = 0; i < squarePackets.size(); i++) {
            squarePackets.get(i).getPacketView().update();
        }
        Operator.getINSTANCE().hud.update();
    }
}
