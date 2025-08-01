package game.controller;

import game.model.*;
import game.view.PortViewManager;
import game.view.SystemViewManager;

public class Level2 {
    private GameState gameState;
    private SystemViewManager systemViewManager;
    private PortViewManager portViewManager;

    public Level2(GameState gameState, SystemViewManager systemViewManager, PortViewManager portViewManager) {
        this.gameState = gameState;
        this.systemViewManager = systemViewManager;
        this.portViewManager = portViewManager;
    }

    public void createLevel() {
        StartSystem generalSystem3 = new StartSystem(200, 390);
        portViewManager.addPort(new SquarePort(generalSystem3, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(generalSystem3, PortType.INPUT));
        portViewManager.addPort(new SquarePort(generalSystem3, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(generalSystem3, PortType.OUTPUT));

        SystemModel generalSystem = new SystemModel(600, 600);
        portViewManager.addPort(new SquarePort(generalSystem, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(generalSystem, PortType.INPUT));
        portViewManager.addPort(new SquarePort(generalSystem, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(generalSystem, PortType.OUTPUT));

        SystemModel generalSystem2 = new SystemModel(1000, 600);
        portViewManager.addPort(new SquarePort(generalSystem2, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(generalSystem2, PortType.INPUT));
        portViewManager.addPort(new SquarePort(generalSystem2, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(generalSystem2, PortType.OUTPUT));

        SystemModel generalSystem4 = new SystemModel(1300, 200);
        portViewManager.addPort(new SquarePort(generalSystem4, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(generalSystem4, PortType.INPUT));
        portViewManager.addPort(new SquarePort(generalSystem4, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(generalSystem4, PortType.OUTPUT));

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
