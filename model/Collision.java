package game.model;

import game.model.packets.Packet;

public class Collision {
    public double x;
    public double y;
    public Packet collidable1;
    public Packet collidable2;

    public Collision(Packet collidable1, Packet collidable2) {
        this.collidable1 = collidable1;
        collidable1.addCollidable(collidable2);
        this.collidable2 = collidable2;
        collidable2.addCollidable(collidable1);
        this.x = (collidable1.getCenterX() + collidable2.getCenterX()) / 2;
        this.y = (collidable1.getCenterY() + collidable2.getCenterY()) / 2;
        System.out.println("just hit");
    }

    public double getX() { return x; }
    public double getY() { return y; }
}
