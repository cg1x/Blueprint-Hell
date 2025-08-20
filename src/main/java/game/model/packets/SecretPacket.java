package game.model.packets;

import game.model.Wire;

public class SecretPacket extends Packet {
    private final int initialHealth = 4;
    private final int rewardValue = 3;

    @Override
    public double getCenterX() {
        return 0;
    }

    @Override
    public double getCenterY() {
        return 0;
    }
}
