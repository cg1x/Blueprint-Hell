package game.view;

import game.model.GeneralSystem;
import game.model.SystemModel;
import game.model.StartSystem;
import java.util.HashMap;
import java.util.Map;

public class SystemViewManager {
    private final Map<GeneralSystem, GeneralSystemView> systemMap = new HashMap<>();

    public void addSystem(GeneralSystem system) {
        GeneralSystemView view = null;
        if (system instanceof SystemModel) {
            view = new SystemView((SystemModel) system);
        } else if (system instanceof StartSystem) {
            view = new StartSystemView((StartSystem) system);
        } 
        systemMap.put(system, view);
    }

    public Map<GeneralSystem, GeneralSystemView> getSystemMap() {
        return systemMap;
    }

    public GeneralSystemView getView(GeneralSystem system) {
        return systemMap.get(system);
    }

    public void removeSystem(GeneralSystem system) {
        systemMap.remove(system);
    }

    public void clear() {
        systemMap.clear();
    }
} 