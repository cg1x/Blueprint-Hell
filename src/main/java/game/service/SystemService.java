package game.service;

import game.model.systems.SystemModel;
import game.model.systems.StartSystem;
import game.model.packets.Packet;
import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;
import game.model.ports.Port;
import game.model.ports.TrianglePort;
import game.model.GameState;
import game.model.ports.SquarePort;
import game.model.systems.GeneralSystem;
import game.controller.GameController;

import static game.controller.Constants.PORT_SIZE;

import java.util.List;

import game.view.systems.StartSystemView;
import game.view.systems.GeneralSystemView;
import game.view.manager.SystemViewManager;

public class SystemService {
    private PortService portService;
    private SystemViewManager systemViewManager;
    private GameState gameState;
    private StartSystem startSystem;

    public SystemService(SystemViewManager systemViewManager, GameState gameState) {
        this.systemViewManager = systemViewManager;
        this.gameState = gameState;
    }

    public void paintAllSystems(GameController gameController) {
        for (GeneralSystem system : systemViewManager.getSystemMap().keySet()) {
            if (system instanceof StartSystem) {
                this.startSystem = (StartSystem) system;
                ((StartSystemView) systemViewManager.getView(system)).setGameController(gameController);
            }
            systemViewManager.getView(system).paint();
            portService.paintAllPorts(system);
        }
    }

    public void startSendingPackets(StartSystem system) {
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
        StartSystemView view2 = (StartSystemView) systemViewManager.getView(startSystem);
        view2.updateButton();
    }

    public Port findAvailablePort(GeneralSystem system, Packet packet) {
        if (packet instanceof SquarePacket) {
            return findAvailablePort(system, (SquarePacket) packet);
        } else if (packet instanceof TrianglePacket) {
            return findAvailablePort(system, (TrianglePacket) packet);
        }
        return null;
    }

    public Port findAvailablePort(GeneralSystem system, SquarePacket packet) {
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        return null;
    }

    public Port findAvailablePort(GeneralSystem system, TrianglePacket packet) {
        for (Port port : system.getOutputPorts()) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        return null;
    }

    public void assignPacketToPort(Packet packet, Port port) {
        if (packet instanceof SquarePacket) {
            packet.setX(port.getCenterX() - PORT_SIZE/2 + packet.getDeflectionX());
            packet.setY(port.getCenterY() - PORT_SIZE/2 + packet.getDeflectionY());
            if (port instanceof SquarePort) {
                packet.setSpeed(2);
            }
            if (port instanceof TrianglePort) {
                packet.setSpeed(4);
            }
        } else if (packet instanceof TrianglePacket) {
            packet.setX(port.getCenterX() + packet.getDeflectionX());
            packet.setY(port.getCenterY() - PORT_SIZE/2 + packet.getDeflectionY());
            if (port instanceof SquarePort) {
                packet.setSpeed(1);
            }
            if (port instanceof TrianglePort) {
                packet.setSpeed(2);
            }
        }
        packet.setT(0);
        packet.setWire(port.getWire());
        packet.getWire().setPacket(packet);
        packet.setOnWire(true);
    }

    public void sendNewPacketTo(Port port) {
        GeneralSystem system = port.getSystem();
        if (system.canSendPacket()) {
            Packet packet = system.getPendingPackets().removeFirst();
            assignPacketToPort(packet, port);
        } else {
            port.getWire().setPacket(null);
        }
    }

    public void decideForPacket(SystemModel system, Packet packet) {
        Port availablePort = findAvailablePort(system, packet);
        if (availablePort != null) {
            assignPacketToPort(packet, availablePort);
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
