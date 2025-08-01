package game.service;

import game.model.SquarePort;
import game.model.TrianglePort;
import game.model.Wire;
import game.model.WireType;
import game.view.WireViewManager;

public class WireService {
    private SystemService systemService;
    private WireViewManager wireViewManager;

    public WireService(SystemService systemService, WireViewManager wireViewManager) {
        this.systemService = systemService;
        this.wireViewManager = wireViewManager;
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
