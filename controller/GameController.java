package game.controller;

import game.model.GameState;
import game.service.GameService;
import game.view.GameView;

public class GameController {
    private GameService gameService;
    private GameView gameView;
    private UpdateController updateController;

    public GameController(GameService gameService, GameView gameView) {
        this.gameService = gameService;
        this.gameView = gameView;
        gameView.setGameController(this);
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

    public void startGame() {
        updateController.start();
    }
}
