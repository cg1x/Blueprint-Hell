package game.service;

import game.model.SystemModel;
import game.model.StartSystem;
import game.model.Packet;
import game.model.SquarePacket;
import game.model.TrianglePacket;
import game.model.Port;
import game.model.TrianglePort;
import game.model.SquarePort;
import game.model.GameStats;
import game.model.GeneralSystem;
import game.controller.GameController;
import static game.model.collision.Collidable.collidables;
import game.view.SystemView;
import game.view.StartSystemView;
import game.view.GeneralSystemView;
import game.view.SystemViewManager;
import java.util.Map;

public class SystemService {
    private PortService portService;
    private SystemViewManager systemViewManager;
    private GameController gameController;

    public SystemService(PortService portService) {
        this.portService = portService;
    }

    public void paintAllSystems(GameController gameController) {
        for (GeneralSystem system : systemViewManager.getSystemMap().keySet()) {
            if (system instanceof StartSystem) {
                ((StartSystemView) systemViewManager.getView(system)).setGameController(gameController);
            }
            systemViewManager.getView(system).paint();
            portService.paintAllPorts(system);
        }
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
        StartSystemView view2 = (StartSystemView) systemViewManager.getView(StartSystem.getINSTANCE());
        view2.updateButton();
    }

    public void decideForPacket(SystemModel system, TrianglePacket packet, GameController gameController) {
        GameStats.getINSTANCE().packetReached(packet);
        collidables.remove(packet);
        for (Port port : system.getOutputPorts()) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        if (system.getPendingPackets().size() == 5) {
            gameController.removePacket(packet);
        } else {
            system.getPendingPackets().add(packet);
        }
    }

    public void decideForPacket(SystemModel system, SquarePacket packet, GameController gameController) {
        GameStats.getINSTANCE().packetReached(packet);
        collidables.remove(packet);
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        if (system.getPendingPackets().size() == 5) {
            gameController.removePacket(packet);
        } else {
            system.getPendingPackets().add(packet);
        }
    }

    public void decideForPacket(StartSystem system, TrianglePacket packet, GameController gameController) {
        if (!packet.isFirst()) {
            gameController.removePacket(packet);
            return;
        }
        collidables.remove(packet);
        for (Port port : system.getOutputPorts()) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        system.getPendingPackets().add(packet);
    }

    public void decideForPacket(StartSystem system, SquarePacket packet, GameController gameController) {
        if (!packet.isFirst()) {
            gameController.removePacket(packet);
            return;
        }
        collidables.remove(packet);
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        system.getPendingPackets().add(packet);
    }

    public void setSystemViewManager(SystemViewManager systemViewManager) {
        this.systemViewManager = systemViewManager;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
