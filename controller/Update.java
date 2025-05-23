package game.controller;


import game.model.Operator;
import game.model.SquarePacket;
import game.model.StartSystem;
import game.model.TrianglePacket;
import game.model.collision.Collision;
import game.view.GameUI;
import javafx.application.Platform;

import static game.model.SquarePacket.squarePackets;
import static game.model.TrianglePacket.trianglePackets;
import static game.model.collision.Collidable.collidables;

public class Update {

    public boolean first = true;
    public boolean running = true;

    public Update() {
        Thread animator = new Thread(() -> {
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
        animator.start();
    }

    public void stop() {
        running = false;
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
        if (Operator.getINSTANCE().inNetworkPacket == 0) {
            stop();
            Operator.getINSTANCE().update();
            if (GameUI.level == 1) {
                if (Operator.getINSTANCE().getPacketLoss() >= 50) {
                    GameUI.showLvl1fail();
                } else {
                    GameUI.showLvl1win();
                }
            }
            if (GameUI.level == 2) {
                if (Operator.getINSTANCE().getPacketLoss() >= 50) {
                    GameUI.showLvl2fail();
                } else {
                    GameUI.showLvl2win();
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
