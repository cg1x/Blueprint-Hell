package game.view;

import game.model.Operator;
import javafx.scene.layout.Pane;

public final class Root extends Pane {
    private static Root INSTANCE;

    public static Root getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Root();
        }
        return INSTANCE;
    }

    public static void reset() {
        INSTANCE = new Root();
    }
}
