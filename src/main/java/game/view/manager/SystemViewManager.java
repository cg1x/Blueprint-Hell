package game.view.manager;

import game.model.systems.*;
import game.view.systems.*;

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
        } else if (system instanceof Spy) {
            view = new SpyView((Spy) system);
        } else if (system instanceof Ddos) {
            view = new DdosView((Ddos) system);
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