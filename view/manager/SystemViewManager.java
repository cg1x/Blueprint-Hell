package game.view.manager;

import game.model.systems.GeneralSystem;
import game.model.systems.SystemModel;
import game.model.systems.StartSystem;
import game.view.systems.GeneralSystemView;
import game.view.systems.StartSystemView;
import game.view.systems.SystemView;

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