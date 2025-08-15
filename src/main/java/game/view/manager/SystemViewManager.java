package game.view.manager;

import game.model.systems.GeneralSystem;
import game.model.systems.Transferor;
import game.model.systems.Server;
import game.view.systems.GeneralSystemView;
import game.view.systems.ServerView;
import game.view.systems.TransferorView;

import java.util.HashMap;
import java.util.Map;

public class SystemViewManager {
    private final Map<GeneralSystem, GeneralSystemView> systemMap = new HashMap<>();

    public void addSystem(GeneralSystem system) {
        GeneralSystemView view = null;
        if (system instanceof Transferor) {
            view = new TransferorView((Transferor) system);
        } else if (system instanceof Server) {
            view = new ServerView((Server) system);
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