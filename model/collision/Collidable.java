package game.model.collision;

import game.model.Packet;
import game.view.PacketView;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public interface Collidable {
    ArrayList<Collidable> collidables = new ArrayList<>();

    default Collision collides(Collidable collidable) {
        return null;
    }
}
