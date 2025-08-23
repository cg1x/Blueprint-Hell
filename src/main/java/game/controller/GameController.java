package game.controller;

import game.model.GameState;
import game.service.GameService;
import game.view.GameView;

public class GameController {
    private GameService gameService;
    private GameView gameView;
    private UpdateController updateController;

    public GameController(GameView gameView) {
        this.gameView = gameView;
        this.gameService = new GameService(this);
        this.updateController = new UpdateController(gameService, this);
    }

    public void startLevel(int level) {
        gameView.resetView();
        gameService.initializeLevel(level, gameView.getSystemViewManager(), gameView.getPortViewManager());
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

    public void updateView() {
        gameView.updateView();
    }

    public void updateHUD() {
        gameView.getHud().update();
    }

    public void startGame() {
        gameService.startGame();
        updateController.start();
    }

    public void generatePackets() {
        int level = gameService.getGameState().getCurrentLevel();
        gameService.getPacketService().createPacketsForLevel(level);
    }

    public GameView getGameView() { return gameView; }
    public GameService getGameService() { return gameService; }

}
