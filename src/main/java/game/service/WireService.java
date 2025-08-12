package game.service;

import game.controller.Utils;
import game.model.*;
import game.model.ports.Port;
import game.model.ports.PortType;
import game.model.ports.SquarePort;
import game.model.systems.GeneralSystem;
import game.view.WireView;
import game.view.manager.ViewManager;
import game.view.manager.WireViewManager;
import javafx.geometry.Point2D;

public class WireService {
    private SystemService systemService;
    private WireViewManager wireViewManager;
    private ViewManager viewManager;
    private GameState gameState;

    public WireService(SystemService systemService, WireViewManager wireViewManager,
                       ViewManager viewManager, GameState gameState) {
        this.systemService = systemService;
        this.wireViewManager = wireViewManager;
        this.viewManager = viewManager;
        this.gameState = gameState;
    }

    public void handleConnection(Port startPort, Port endPort) {
        if (AreConnectable(startPort, endPort)) {
            createWire(startPort, endPort);
        }
    }

    public boolean AreConnectable(Port startPort, Port endPort) {
        boolean bool1 = startPort.getPortType() == PortType.OUTPUT;
        boolean bool2 = endPort.getPortType() == PortType.INPUT;
        boolean bool3 = startPort.getSystem() != endPort.getSystem();
        boolean bool4 = endPort.isAvailable();

        return bool1 && bool2 && bool3 && bool4;
    }

    public void createWire(Port port1, Port port2) {
        Wire wire = null;
        if (port1 instanceof SquarePort) {
            wire = new Wire(port1, port2, WireType.SQUARE);
        } else {
            wire = new Wire(port1, port2, WireType.TRIANGLE);
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
//        for (GeneralSystem system : systemService.getAllSystems()) {
//            if (viewManager.AreIntersecting(wire, system)) {
//                wire.setValid(false);
//                return;
//            }
//        }
        wire.setValid(true);
    }

    public double getPositionX(Wire wire, double t) {
        double x = 0;
        switch (wire.getControlPointsCount()) {
            case 2:
                x = Utils.getXOnLine(wire, t);
            case 3:
                x = Utils.getXOnQuad(wire, t);
            default:
                break;
        }
        return x;
    }

    public double getPositionY(Wire wire, double t) {
        double y = 0;
        switch (wire.getControlPointsCount()) {
            case 2:
                y = Utils.getYOnLine(wire, t);
            case 3:
                y = Utils.getYOnQuad(wire, t);
            default:
                break;
        }
        return y;
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
