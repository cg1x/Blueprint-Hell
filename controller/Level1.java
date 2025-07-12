package game.controller;

import game.model.*;
import game.view.StartSystemView;
import game.view.SystemView;

public class Level1 {
    private GameState gameState;

    public Level1(GameState gameState) {
        this.gameState = gameState;
    }

    public void createLevel() {
        StartSystem generalSystem3 = new StartSystem(200, 390);
        generalSystem3.setGameState(gameState);
        new SquarePort(generalSystem3, PortType.INPUT);
        new SquarePort(generalSystem3, PortType.OUTPUT);
        new SquarePort(generalSystem3, PortType.OUTPUT);

        SystemModel generalSystem = new SystemModel(600, 500);
        new SquarePort(generalSystem, PortType.INPUT);
        new SquarePort(generalSystem, PortType.INPUT);
        new TrianglePort(generalSystem, PortType.OUTPUT);
        new TrianglePort(generalSystem, PortType.OUTPUT);

        SystemModel generalSystem2 = new SystemModel(1200, 390);
        new TrianglePort(generalSystem2, PortType.INPUT);
        new TrianglePort(generalSystem2, PortType.INPUT);
        new SquarePort(generalSystem2, PortType.OUTPUT);

        gameState.addSystem(generalSystem3);
        gameState.addSystem(generalSystem);
        gameState.addSystem(generalSystem2);
    }
}
