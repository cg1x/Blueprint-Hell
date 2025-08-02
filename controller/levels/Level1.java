package game.controller.levels;

import game.model.*;
import game.model.ports.PortType;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.model.systems.StartSystem;
import game.model.systems.SystemModel;
import game.view.manager.PortViewManager;
import game.view.manager.SystemViewManager;

public class Level1 {
    private GameState gameState;
    private SystemViewManager systemViewManager;
    private PortViewManager portViewManager;

    public Level1(GameState gameState, SystemViewManager systemViewManager, PortViewManager portViewManager) {
        this.gameState = gameState;
        this.systemViewManager = systemViewManager;
        this.portViewManager = portViewManager;
    }

    public void createLevel() {
        StartSystem generalSystem3 = new StartSystem(200, 390);
        portViewManager.addPort(new SquarePort(generalSystem3, PortType.OUTPUT));
        portViewManager.addPort(new SquarePort(generalSystem3, PortType.OUTPUT));
        portViewManager.addPort(new SquarePort(generalSystem3, PortType.INPUT));

        SystemModel generalSystem = new SystemModel(600, 500);
        portViewManager.addPort(new SquarePort(generalSystem, PortType.INPUT));
        portViewManager.addPort(new SquarePort(generalSystem, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(generalSystem, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(generalSystem, PortType.OUTPUT));

        SystemModel generalSystem2 = new SystemModel(1200, 390);
        portViewManager.addPort(new TrianglePort(generalSystem2, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(generalSystem2, PortType.INPUT));
        portViewManager.addPort(new SquarePort(generalSystem2, PortType.OUTPUT));

        gameState.addSystem(generalSystem3);
        systemViewManager.addSystem(generalSystem3);
        gameState.addSystem(generalSystem);
        systemViewManager.addSystem(generalSystem);
        gameState.addSystem(generalSystem2);
        systemViewManager.addSystem(generalSystem2);
    }
}
