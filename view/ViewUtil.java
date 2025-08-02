package game.view;

import game.model.systems.GeneralSystem;
import javafx.scene.Group;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static game.controller.Constants.*;

public class ViewUtil {
    public Text createText(String str, double x, double y, Pane root) {
        Text text = new Text(str);
        text.setFill(Color.WHITE);
        text.setFont(new Font("Verdana",26));
        text.setX(x);
        text.setY(y);
        root.getChildren().add(text);
        return text;
    }

    public Button createButton(String text, double x, double y, Pane root) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setFont(new Font("Verdana", 16));
        root.getChildren().add(button);
        return button;
    }

    public void paintSystem(double x, double y, GeneralSystem system) {
        Group shape = new Group();
        Rectangle mainRectangle = new Rectangle(x, y, SYSTEM_SIZE, SYSTEM_SIZE);
        mainRectangle.setFill(SYSTEM_COLOR);
        mainRectangle.setStroke(SYSTEM_COLOR);
        Rectangle topRectangle = new Rectangle(x, y, SYSTEM_SIZE, SYSTEM_TOP_HEIGHT);
        topRectangle.setFill(SYSTEM_TOP_COLOR);
        topRectangle.setStroke(SYSTEM_COLOR);
        shape.getChildren().addAll(mainRectangle, topRectangle);
        Root.getINSTANCE().getChildren().addAll(shape);
    }
}
