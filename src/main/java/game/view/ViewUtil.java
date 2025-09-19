package game.view;

import game.model.systems.GeneralSystem;
import javafx.scene.Group;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static game.controller.Constants.*;

public class ViewUtil {
    public Label createLabel(String str) {
        Label label = new Label(str);
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Verdana",26));
        return label;
    }

    public Button createButton(String text) {
        Button button = new Button(text);

        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setFont(new Font("Verdana", 16));
        styleButton(button);

        return button;
    }

    public Button createButton(String text, double x, double y, Pane root) {
        Button button = new Button(text);

        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setFont(new Font("Verdana", 16));

        button.setLayoutX(x);
        button.setLayoutY(y);

        styleButton(button);

        root.getChildren().add(button);
        return button;
    }

    private void styleButton(Button button) {
        button.setStyle(
                "-fx-background-color: linear-gradient(to right, #ff7e5f, #feb47b);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: #ff7e5f;" +
                        "-fx-border-width: 2;"
        );

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.4));
        button.setEffect(shadow);

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to right, #feb47b, #ff7e5f);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: #ff7e5f;" +
                        "-fx-border-width: 2;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: linear-gradient(to right, #ff7e5f, #feb47b);" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-radius: 15;" +
                        "-fx-border-color: #ff7e5f;" +
                        "-fx-border-width: 2;"
        ));
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
