package game.service;

import game.controller.GameController;
import game.controller.levels.Level1;
import game.controller.levels.Level2;
import game.model.GameState;
import game.model.systems.StartSystem;
import game.view.manager.PortViewManager;
import game.view.manager.SystemViewManager;

public class GameService {
    private GameController gameController;
    private GameState gameState;
    private CollisionService collisionService;
    private PacketService packetService;
    private SystemService systemService;
    private PortService portService;
    private WireService wireService;
    private MovementService movementService;

    public GameService(GameController gameController) {
        this.gameController = gameController;
        this.gameState = new GameState();
        this.systemService = new SystemService(gameController.getGameView().getSystemViewManager(), gameState);
        this.wireService = new WireService(systemService, gameController.getGameView().getWireViewManager(), 
                                            gameController.getGameView().getViewManager(), gameState);
        this.movementService = new MovementService(wireService);
        this.packetService = new PacketService(gameState, systemService, movementService, gameController.getGameView().getViewManager());
        this.collisionService = new CollisionService(packetService, gameController.getGameView().getViewManager());
        this.portService = new PortService(gameController.getGameView().getPortViewManager(), wireService);
        systemService.setPortService(portService);
    }

    public void initializeLevel(int level, SystemViewManager systemViewManager, PortViewManager portViewManager) {
        gameState.reset();
        gameState.setCurrentLevel(level);
        switch (level) {
            case 1:
                new Level1(gameState, systemViewManager, portViewManager).createLevel();
                break;
            case 2:
                new Level2(gameState, systemViewManager, portViewManager).createLevel();
                break;
            default:
                break;
        }
    }

    public void startGame() {
        gameState.setGameRunning(true);
        systemService.startSendingPackets((StartSystem) gameState.getSystems().getFirst());
        wireService.disableControlComponents();
    }

    public boolean canStartGame() {
        return systemService.AreSystemsReady() && wireService.AreWiresReady();
    }

    public boolean isGameOver() {
        return gameState.getGameStats().getInNetworkPackets() == 0;
    }

    public boolean isLevelComplete() {
        return gameState.getGameStats().getPacketLoss() >= 50;
    }

    public GameState getGameState() { return gameState; }
    public PacketService getPacketService() { return packetService; }
    public SystemService getSystemService() { return systemService; }
    public CollisionService getCollisionService() { return collisionService; }
}
