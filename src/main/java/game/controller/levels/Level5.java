package game.controller.levels;

import game.model.GameState;
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

public class Level5 {
    private GameState gameState;
    private SystemViewManager systemViewManager;
    private PortViewManager portViewManager;

    public Level5(GameState gameState, SystemViewManager systemViewManager, PortViewManager portViewManager) {
        this.gameState = gameState;
        this.systemViewManager = systemViewManager;
        this.portViewManager = portViewManager;
    }

    public void createLevel() {
        Server server = new Server(100, 390);
        portViewManager.addPort(new SquarePort(server, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(server, PortType.OUTPUT));

        Ddos ddos1 = new Ddos(400, 390);
        portViewManager.addPort(new TrianglePort(ddos1, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(ddos1, PortType.OUTPUT));
        portViewManager.addPort(new SquarePort(ddos1, PortType.OUTPUT));

        Spy spy1 = new Spy(450, 650);
        portViewManager.addPort(new SquarePort(spy1, PortType.INPUT));
        portViewManager.addPort(new SquarePort(spy1, PortType.INPUT));

        Transferor transferor1 = new Transferor(600, 200);
        portViewManager.addPort(new SquarePort(transferor1, PortType.INPUT));
        portViewManager.addPort(new BitPort(transferor1, PortType.INPUT));
        portViewManager.addPort(new SquarePort(transferor1, PortType.OUTPUT));
        portViewManager.addPort(new SquarePort(transferor1, PortType.OUTPUT));

        Spy spy3 = new Spy(700, 390);
        portViewManager.addPort(new BitPort(spy3, PortType.OUTPUT));

        Transferor transferor2 = new Transferor(900, 600);
        portViewManager.addPort(new BitPort(transferor2, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(transferor2, PortType.INPUT));
        portViewManager.addPort(new BitPort(transferor2, PortType.OUTPUT));
        portViewManager.addPort(new TrianglePort(transferor2, PortType.OUTPUT));

        Spy spy2 = new Spy(1000, 200);
        portViewManager.addPort(new BitPort(spy2, PortType.OUTPUT));

        Transferor transferor3 = new Transferor(1100, 390);
        portViewManager .addPort(new TrianglePort(transferor3, PortType.INPUT));
        portViewManager .addPort(new TrianglePort(transferor3, PortType.OUTPUT));

        Server server2 = new Server(1400, 390);
        portViewManager.addPort(new SquarePort(server2, PortType.INPUT));
        portViewManager.addPort(new TrianglePort(server2, PortType.INPUT));
        portViewManager.addPort(new BitPort(server2, PortType.INPUT));

        gameState.addSystem(server);
        systemViewManager.addSystem(server);
        gameState.addSystem(ddos1);
        systemViewManager.addSystem(ddos1);
        gameState.addSystem(spy1);
        systemViewManager.addSystem(spy1);
        gameState.addSystem(transferor1);
        systemViewManager.addSystem(transferor1);
        gameState.addSystem(spy3);
        systemViewManager.addSystem(spy3);
        gameState.addSystem(transferor2);
        systemViewManager.addSystem(transferor2);
        gameState.addSystem(spy2);
        systemViewManager.addSystem(spy2);
        gameState.addSystem(transferor3);
        systemViewManager.addSystem(transferor3);
        gameState.addSystem(server2);
        systemViewManager.addSystem(server2);
    }
}
