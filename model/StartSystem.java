package game.model;

import game.view.GeneralSystemView;
import game.view.StartSystemView;

import java.util.ArrayList;

import static game.model.collision.Collidable.collidables;


public final class StartSystem extends GeneralSystem {

    private static StartSystem INSTANCE;
    public double initialX;
    public double initialY;
    public StartSystemView systemView;
    public boolean ready = false;
    private GameState gameState;

    public ArrayList<Port> inputPorts = new ArrayList<>();
    public ArrayList<Port> outputPorts = new ArrayList<>();
    public ArrayList<Packet> pendingPackets = new ArrayList<>();

    public StartSystem(double x, double y) {
        super(x, y);
        INSTANCE = this;
        initialX = x;
        initialY = y;
    }

    public void generatePackets() {
        if (gameState.getCurrentLevel() == 1) {
            new TrianglePacket();
            new SquarePacket();
            new TrianglePacket();
            new SquarePacket();
            new TrianglePacket();
            new SquarePacket();
        }
        if (gameState.getCurrentLevel()  == 2) {
            new TrianglePacket();
            new SquarePacket();
            new TrianglePacket();
            new SquarePacket();
            new TrianglePacket();
            new SquarePacket();
            new TrianglePacket();
            new SquarePacket();
            new TrianglePacket();
            new SquarePacket();
        }
    }

    public boolean isReady() {
        return ready;
    }

    public ArrayList<Packet> getPendingPackets() {
        return pendingPackets;
    }

    @Override
    public void decideForPacket(TrianglePacket packet) {
        if (!packet.isFirst()) {
            packet.remove();
            return;
        }
        collidables.remove(packet);
        for (Port port : outputPorts) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        for (Port port : outputPorts) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        pendingPackets.add(packet);
    }

    @Override
    public void decideForPacket(SquarePacket packet) {
        if (!packet.isFirst()) {
            packet.remove();
            return;
        }
        collidables.remove(packet);
        for (Port port : outputPorts) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        for (Port port : outputPorts) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                packet.setPort(port);
                return;
            }
        }
        pendingPackets.add(packet);
    }

    @Override
    public void updateIndicator() {
        int availablePorts = inputPorts.size() + outputPorts.size();

        for (Port port : inputPorts) {
           if (!(port.isAvailable())) {
               availablePorts--;
            }
        }

        for (Port port : outputPorts) {
            if (!(port.isAvailable())) {
                availablePorts--;
            }
        }

        if (availablePorts == 0) {
            systemView.turnOnIndicator();
            ready = true;
            systemView.updateButton();
        } else {
            systemView.turnOffIndicator();
            ready = false;
            systemView.updateButton();
        }
    }

    @Override
    public double getInitialY() {
        return initialY;
    }
    @Override
    public double getInitialX() {
        return initialX;
    }
    @Override
    public StartSystemView getView() {
        return systemView;
    }
    @Override
    public ArrayList<Port> getInputPorts() {
        return inputPorts;
    }
    @Override
    public ArrayList<Port> getOutputPorts() {
        return outputPorts;
    }
    @Override
    public void setView(GeneralSystemView systemView) {
        this.systemView = (StartSystemView) systemView;
    }
    @Override
    public void addPort(Port port, PortType portType) {
        if (portType == PortType.INPUT) {
            inputPorts.add(port);
        } else if (portType == PortType.OUTPUT) {
            outputPorts.add(port);
        }
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public static StartSystem getINSTANCE() {
        return INSTANCE;
    }
}
