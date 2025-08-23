package game.service;

import game.controller.GameController;
import game.controller.Utils;
import game.model.*;
import game.model.ports.Port;
import game.model.ports.PortType;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.model.systems.GeneralSystem;
import game.service.system.SystemService;
import game.view.wire.WireView;
import game.view.manager.ViewManager;
import game.view.manager.WireViewManager;

public class WireService {
    private SystemService systemService;
    private WireViewManager wireViewManager;
    private ViewManager viewManager;
    private GameState gameState;
    private GameController gameController;

    public WireService(SystemService systemService, WireViewManager wireViewManager,
                       ViewManager viewManager, GameState gameState, GameController gameController) {
        this.systemService = systemService;
        this.wireViewManager = wireViewManager;
        this.viewManager = viewManager;
        this.gameState = gameState;
        this.gameController = gameController;
    }

    public void handleConnection(Port startPort, Port endPort) {
        if (AreConnectable(startPort, endPort)) {
            createWire(startPort, endPort);
        }
    }

    public boolean AreConnectable(Port startPort, Port endPort) {
        boolean bool1 = startPort.getPortType() == PortType.OUTPUT && endPort.getPortType() == PortType.INPUT;
        boolean bool2 = startPort.getPortType() == PortType.INPUT && endPort.getPortType() == PortType.OUTPUT;
        boolean bool3 = startPort.getSystem() != endPort.getSystem();
        boolean bool4 = endPort.isAvailable();
        boolean bool5 = startPort.getClass().getName().equals(endPort.getClass().getName());

        return (bool1 || bool2) && bool3 && bool4 && bool5;
    }

    public void createWire(Port port1, Port port2) {
        Wire wire = null;
        if (port1 instanceof SquarePort) {
            if (port1.getPortType() == PortType.OUTPUT) {
                wire = new Wire(port1, port2, WireType.SQUARE);
            } else {
                wire = new Wire(port2, port1, WireType.SQUARE);
            }
        } else if (port1 instanceof TrianglePort){
            if (port1.getPortType() == PortType.OUTPUT) {
                wire = new Wire(port1, port2, WireType.TRIANGLE);
            } else {
                wire = new Wire(port2, port1, WireType.TRIANGLE);
            }
        } else {
            if (port1.getPortType() == PortType.OUTPUT) {
                wire = new Wire(port1, port2, WireType.BIT);
            } else {
                wire = new Wire(port2, port1, WireType.BIT);
            }
        }
        wire.setLength(calculateDistance(port1, port2));
        wireViewManager.addWire(wire);
        wireViewManager.getView(wire).enableEventHandling(this);
        gameState.addWire(wire);
        validateWire(wire);
        updatePortsAndSystems(port1, port2);
        wireViewManager.getView(wire).update();
    }

    public double calculateDistance(Port port1, Port port2) {
        double x1 = port1.getCenterX(), y1 = port1.getCenterY();
        double x2 = port2.getCenterX(), y2 = port2.getCenterY();
        return Utils.measureDistance(x2, y2, x1, y1);
    }

    public void validateWire(Wire wire) {
        for (GeneralSystem system : systemService.getAllSystems()) {
            if (viewManager.AreIntersecting(wire, system)) {
                wire.setValid(false);
                return;
            }
        }
        wire.setValid(true);
        systemService.updateStartButton();
    }

    public boolean canAddNewCurve() {
        GameStats gameStats = gameState.getGameStats();
        if (gameStats.getCoins() >= 1) {
            gameStats.decrementCoins(1);
            gameController.updateHUD();
            return true;
        }
        return false;
    }

    public double getPositionX(Wire wire, double t) {
        return switch (wire.getControlPointsCount()) {
            case 2 -> Utils.getXOnLine(wire, t);
            case 3 -> Utils.getXOnQuad(wire, t);
            case 4 -> Utils.getXOnCubic(wire, t);
            case 5 -> Utils.getXOnQuartic(wire, t);
            default -> 0;
        };
    }

    public double getPositionY(Wire wire, double t) {
        return switch (wire.getControlPointsCount()) {
            case 2 -> Utils.getYOnLine(wire, t);
            case 3 -> Utils.getYOnQuad(wire, t);
            case 4 -> Utils.getYOnCubic(wire, t);
            case 5 -> Utils.getYOnQuartic(wire, t);
            default -> 0;
        };
    }

    public void updateLength(Wire wire) {
        final int segments = 1000;
        double length = 0.0, t = 0.0;
        double previousX = getPositionX(wire, t);
        double previousY = getPositionY(wire, t);
        double currentX, currentY;

        for (int i = 1; i <= segments; i++) {
            t = (double) i / segments;
            currentX = getPositionX(wire, t);
            currentY = getPositionY(wire, t);
            length += Utils.measureDistance(currentX, currentY, previousX, previousY);

            previousX = currentX;
            previousY = currentY;
        }

        wire.setLength(length);
    }

    public void disableControlComponents() {
        for (WireView view : wireViewManager.getWireMap().values()) {
            view.disableControlComponents();
        }
    }

    private void updatePortsAndSystems(Port port1, Port port2) {
        port1.setAvailable(false);
        port2.setAvailable(false);
        systemService.updateSystem(port1.getSystem());
        systemService.updateSystem(port2.getSystem());
    }

    public void removeWire(Wire wire) {
        wireViewManager.removeWire(wire);
        gameState.removeWire(wire);
        wire.getStartPort().setAvailable(true);
        wire.getEndPort().setAvailable(true);
        systemService.updateSystem(wire.getStartPort().getSystem());
        systemService.updateSystem(wire.getEndPort().getSystem());
    }

    public boolean AreWiresReady() {
        for (Wire wire : gameState.getWires()) {
            if (!wire.isValid()) {
                return false;
            }
        }
        return true;
    }
}
