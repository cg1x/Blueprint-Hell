package game.view;

import game.model.AbilityType;
import game.model.PurchaseResult;
import game.service.AbilityService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static game.controller.Constants.BACKGROUND_COLOR;

public class Shop {
    private Label messageLabel;
    private Label coinsLabel;
    private AbilityService abilityService;
    private GameView gameView;

    public Shop(AbilityService abilityService, GameView gameView) {
        this.abilityService = abilityService;
        this.gameView = gameView;
    }

    public Scene showShop(int coins) {
        Label title = new Label("SHOP");
        title.setFont(new Font("Verdana", 36));
        title.setTextFill(Color.WHITE);

        messageLabel = new Label("Choose an item to buy.");
        messageLabel.setFont(new Font("Verdana", 20));
        messageLabel.setTextFill(Color.WHITE);

        coinsLabel = new Label("coins: " + coins);
        coinsLabel.setFont(new Font("Verdana", 20));
        coinsLabel.setTextFill(Color.WHITE);

        Button atarButton = createShopButton("O' Atar (-3)");
        Button airyamanButton = createShopButton("O' Airyaman (-4)");
        Button anahitaButton = createShopButton("O' Anahita (-5)");
        Button backButton = createShopButton("Back to game");
        Button aergiaButton = createShopButton("Scroll of Aergia (-10)");
        Button sisyphusButton = createShopButton("Scroll of Sisyphus (-15)");
        Button eliphasButton = createShopButton("Scroll of Eliphas (-20)");

        atarButton.setOnAction(e -> handlePurchase(AbilityType.O_ATAR));
        airyamanButton.setOnAction(e -> handlePurchase(AbilityType.O_AIRYAMAN));
        anahitaButton.setOnAction(e -> handlePurchase(AbilityType.O_ANAHITA));
        backButton.setOnAction(e -> gameView.resumeGame());

        HBox hbox1 = new HBox(50, atarButton, airyamanButton);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(50, anahitaButton, aergiaButton);
        hbox2.setAlignment(Pos.CENTER);
        HBox hbox3 = new HBox(50, sisyphusButton, eliphasButton);
        hbox3.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(50, title, hbox1, hbox2, hbox3, messageLabel, coinsLabel);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(vbox, backButton);
        root.setPrefSize(1600, 900);
        root.setBackground(new Background(new BackgroundFill(
                BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY
        )));

        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(20));

        return new Scene(root);
    }

    public void handlePurchase(AbilityType abilityType) {
        PurchaseResult result = abilityService.purchaseAbility(abilityType);
        if (result.successful()) {
            messageLabel.setText(abilityType.toString().toLowerCase() + " is purchased successfully");
            coinsLabel.setText("coins: " + result.coins());
            switch (abilityType) {
                case SCROLL_OF_AERGIA, SCROLL_OF_SISYPHUS, SCROLL_OF_ELIPHAS -> {
                    //gameView.showGameScene();
                }
                default -> {
                }
            }

        } else {
            if (result.alreadyPurchased()) {
                messageLabel.setText("You already have " + abilityType.toString().toLowerCase());
            } else {
                messageLabel.setText("You don't have enough coin to buy " + abilityType.toString().toLowerCase());
            }
        }
    }

    private Button createShopButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(220);
        button.setPrefHeight(50);
        button.setFont(new Font("Verdana", 16));

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

        return button;
    }
}
