package game.model.packets;

import game.model.Wire;

import java.util.ArrayList;
import static game.controller.Constants.*;

public class SquarePacket extends Packet {

    public SquarePacket() {
        super();
        initialHealth = 2;
        rewardValue = 1;
        health = initialHealth;
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
