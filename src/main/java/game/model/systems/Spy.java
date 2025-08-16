package game.model.systems;

public class Spy extends GeneralSystem {
    public Spy(double x, double y) {
        super(x, y);
        ready = false;
    }

    public boolean canSendPacket() {
        return !pendingPackets.isEmpty();
    }

    public boolean canAcceptPacket() {
        return pendingPackets.size() < 5;
    }
}
