package game.controller;

import game.model.*;
import game.view.SystemViewManager;

public class Level1 {
    private GameState gameState;
    private SystemViewManager systemViewManager;

    public Level1(GameState gameState, SystemViewManager systemViewManager) {
        this.gameState = gameState;
        this.systemViewManager = systemViewManager;
    }

    public void createLevel() {
        StartSystem generalSystem3 = new StartSystem(200, 390);
        generalSystem3.setGameState(gameState);
        SquarePort inputPort1 = new SquarePort(generalSystem3, PortType.INPUT);
        SquarePort outputPort1 = new SquarePort(generalSystem3, PortType.OUTPUT);
        SquarePort outputPort2 = new SquarePort(generalSystem3, PortType.OUTPUT);

        SystemModel generalSystem = new SystemModel(600, 500);
        SquarePort inputPort2 = new SquarePort(generalSystem, PortType.INPUT);
        SquarePort inputPort3 = new SquarePort(generalSystem, PortType.INPUT);
        TrianglePort outputPort3 = new TrianglePort(generalSystem, PortType.OUTPUT);
        TrianglePort outputPort4 = new TrianglePort(generalSystem, PortType.OUTPUT);

        SystemModel generalSystem2 = new SystemModel(1200, 390);
        TrianglePort inputPort4 = new TrianglePort(generalSystem2, PortType.INPUT);
        TrianglePort inputPort5 = new TrianglePort(generalSystem2, PortType.INPUT);
        SquarePort outputPort5 = new SquarePort(generalSystem2, PortType.OUTPUT);

        gameState.addSystem(generalSystem3);
        systemViewManager.addSystem(generalSystem3);
        gameState.addSystem(generalSystem);
        systemViewManager.addSystem(generalSystem);
        gameState.addSystem(generalSystem2);
        systemViewManager.addSystem(generalSystem2);
    }
}
