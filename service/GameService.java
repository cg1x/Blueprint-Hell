package game.service;

import game.controller.Level1;
import game.controller.Level2;
import game.model.GameState;

public class GameService {
    private GameState gameState;

    public GameService() {
        this.gameState = new GameState();
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
}
