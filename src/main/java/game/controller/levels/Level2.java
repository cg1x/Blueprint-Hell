package game.controller.levels;

import game.model.*;
import game.model.ports.PortType;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.model.systems.Server;
import game.model.systems.Transferor;
import game.view.manager.PortViewManager;
import game.view.manager.SystemViewManager;

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
        Server server = new Server(200, 390);
        portViewManager.addPort(new SquarePort(server, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(server, PortType.INPUT));
        portViewManager.addPort(new SquarePort(server, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(server, PortType.OUTPUT));

        Transferor transferor1 = new Transferor(600, 600);
        portViewManager.addPort(new SquarePort(transferor1, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(transferor1, PortType.INPUT));
        portViewManager.addPort(new SquarePort(transferor1, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(transferor1, PortType.OUTPUT));

        Transferor transferor2 = new Transferor(1000, 600);
        portViewManager.addPort(new SquarePort(transferor2, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(transferor2, PortType.INPUT));
        portViewManager.addPort(new SquarePort(transferor2, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(transferor2, PortType.OUTPUT));

        Transferor transferor3 = new Transferor(1300, 200);
        portViewManager.addPort(new SquarePort(transferor3, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(transferor3, PortType.INPUT));
        portViewManager.addPort(new SquarePort(transferor3, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(transferor3, PortType.OUTPUT));

        gameState.addSystem(server);
        systemViewManager.addSystem(server);
        gameState.addSystem(transferor1);
        systemViewManager.addSystem(transferor1);
        gameState.addSystem(transferor2);
        systemViewManager.addSystem(transferor2);
        gameState.addSystem(transferor3);
        systemViewManager.addSystem(transferor3);
    }
}
