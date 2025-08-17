package game.view.systems;

import game.model.systems.Ddos;
import game.view.Root;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static game.controller.Constants.SYSTEM_SIZE;

public class DdosView extends GeneralSystemView<Ddos> {

    public DdosView(Ddos system) {
        super(system);
    }

    @Override
    public void paint() {
        super.paint();
        createLabel();
    }

    private void createLabel() {
        Text label = new Text("DDOS");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        label.setFill(Color.WHITE);
        label.setX(x + SYSTEM_SIZE - label.getLayoutBounds().getWidth() - 5);
        label.setY(y + label.getLayoutBounds().getHeight() + 4);

        shape.getChildren().add(label);
        Root.getINSTANCE().getChildren().add(label);
    }
}
