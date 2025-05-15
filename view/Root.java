package game.view;

import javafx.scene.layout.Pane;

public final class Root extends Pane {
    private static Root INSTANCE;

    public static Root getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Root();
        }
        return INSTANCE;
    }
}
