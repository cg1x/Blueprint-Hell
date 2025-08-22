package game.controller.levels;

import game.model.*;
import game.model.ports.BitPort;
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
        Server server = new Server(100, 390);
        portViewManager.addPort(new SquarePort(server, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(server, PortType.OUTPUT));

        Transferor transferor1 = new Transferor(500, 200);
        portViewManager.addPort(new SquarePort(transferor1, PortType.INPUT));
        portViewManager.addPort(new BitPort(transferor1, PortType.INPUT));
        portViewManager.addPort(new SquarePort(transferor1, PortType.OUTPUT));

        Transferor transferor2 = new Transferor(800, 600);
        portViewManager.addPort(new TrianglePort(transferor2, PortType.INPUT));
        portViewManager.addPort(new BitPort(transferor2, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(transferor2, PortType.OUTPUT));

        Server server2 = new Server(1400, 390);
        portViewManager.addPort(new SquarePort(server2, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(server2, PortType.INPUT));

        gameState.addSystem(server);
        systemViewManager.addSystem(server);
        gameState.addSystem(transferor1);
        systemViewManager.addSystem(transferor1);
        gameState.addSystem(transferor2);
        systemViewManager.addSystem(transferor2);
        gameState.addSystem(server2);
        systemViewManager.addSystem(server2);
    }
}
