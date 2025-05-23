package game.view;

import game.model.Operator;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HUD {
    public Text coins;
    public Text successfulPacket;
    public Text inNetworkPacket;
    public Text lostPacket;
    public Text packetLoss;
    public Operator operator;

    public HUD(Operator operator) {
        this.operator = operator;
        coins = new Text("coins: " + operator.coins);
        coins.setFill(Color.WHITE);
        coins.setFont(new Font("Verdana",18));
        coins.setX(10);
        coins.setY(30);

        successfulPacket = new Text("successfulPackets: " + operator.successfulPacket);
        successfulPacket.setFill(Color.WHITE);
        successfulPacket.setFont(new Font("Verdana",18));
        successfulPacket.setX(10);
        successfulPacket.setY(60);

        inNetworkPacket = new Text("inNetworkPackets: " + operator.inNetworkPacket);
        inNetworkPacket.setFill(Color.WHITE);
        inNetworkPacket.setFont(new Font("Verdana",18));
        inNetworkPacket.setX(10);
        inNetworkPacket.setY(90);

        lostPacket = new Text("lostPackets: " + operator.lostPacket);
        lostPacket.setFill(Color.WHITE);
        lostPacket.setFont(new Font("Verdana",18));
        lostPacket.setX(10);
        lostPacket.setY(120);

        packetLoss = new Text("packetLoss: " + operator.packetLoss);
        packetLoss.setFill(Color.WHITE);
        packetLoss.setFont(new Font("Verdana",18));
        packetLoss.setX(10);
        packetLoss.setY(150);

        Root.getINSTANCE().getChildren().addAll(coins, successfulPacket, inNetworkPacket, lostPacket);
    }

    public void update() {
        coins.setText("coins: " + operator.coins);
        successfulPacket.setText("successfulPackets: " + operator.successfulPacket);
        inNetworkPacket.setText("inNetworkPackets: " + operator.inNetworkPacket);
        lostPacket.setText("lostPackets: " + operator.lostPacket);
    }
}
