package game.view.manager;

import java.util.HashMap;
import java.util.Map;

import game.model.Wire;
import game.view.WireView;

public class WireViewManager {
    private final Map<Wire, WireView> wireMap = new HashMap<>();

    public void addWire(Wire wire) {
        wireMap.put(wire, new WireView(wire));
    }

    public Map<Wire, WireView> getWireMap() {
        return wireMap;
    }

    public WireView getView(Wire wire) {
        return wireMap.get(wire);
    }

    public void removeWire(Wire wire) {
        wireMap.remove(wire);
    }

    public void clear() {
        wireMap.clear();
    }
}
