package game.model;

import game.view.GeneralSystemView;
import game.view.SystemView;

import java.util.ArrayList;

import static game.model.collision.Collidable.collidables;

public class SystemModel extends GeneralSystem {
    public double initialX;
    public double initialY;
    public boolean ready = false;
    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();
    public ArrayList<Packet> pendingPackets = new ArrayList<>();
    public static ArrayList<SystemModel> systems = new ArrayList<>();

    public SystemModel(double x, double y) {
        super(x, y);
        initialX = x;
        initialY = y;
        systems.add(this);
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public void setReady(boolean ready) {
        super.setReady(ready);
    }

    public ArrayList<Packet> getPendingPackets() {
        return pendingPackets;
    }

    @Override
    public double getInitialY() {
        return initialY;
    }

    @Override
    public double getInitialX() {
        return initialX;
    }
}
