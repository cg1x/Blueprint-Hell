package game.service.system;

import game.model.systems.Transferor;
import game.model.systems.Server;
import game.model.packets.Packet;
import game.model.ports.Port;
import game.model.GameState;
import game.model.systems.GeneralSystem;
import game.controller.GameController;

import java.util.List;

import game.service.PortService;
import game.view.systems.ServerView;
import game.view.systems.GeneralSystemView;
import game.view.manager.SystemViewManager;

public class SystemService {
    private PortService portService;
    private SystemViewManager systemViewManager;
    private GameState gameState;
    private Server server;
    private PortFinder portFinder;

    public SystemService(SystemViewManager systemViewManager, GameState gameState) {
        this.systemViewManager = systemViewManager;
        this.gameState = gameState;
        this.portFinder = new PortFinder();
    }

    public void paintAllSystems(GameController gameController) {
        List<GeneralSystem> systems = getAllSystems();
        for (GeneralSystem system : systems) {
            if (system instanceof Server && systems.indexOf(system) == 0) {
                this.server = (Server) system;
                ((ServerView) systemViewManager.getView(system)).setGameController(gameController);
            }
            systemViewManager.getView(system).paint();
            portService.paintAllPorts(system);
        }
    }

    public void startSendingPackets(Server system) {
        for (Port port : system.getOutputPorts()) {
            sendNewPacketTo(port);
        }
    }

    public void updateSystem(GeneralSystem system) {
        updateSystemIndicator(system);
        updateStartButton();
    }

    public void updateSystemIndicator(GeneralSystem system) {
        GeneralSystemView view = systemViewManager.getView(system);
        for (Port port : system.getInputPorts()) {
            if (port.isAvailable()) {
                view.turnOffIndicator();
                system.setReady(false);
                return;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port.isAvailable()) {
                view.turnOffIndicator();
                system.setReady(false);
                return;
            }
        }
        view.turnOnIndicator();
        system.setReady(true);
    }

    public void updateStartButton() {
        ServerView view2 = (ServerView) systemViewManager.getView(server);
        view2.updateButton();
    }

    public void sendNewPacketTo(Port port) {
        GeneralSystem system = port.getSystem();
        if (system.canSendPacket()) {
            Packet packet = system.getPendingPackets().removeFirst();
            portService.assignPacketToPort(packet, port);
        } else {
            port.getWire().setPacket(null);
        }
    }

    public void decideForPacket(Transferor system, Packet packet) {
        Port availablePort = portFinder.findAvailablePort(system, packet);
        if (availablePort != null) {
            portService.assignPacketToPort(packet, availablePort);
        } else {
            system.getPendingPackets().add(packet);
            packet.setOnWire(false);
        }
    }

    public void setPortService(PortService portService) {
        this.portService = portService;
    }

    public List<GeneralSystem> getAllSystems() {
        return gameState.getSystems();
    }

    public boolean AreSystemsReady() {
        for (GeneralSystem system : gameState.getSystems()) {
            if (!system.isReady()) {
                return false;
            }
        }
        return true;
    }
}
