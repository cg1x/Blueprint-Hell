package game.controller;

import game.model.*;
import game.view.SystemViewManager;

public class Level2 {
    private GameState gameState;
    private SystemViewManager systemViewManager;

    public Level2(GameState gameState, SystemViewManager systemViewManager) {
        this.gameState = gameState;
        this.systemViewManager = systemViewManager;
    }

    public void createLevel() {
        StartSystem generalSystem3 = new StartSystem(200, 390);
        SquarePort inputPort1 = new SquarePort(generalSystem3, PortType.INPUT);
        TrianglePort inputPort2 = new TrianglePort(generalSystem3, PortType.INPUT);
        SquarePort outputPort1 = new SquarePort(generalSystem3, PortType.OUTPUT);
        TrianglePort outputPort2 = new TrianglePort(generalSystem3, PortType.OUTPUT);

        SystemModel generalSystem = new SystemModel(600, 600);
        SquarePort inputPort3 = new SquarePort(generalSystem, PortType.INPUT);
        TrianglePort inputPort4 = new TrianglePort(generalSystem, PortType.INPUT);
        SquarePort outputPort3 = new SquarePort(generalSystem, PortType.OUTPUT);
        TrianglePort outputPort4 = new TrianglePort(generalSystem, PortType.OUTPUT);

        SystemModel generalSystem2 = new SystemModel(1000, 600);
        SquarePort inputPort5 = new SquarePort(generalSystem2, PortType.INPUT);
        TrianglePort inputPort6 = new TrianglePort(generalSystem2, PortType.INPUT);
        SquarePort outputPort5 = new SquarePort(generalSystem2, PortType.OUTPUT);
        TrianglePort outputPort6 = new TrianglePort(generalSystem2, PortType.OUTPUT);

        SystemModel generalSystem4 = new SystemModel(1300, 200);
        SquarePort inputPort7 = new SquarePort(generalSystem4, PortType.INPUT);
        TrianglePort inputPort8 = new TrianglePort(generalSystem4, PortType.INPUT);
        SquarePort outputPort7 = new SquarePort(generalSystem4, PortType.OUTPUT);
        TrianglePort outputPort8 = new TrianglePort(generalSystem4, PortType.OUTPUT);

        gameState.addSystem(generalSystem3);
        systemViewManager.addSystem(generalSystem3);
        gameState.addSystem(generalSystem);
        systemViewManager.addSystem(generalSystem);
        gameState.addSystem(generalSystem2);
        systemViewManager.addSystem(generalSystem2);
        gameState.addSystem(generalSystem4);
        systemViewManager.addSystem(generalSystem4);
    }
}
