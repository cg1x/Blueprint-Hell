package game.service;

import game.model.*;
import game.model.ports.Port;
import game.model.ports.PortType;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.view.manager.WireViewManager;

public class WireService {
    private SystemService systemService;
    private WireViewManager wireViewManager;

    public WireService(SystemService systemService, WireViewManager wireViewManager) {
        this.systemService = systemService;
        this.wireViewManager = wireViewManager;
    }

    public void handleConnection(Port startPort, Port endPort) {
        if (AreConnectable(startPort, endPort)) {
            if (startPort instanceof SquarePort) {
                createWire((SquarePort) startPort, (SquarePort) endPort);
            } else {
                createWire((TrianglePort) startPort, (TrianglePort) endPort);
            }
        }
    }

    public boolean AreConnectable(Port startPort, Port endPort) {
        boolean bool1 = startPort.getPortType() == PortType.OUTPUT;
        boolean bool2 = endPort.getPortType() == PortType.INPUT;
        boolean bool3 = startPort.getSystem() != endPort.getSystem();
        boolean bool4 = endPort.isAvailable();

        return bool1 && bool2 && bool3 && bool4;
    }

    public void createWire(SquarePort port1, SquarePort port2) {
        //initialize wire model and view
        Wire wire = new Wire(port1, port2, WireType.SQUARE, null);
        wireViewManager.addWire(wire);
        wireViewManager.getView(wire).enableWireRemoval(this);
        //update ports and systems status
        port1.setAvailable(false);
        port2.setAvailable(false);
        systemService.updateSystemIndicator(port1.getSystem());
        systemService.updateSystemIndicator(port2.getSystem());
    }

    public void createWire(TrianglePort port1, TrianglePort port2) {
        //initialize wire model and view
        Wire wire = new Wire(port1, port2, WireType.TRIANGLE, null);
        wireViewManager.addWire(wire);
        wireViewManager.getView(wire).enableWireRemoval(this);
        //update ports and systems status
        port1.setAvailable(false);
        port2.setAvailable(false);
        systemService.updateSystemIndicator(port1.getSystem());
        systemService.updateSystemIndicator(port2.getSystem());
    }

    public void removeWire(Wire wire) {
        wireViewManager.removeWire(wire);
        wire.getStartPort().setAvailable(true);
        wire.getEndPort().setAvailable(true);
        systemService.updateSystemIndicator(wire.getStartPort().getSystem());
        systemService.updateSystemIndicator(wire.getEndPort().getSystem());
    }
}
