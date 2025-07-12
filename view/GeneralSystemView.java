package game.view;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.lang.reflect.GenericSignatureFormatError;
import java.util.concurrent.atomic.AtomicReference;

import static game.controller.Constants.SYSTEM_TOP_COLOR;

public abstract class GeneralSystemView {

    public abstract void turnOnIndicator();

    public abstract void turnOffIndicator();

}
