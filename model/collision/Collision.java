package game.model.collision;

import game.controller.Utils;
import game.model.Packet;
import game.model.movement.Direction;

import static game.controller.Constants.PORT_SIZE;
import static game.model.collision.Collidable.collidables;

public class Collision {
    public double x;
    public double y;
    public Collidable collidable1;
    public Collidable collidable2;
    public final double MAGNITUDE = PORT_SIZE / 2;
    public final double impactRadius = 1600;

    public Collision(Collidable collidable1, Collidable collidable2) {
        this.collidable1 = collidable1;
        collidable1.addCollidable(collidable2);
        this.collidable2 = collidable2;
        collidable2.addCollidable(collidable1);
        this.x = (collidable1.getCenterX() + collidable2.getCenterX()) / 2;
        this.y = (collidable1.getCenterY() + collidable2.getCenterY()) / 2;
        System.out.println("just hit");
    }

    public void applyImpact() {
        for (Collidable collidable : collidables) {
            if (!(collidable == collidable1 || collidable == collidable2)) {
                applyImpact(collidable);
            }
        }
    }

    public void applyImpact(Collidable collidable) {
        Packet packet = collidable.getPacket();
        Direction direction = new Direction(packet, this);
        double distance = Utils.measureDistance(packet.getX(), packet.getY(), x, y);
        packet.setDeflectionX(direction.getX() * MAGNITUDE * (1 - distance/impactRadius));
        packet.setDeflectionY(direction.getY() * MAGNITUDE * (1 - distance/impactRadius));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
