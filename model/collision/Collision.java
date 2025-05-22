package game.model.collision;

public class Collision {
    public double x;
    public double y;
    public Collidable collidable1;
    public Collidable collidable2;

    public Collision(Collidable collidable1, Collidable collidable2) {
        this.collidable1 = collidable1;
        collidable1.addCollidable(collidable2);
        this.collidable2 = collidable2;
        collidable2.addCollidable(collidable1);
        this.x = (collidable1.getCenterX() + collidable2.getCenterX()) / 2;
        this.y = (collidable1.getCenterY() + collidable2.getCenterY()) / 2;
    }
}
