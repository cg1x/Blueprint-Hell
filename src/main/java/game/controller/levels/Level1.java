package game.controller.levels;

import game.model.*;
import game.model.ports.PortType;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.model.systems.Ddos;
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
        portViewManager.addPort(new TrianglePort(server, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(server, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(server, PortType.INPUT));

        Ddos ddos = new Ddos(600, 390);
        portViewManager.addPort(new SquarePort(ddos, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(ddos, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(ddos, PortType.OUTPUT));
        portViewManager.addPort(new SquarePort(ddos, PortType.OUTPUT));

        Transferor transferor2 = new Transferor(1200, 500);
        portViewManager.addPort(new SquarePort(transferor2, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(transferor2, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(transferor2, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(transferor2, PortType.OUTPUT));

//        Server server2 = new Server(1200, 390);
//        portViewManager.addPort(new TrianglePort(server2, PortType.INPUT));
//        portViewManager.addPort(new TrianglePort(server2, PortType.INPUT));

        gameState.addSystem(server);
        systemViewManager.addSystem(server);
        gameState.addSystem(ddos);
        systemViewManager.addSystem(ddos);
        gameState.addSystem(transferor2);
        systemViewManager.addSystem(transferor2);
    }
}
