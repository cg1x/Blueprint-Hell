package game.model;

import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;

public final class GameStats {
    public int coins;
    public double wireLength;
    public int successfulPackets;
    public int inNetworkPackets;
    public int lostPackets;
    public int totalPackets;
    public double packetLoss;
    private static GameStats INSTANCE;

    public GameStats() {
        coins = 10;
        successfulPackets = 0;
        lostPackets = 0;
        totalPackets = 0;
        inNetworkPackets = 0;
        packetLoss = 0.0;
    }

    public int getCoins() { return coins; }
    public void setCoins(int coins) { this.coins = coins; }
    public void addCoins(int coins) { this.coins += coins; }
    public void decrementCoins(int coins) { this.coins -= coins; }
    
    public int getSuccessfulPackets() { return successfulPackets; }
    public void setSuccessfulPackets(int successfulPackets) { this.successfulPackets = successfulPackets; }
    public void incrementSuccessfulPackets() { this.successfulPackets++; }
    
    public int getInNetworkPackets() { return inNetworkPackets; }
    public void setInNetworkPackets(int inNetworkPackets) { this.inNetworkPackets = inNetworkPackets; }
    public void decrementInNetworkPackets() { this.inNetworkPackets--; }
    
    public int getLostPackets() { return lostPackets; }
    public void setLostPackets(int lostPackets) { this.lostPackets = lostPackets; }
    public void incrementLostPackets() { this.lostPackets++; }
    
    public int getTotalPackets() { return totalPackets; }
    public void setTotalPackets(int totalPackets) { 
        this.totalPackets = totalPackets;
        this.inNetworkPackets = totalPackets;
    }
    
    public double getPacketLoss() { return packetLoss; }
    
    public void calculatePacketLoss() {
        if (totalPackets > 0) {
            this.packetLoss = ((double) lostPackets / totalPackets) * 100;
        }
    }

    public void packetReached(TrianglePacket packet) {
        coins += 2;
    }

    public void packetReached(SquarePacket packet) {
        coins++;
    }

    public static GameStats getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GameStats();
        }
        return INSTANCE;
    }

    public void reset() {
        INSTANCE = new GameStats();
    }
}
