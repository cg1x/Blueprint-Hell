package game.service;

import static game.controller.Constants.SYSTEM_SIZE;
import static game.controller.Constants.SYSTEM_TOP_HEIGHT;

import game.model.GeneralSystem;
import game.model.Port;
import game.model.SquarePort;
import game.model.TrianglePort;
import game.view.SquarePortView;
import game.view.TrianglePortView;
import game.view.PortView;
import game.view.PortViewManager;

public class PortService {
    private PortViewManager portViewManager;
    private WireService wireService;

    public PortService(PortViewManager portViewManager, WireService wireService) {
        this.portViewManager = portViewManager;
        this.wireService = wireService;
    }

    public void paintAllPorts(GeneralSystem system) {
        double x = system.getInitialX();
        double y = system.getInitialY();
        // paint input ports
        for (Port port : system.getInputPorts()) {
            if (port instanceof SquarePort) {
                port.setX(x - 5);
                port.setY(y + 10 + ((system.getInputPorts().indexOf(port) + 1) * SYSTEM_TOP_HEIGHT));
            }
            else if (port instanceof TrianglePort) {
                port.setX(x);
                port.setY(y + 10 + ((system.getInputPorts().indexOf(port) + 1) * SYSTEM_TOP_HEIGHT));
            }
            portViewManager.getView(port).paint();
            portViewManager.getView(port).enableDrawLine(wireService);
        }
        // paint output ports
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort) {
                port.setX(x + SYSTEM_SIZE - 5);
                port.setY(y + 10 + ((system.getOutputPorts().indexOf(port) + 1) * SYSTEM_TOP_HEIGHT));
            }
            else if (port instanceof TrianglePort) {
                port.setX(x + SYSTEM_SIZE);
                port.setY(y + 10 + ((system.getOutputPorts().indexOf(port) + 1) * SYSTEM_TOP_HEIGHT));
            }
            portViewManager.getView(port).paint();
            portViewManager.getView(port).enableDrawLine(wireService);
        }
    }

}
