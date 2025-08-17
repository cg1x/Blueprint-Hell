package game.service.system;

import game.model.GameStats;
import game.model.systems.*;
import game.model.packets.Packet;
import game.model.ports.Port;
import game.model.GameState;
import game.controller.GameController;

import java.util.List;

import game.service.PacketService;
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
    private DdosService ddosService;
    private ServerService serverService;
    private SpyService spyService;
    private TransferorService transferorService;

    public SystemService(SystemViewManager systemViewManager, GameState gameState) {
        this.systemViewManager = systemViewManager;
        this.gameState = gameState;
        this.portFinder = new PortFinder();
        this.ddosService = new DdosService(gameState);
        this.serverService = new ServerService(gameState);
        this.spyService = new SpyService(gameState);
        this.transferorService = new TransferorService(gameState);
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
        serverService.startSendingPackets(system);
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
        switch (system) {
            case Server server1 -> serverService.sendNewPacketTo(port);
            case Transferor transferor -> transferorService.sendNewPacketTo(port);
            case Spy spy -> spyService.sendNewPacketTo(port);
            case Ddos ddos -> ddosService.sendNewPacketTo(port);
            default -> {
                return;
            }
        }
    }

    public void handlePacketReached(Packet packet, PacketService packetService) {
        GeneralSystem system = packet.getWire().getEndPort().getSystem();
        switch (system) {
            case Server server1 -> serverService.handlePacketReached(packet, packetService);
            case Transferor transferor -> transferorService.handlePacketReached(packet, packetService);
            case Spy spy -> spyService.handlePacketReached(packet, packetService);
            case Ddos ddos -> ddosService.handlePacketReached(packet, packetService);
            default -> {
                return;
            }
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
