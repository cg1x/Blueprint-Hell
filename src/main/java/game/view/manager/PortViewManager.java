package game.view.manager;

import java.util.HashMap;
import java.util.Map;

import game.model.ports.BitPort;
import game.model.ports.Port;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.view.ports.BitPortView;
import game.view.ports.PortView;
import game.view.ports.SquarePortView;
import game.view.ports.TrianglePortView;

public class PortViewManager {
    private final Map<Port, PortView> portViews = new HashMap<>();

    public void addPort(Port port) {
        if (port instanceof SquarePort) {
            portViews.put(port, new SquarePortView((SquarePort) port));
        } else if (port instanceof TrianglePort) {
            portViews.put(port, new TrianglePortView((TrianglePort) port));
        } else if (port instanceof BitPort) {
            portViews.put(port, new BitPortView((BitPort) port));
        }
    }

    public PortView getView(Port port) {
        return portViews.get(port);
    }

    public void clear() {
        portViews.clear();
    }
}
