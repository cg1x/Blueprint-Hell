package game.model.packets;

import game.model.Wire;

import java.util.ArrayList;

import static game.controller.Constants.PORT_SIZE;

public class TrianglePacket extends Packet {
    private final int initialHealth = 3;
    public final double acceleration = 0.05;
    private final int rewardValue = 2;

    public TrianglePacket() {
        super();
        health = initialHealth;
    }

    public void incrementSpeed() {
        speed += acceleration;
    }

    @Override
    public int getRewardValue() {
        return rewardValue;
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    public double getAcceleration() {
        return acceleration;
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
