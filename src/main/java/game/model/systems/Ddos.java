package game.model.systems;

public class Ddos extends GeneralSystem {
    public Ddos(double x, double y) {
        super(x, y);
    }


    public boolean canSendPacket() {
        return !pendingPackets.isEmpty();
    }

    public boolean canAcceptPacket() {
        return pendingPackets.size() < 5;
    }
}
