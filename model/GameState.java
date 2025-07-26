package game.model;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private int currentLevel;
    private List<GeneralSystem> systems;
    private List<SquarePacket> squarePackets;
    private List<TrianglePacket> trianglePackets;
    private GameStats gameStats;
    private boolean gameRunning;

    public GameState() {
        this.currentLevel = 1;
        this.systems = new ArrayList<>();
        this.gameStats = new GameStats();
        this.gameRunning = false;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public List<GeneralSystem> getSystems() {
        return systems;
    }

    public void addSystem(GeneralSystem system) {
        systems.add(system);
    }

    public void addSquarePacket(SquarePacket packet) {
        squarePackets.add(packet);
    }

    public void addTrianglePacket(TrianglePacket packet) {
        trianglePackets.add(packet);
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public void reset() {
        systems.clear();
        gameStats = new GameStats();
        gameRunning = false;
    }

    public List<SquarePacket> getSquarePackets() {
        return squarePackets;
    }

    public List<TrianglePacket> getTrianglePackets() {
        return trianglePackets;
    }
}
