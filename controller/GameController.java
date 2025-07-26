package game.controller;

import game.model.GameState;
import game.model.Packet;
import game.service.GameService;
import game.view.GameView;
import game.view.PacketViewManager;
import game.view.SystemViewManager;

public class GameController {
    private GameService gameService;
    private GameView gameView;
    private UpdateController updateController;
    private PacketViewManager packetViewManager;
    private SystemViewManager systemViewManager;

    public GameController(GameService gameService, GameView gameView) {
        this.gameService = gameService;
        this.gameView = gameView;
        gameView.setGameController(this);
        this.packetViewManager = new PacketViewManager();
        this.systemViewManager = new SystemViewManager();
        this.updateController = new UpdateController(gameService.getGameState().getGameStats(), this);
    }

    public void startLevel(int level) {
        gameService.initializeLevel(level);
        gameView.initializeLevel(gameService.getGameState());
    }

    public void levelComplete() {
        GameState gameState = gameService.getGameState();
        gameView.showLevelComplete(gameState.getCurrentLevel(), gameState.getGameStats());
    }

    public void levelFailed() {
        GameState gameState = gameService.getGameState();
        gameView.showLevelFailed(gameState.getCurrentLevel(), gameState.getGameStats());
    }

    public void updateHud() {
        gameView.getHud().update();
    }

    public void startGame() {
        updateController.start();
    }

    public void generatePackets() {
        int level = gameService.getGameState().getCurrentLevel();
        gameService.getPacketService().createPacketsForLevel(level, gameService.getGameState(), packetViewManager);
    }

    public void removePacket(Packet packet) {
        gameService.getPacketService().removePacket(packet, packetViewManager);
    }

    public GameService getGameService() { return gameService; }

    public PacketViewManager getPacketViewManager() { return packetViewManager; }

    public SystemViewManager getSystemViewManager() { return systemViewManager; }
}
