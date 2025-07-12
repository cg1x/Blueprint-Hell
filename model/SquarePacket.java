package game.model;

import game.model.collision.Collidable;
import game.model.movement.Direction;
import game.model.movement.Movable;
import game.view.SquarePacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static game.controller.Constants.*;

public class SquarePacket extends Packet implements Movable, Collidable {
    public double x;
    public double y;
    public double deflectionX;
    public double deflectionY;
    public Wire wire;
    public Direction direction;
    public SquarePacketView packetView;
    public boolean first = true;
    public boolean onWire;
    public static ArrayList<SquarePacket> squarePackets = new ArrayList<>();
    public ArrayList<Collidable> collidingWith = new ArrayList<>();
    public int health = 2;
    public double speed;

    public SquarePacket() {
        deflectionX = 0;
        deflectionY = 0;
        Port port = StartSystem.getINSTANCE().getInputPorts().getFirst();
        x = port.getPortView().getCenterX() - PORT_SIZE/2;
        y = port.getPortView().getCenterY() - PORT_SIZE/2;
        wire = port.getWire();
        StartSystem.getINSTANCE().decideForPacket(this);
        packetView = new SquarePacketView(this);
        squarePackets.add(this);
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isOnWire() {
        return onWire;
    }

    public void setOnWire(boolean onWire) {
        this.onWire = onWire;
    }


    public boolean reachedEndPort() {
        Shape shape = Shape.intersect(this.packetView.getShape(), wire.getEndPort().getPortView());
        return shape.getBoundsInLocal().getWidth() != -1;
    }

    public void setSpeed() {
        if (wire.getWireType() == WireType.SQUARE) {
            this.speed = 2;
        }
        if (wire.getWireType() == WireType.TRIANGLE) {
            this.speed = 4;
        }
    }

    @Override
    public boolean deflected() {
        Shape shape = Shape.intersect(packetView.getShape(), wire.getWireView());
        return shape.getBoundsInLocal().getWidth() == -1;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void reduceHealth() {
        health--;
    }

    public void setPort(Port port) {
        x = port.getPortView().getCenterX() - PORT_SIZE/2 + deflectionX;
        y = port.getPortView().getCenterY() - PORT_SIZE/2 + deflectionY;
        wire = port.getWire();
        wire.setPacket(this);
        setSpeed();
        direction = new Direction(wire);
        collidables.add(this);
        first = false;
    }

    public Wire getWire() {
        return wire;
    }

    public void setDeflectionX(double deflectionX) {
        this.deflectionX += deflectionX;
        x += deflectionX;
        if (deflectionX > (PORT_SIZE + WIRE_WIDTH) / 2) {
            kill();
        }
    }

    public void setDeflectionY(double deflectionY) {
        this.deflectionY += deflectionY;
        y += deflectionY;
        if (deflectionY > (PORT_SIZE + WIRE_WIDTH) / 2) {
            kill();
        }
    }

    @Override
    public boolean isCollidingWith(Collidable collidable) {
        return collidingWith.contains(collidable);
    }

    @Override
    public void addCollidable(Collidable collidable) {
        collidingWith.add(collidable);
    }

    @Override
    public void removeCollidable(Collidable collidable) {
        collidingWith.remove(collidable);
    }

    @Override
    public SquarePacketView getPacketView() {
        return packetView;
    }

    public double getX() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void move(Direction direction, double speed) {
        this.x += direction.getX() * speed;
        this.y += direction.getY() * speed;
    }

    @Override
    public void move() {
        move(direction, speed);
    }

    @Override
    public double getCenterX() {
        return x + PORT_SIZE/2;
    }

    @Override
    public double getCenterY() {
        return y + PORT_SIZE/2;
    }

    @Override
    public SquarePacket getPacket() {
        return this;
    }

    @Override
    public void remove() {
        collidables.remove(this);
        squarePackets.remove(this);
        packetView.remove();
        GameStats.getINSTANCE().setSuccessfulPacket(this);
    }

    @Override
    public void kill() {
        wire.getNewPacket();
        collidables.remove(this);
        squarePackets.remove(this);
        packetView.remove();
        GameStats.getINSTANCE().setLostPacket(this);
    }
}
