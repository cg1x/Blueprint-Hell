package game.view.packets;

import javafx.scene.shape.Shape;

public abstract class PacketView {
    public abstract Shape getShape();
    public abstract void update();
    public abstract void remove();
}
