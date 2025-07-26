package game.service;

import game.controller.Level1;
import game.controller.Level2;
import game.model.GameState;
import game.service.PacketService;
import game.service.MovementService;
import game.service.SystemService;

public class GameService {
    private GameState gameState;
    private PacketService packetService;
    private SystemService systemService;

    public GameService() {
        this.gameState = new GameState();
        this.packetService = new PacketService(new MovementService());
        this.systemService = new SystemService();
    }

    public void initializeLevel(int level) {
        gameState.reset();
        gameState.setCurrentLevel(level);
        switch (level) {
            case 1:
                new Level1(gameState).createLevel();
                break;
            case 2:
                new Level2(gameState).createLevel();
                break;
            default:
                break;
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public PacketService getPacketService() {
        return packetService;
    }

    public SystemService getSystemService() {
        return systemService;
    }
}
