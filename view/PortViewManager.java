package game.view;

import java.util.HashMap;
import java.util.Map;

import game.model.*;

public class PortViewManager {
    private final Map<Port, PortView> portViews = new HashMap<>();

    public void addPort(Port port) {
        if (port instanceof SquarePort) {
            portViews.put(port, new SquarePortView((SquarePort) port));
        } else if (port instanceof TrianglePort) {
            portViews.put(port, new TrianglePortView((TrianglePort) port));
        }
    }

    public PortView getView(Port port) {
        return portViews.get(port);
    }

    public void clear() {
        portViews.clear();
    }
}
