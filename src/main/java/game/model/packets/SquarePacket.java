package game.model.packets;

import game.model.Wire;

import java.util.ArrayList;
import static game.controller.Constants.*;

public class SquarePacket extends Packet {
    private final int initialHealth = 2;
    private final int rewardValue = 1;

    public SquarePacket() {
        super();
        health = initialHealth;
    }

    @Override
    public int getRewardValue() {
        return rewardValue;
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public double getCenterX() {
        return x + PORT_SIZE/2;
    }

    @Override
    public double getCenterY() {
        return y + PORT_SIZE/2;
    }
}
