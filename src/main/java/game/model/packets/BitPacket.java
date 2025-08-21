package game.model.packets;

import static game.controller.Constants.PORT_SIZE;

public class BitPacket extends Packet {
    public BitPacket() {
        super();
        initialHealth = 1;
        rewardValue = 1;
        acceleration = 0.05;
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
