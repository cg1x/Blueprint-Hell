package game.model.collision;

import game.view.PacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public interface Collidable {
    ArrayList<Collidable> collidables = new ArrayList<>();

    boolean isCollidingWith(Collidable collidable);
    void addCollidable(Collidable collidable);
    void removeCollidable(Collidable collidable);
    double getCenterX();
    double getCenterY();
    PacketView getPacketView();

    default Collision collides(Collidable collidable) {
        if (!isCollidingWith(collidable)) {
            Shape shape = Shape.intersect(this.getPacketView().getShape(), collidable.getPacketView().getShape());
            if (shape.getBoundsInLocal().getWidth() != -1) {
                return new Collision(this, collidable);
            }
        } else {
            Shape shape = Shape.intersect(this.getPacketView().getShape(), collidable.getPacketView().getShape());
            if (shape.getBoundsInLocal().getWidth() == -1) {
                this.removeCollidable(collidable);
                collidable.removeCollidable(this);
            }
        }
        return null;
    }
}
