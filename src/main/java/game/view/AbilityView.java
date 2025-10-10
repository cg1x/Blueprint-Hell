package game.view;

import game.model.AbilityType;
import game.view.wire.WireView;
import javafx.scene.Node;
import javafx.scene.Scene;

public class AbilityView {
    public void handleAbility(Scene gameScene, AbilityType abilityType) {
        switch (abilityType) {
            case SCROLL_OF_AERGIA -> handleAergia(gameScene);
            case SCROLL_OF_SISYPHUS -> handleSisyphus(gameScene);
            case SCROLL_OF_ELIPHAS -> handleEliphas(gameScene);
        }
    }

    private void handleAergia(Scene gameScene) {
        Root.getINSTANCE().setOnMouseClicked(e -> {
            Node node = e.getPickResult().getIntersectedNode();
            if (node.getUserData() instanceof WireView) {
                
            } else {
                handleAergia(gameScene);
            }
        });
    }

    private void handleSisyphus(Scene gameScene) {

    }

    private void handleEliphas(Scene gameScene) {

    }
}
