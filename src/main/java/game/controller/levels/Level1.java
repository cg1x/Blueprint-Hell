package game.controller.levels;

import game.model.*;
import game.model.ports.PortType;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.model.systems.Server;
import game.model.systems.Spy;
import game.model.systems.Transferor;
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
        Server server = new Server(200, 390);
        portViewManager.addPort(new SquarePort(server, PortType.OUTPUT));
        portViewManager.addPort(new SquarePort(server, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(server, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(server, PortType.INPUT));

        Spy transferor1 = new Spy(600, 390);
        portViewManager.addPort(new SquarePort(transferor1, PortType.INPUT));
        portViewManager.addPort(new SquarePort(transferor1, PortType.INPUT));

        Spy transferor2 = new Spy(1200, 500);
        portViewManager.addPort(new TrianglePort(transferor2, PortType.OUTPUT));

        Spy transferor3 = new Spy(1200, 300);
        portViewManager.addPort(new TrianglePort(transferor3, PortType.OUTPUT));

//        Server server2 = new Server(1200, 390);
//        portViewManager.addPort(new TrianglePort(server2, PortType.INPUT));
//        portViewManager.addPort(new TrianglePort(server2, PortType.INPUT));

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
