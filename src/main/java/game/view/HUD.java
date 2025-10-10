package game.view;

import game.model.GameStats;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class HUD extends StackPane {
    private final GameStats gameStats;
    private final Label coinsLabel;
    private final Label successfulPacketLabel;
    private final Label inNetworkPacketLabel;
    private final Label lostPacketLabel;
    private final Label packetLossLabel;

    public HUD(GameStats gameStats) {
        this.gameStats = gameStats;

        // Create Labels
        coinsLabel = makeLabel("Coins: " + gameStats.coins);
        successfulPacketLabel = makeLabel("Successful: " + gameStats.getSuccessfulPackets());
        inNetworkPacketLabel = makeLabel("In-Network: " + gameStats.getInNetworkPackets());
        lostPacketLabel = makeLabel("Lost: " + gameStats.getLostPackets());
        packetLossLabel = makeLabel("Packet Loss: " + gameStats.getPacketLoss() + "%");

        VBox statsBox = new VBox(5, coinsLabel, successfulPacketLabel, inNetworkPacketLabel, lostPacketLabel, packetLossLabel);
        statsBox.setPadding(new Insets(10));

        this.getChildren().add(statsBox);
        this.setTranslateX(10);
        this.setTranslateY(10);

        Root.getINSTANCE().getChildren().add(this);
    }

    private Label makeLabel(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Verdana", 18));
        return label;
    }

    public void update() {
        coinsLabel.setText("Coins: " + gameStats.coins);
        successfulPacketLabel.setText("Successful: " + gameStats.getSuccessfulPackets());
        inNetworkPacketLabel.setText("In-Network: " + gameStats.getInNetworkPackets());
        lostPacketLabel.setText("Lost: " + gameStats.getLostPackets());
        packetLossLabel.setText("Packet Loss: " + gameStats.getPacketLoss() + "%");
    }
}
