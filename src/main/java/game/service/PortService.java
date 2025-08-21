package game.service;

import game.model.packets.BitPacket;
import game.model.packets.Packet;
import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;
import game.model.ports.BitPort;
import game.model.systems.GeneralSystem;
import game.model.ports.Port;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.view.manager.PortViewManager;

import static game.controller.Constants.*;
import static game.controller.Constants.PORT_SIZE;

public class PortService {
    private PortViewManager portViewManager;
    private WireService wireService;

    public PortService(PortViewManager portViewManager, WireService wireService) {
        this.portViewManager = portViewManager;
        this.wireService = wireService;
    }

    public void resetSpeed(Packet packet) {
        Port port = packet.getWire().getEndPort();
        if (packet instanceof SquarePacket) {
            assignSquarePacketToPort((SquarePacket) packet, port);
        } else if (packet instanceof TrianglePacket) {
            assignTrianglePacketToPort((TrianglePacket) packet, port);
        } else {
            assignBitPacketToPort((BitPacket) packet, port);
        }
    }

    private void assignSquarePacketToPort(SquarePacket packet, Port port) {
        packet.setX(port.getCenterX() - PORT_SIZE/2 + packet.getDeflectionX());
        packet.setY(port.getCenterY() - PORT_SIZE/2 + packet.getDeflectionY());
        if (port instanceof SquarePort) {
            packet.setSpeed(2);
        }
        if (port instanceof TrianglePort) {
            packet.setSpeed(4);
        }
    }

    private void assignTrianglePacketToPort(TrianglePacket packet, Port port) {
        packet.setX(port.getCenterX() + packet.getDeflectionX());
        packet.setY(port.getCenterY() - PORT_SIZE/2 + packet.getDeflectionY());
        if (port instanceof SquarePort) {
            packet.setSpeed(1);
        }
        if (port instanceof TrianglePort) {
            packet.setSpeed(2);
        }
    }

    private void assignBitPacketToPort(BitPacket packet, Port port) {
        packet.setX(port.getCenterX() - PORT_SIZE/2 + packet.getDeflectionX());
        packet.setY(port.getCenterY() - PORT_SIZE/2 + packet.getDeflectionY());
        packet.setSpeed(1);
        packet.setAcceleration(0.005);
    }

    public void paintAllPorts(GeneralSystem system) {
        double x = system.getInitialX();
        double y = system.getInitialY();
        // paint input ports
        for (Port port : system.getInputPorts()) {
            if (port instanceof SquarePort || port instanceof BitPort) {
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
            if (port instanceof SquarePort || port instanceof BitPort) {
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
