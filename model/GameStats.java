package game.model;

import game.view.HUD;

public final class GameStats {
    public int coins;
    public double wireLength;
    public int successfulPacket;
    public int inNetworkPacket;
    public int lostPacket;
    public int totalPacket;
    public double packetLoss;
    public HUD hud;
    private static GameStats INSTANCE;

    public GameStats() {
        coins = 0;
        successfulPacket = 0;
        lostPacket = 0;
        totalPacket = 0;
        inNetworkPacket = totalPacket;
        packetLoss = 0.0;
    }

    public void setTotalPacket(int n) {
        totalPacket = n;
        inNetworkPacket = totalPacket;
    }

    public void setSuccessfulPacket(TrianglePacket packet) {
        successfulPacket++;
        inNetworkPacket--;
        coins += 2;
    }

    public void setSuccessfulPacket(SquarePacket packet) {
        successfulPacket++;
        inNetworkPacket--;
        coins++;
    }

    public void packetReached(TrianglePacket packet) {
        coins += 2;
    }

    public void packetReached(SquarePacket packet) {
        coins++;
    }

    public void setLostPacket(TrianglePacket packet) {
        lostPacket++;
        inNetworkPacket--;
    }

    public void setLostPacket(SquarePacket packet) {
        lostPacket++;
        inNetworkPacket--;
    }

    public static GameStats getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GameStats();
        }
        return INSTANCE;
    }

    public void update() {
        packetLoss = ((double) (lostPacket / totalPacket)) * 100;
    }

    public double getPacketLoss() {
        return packetLoss;
    }

    public void reset() {
        INSTANCE = new GameStats();
    }
}
