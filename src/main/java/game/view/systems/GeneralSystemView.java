package game.view.systems;

import javafx.scene.shape.Shape;

public abstract class GeneralSystemView {
    public abstract void paint();

    public abstract void turnOnIndicator();

    public abstract void turnOffIndicator();

    public abstract Shape getShape();
}
