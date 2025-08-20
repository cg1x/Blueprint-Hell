package game.model.packets;

import game.model.Wire;

import java.util.ArrayList;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePacket extends Packet {
    public TrianglePacket() {
        super();
        initialHealth = 3;
        rewardValue = 2;
        acceleration = 0.05;
        health = initialHealth;
    }

    @Override
    public double getCenterX() {
        return x;
    }

    @Override
    public double getCenterY() {
        return y + PORT_SIZE/2;
    }
}
