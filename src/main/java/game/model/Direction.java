package game.model;

import game.model.packets.Packet;

public class Direction {
    boolean isUpward = false;
    boolean isDownward = false;
    double slope;
    double x;
    double y;
    DirectionState state;

    public Direction(Wire wire) {
        double x = wire.getControlX(1) - wire.getControlX(0);
        double y = wire.getControlY(1) - wire.getControlY(0);

        if (x == 0 && y > 0) {
            isUpward = true;
        } else if (x == 0 && y < 0) {
            isDownward = true;
        } else if (x == 0) {
            state = DirectionState.NEUTRAL;
        } else {
            slope = y / x;
            if (x > 0) {
                state = DirectionState.POSITIVE;
            } else {
                state = DirectionState.NEGATIVE;
            }
        }
        getDirectionVector();
    }

    public Direction(Packet packet, Collision collision) {
        double x = packet.getCenterX() - collision.getX();
        double y = packet.getCenterY() - collision.getY();

        if (x == 0 && y > 0) {
            isUpward = true;
        } else if (x == 0 && y < 0) {
            isDownward = true;
        } else if (x == 0) {
            state = DirectionState.NEUTRAL;
        } else {
            slope = y / x;
            if (x > 0) {
                state = DirectionState.POSITIVE;
            } else {
                state = DirectionState.NEGATIVE;
            }
        }
        getDirectionVector();
    }

    public void getDirectionVector() {
        if (state == DirectionState.NEUTRAL) {
            x = 0;
            y = 0;
        }
        if (isUpward) {
            x = 0;
            y = 1;
        }
        if (isDownward) {
            x = 0;
            y = -1;
        }
        double magnitude = Math.sqrt(1 + slope * slope);
        x = 1 / magnitude;
        y = slope / magnitude;
        if (state == DirectionState.NEGATIVE) {
            x = -x;
            y = -y;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public enum DirectionState{
        NEGATIVE,
        POSITIVE,
        NEUTRAL
    }
}
