package game.service.system;

import game.model.packets.Packet;
import game.model.packets.SquarePacket;
import game.model.packets.TrianglePacket;
import game.model.ports.BitPort;
import game.model.ports.Port;
import game.model.ports.SquarePort;
import game.model.ports.TrianglePort;
import game.model.systems.GeneralSystem;

public class PortFinder {

    public Port findAvailablePort(GeneralSystem system, Packet packet) {
        if (packet instanceof SquarePacket) {
            return findAvailablePortForSquare(system);
        } else if (packet instanceof TrianglePacket){
            return findAvailablePortForTriangle(system);
        } else {
            return findAvailablePortForBit(system);
        }
    }

    private Port findAvailablePortForSquare(GeneralSystem system) {
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if ((!(port instanceof SquarePort)) && port.getWire().getPacket() == null) {
                return port;
            }
        }
        return null;
    }

    private Port findAvailablePortForTriangle(GeneralSystem system) {
        for (Port port : system.getOutputPorts()) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if ((!(port instanceof TrianglePort)) && port.getWire().getPacket() == null) {
                return port;
            }
        }
        return null;
    }

    private Port findAvailablePortForBit(GeneralSystem system) {
        for (Port port : system.getOutputPorts()) {
            if (port instanceof BitPort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if ((!(port instanceof BitPort)) && port.getWire().getPacket() == null) {
                return port;
            }
        }
        return null;
    }

    public Port findUnsuitablePort(GeneralSystem system, Packet packet) {
        if (packet instanceof SquarePacket) {
            return findUnsuitablePortForSquare(system);
        } else if (packet instanceof TrianglePacket){
            return findUnsuitablePortForTriangle(system);
        } else {
            return findUnsuitablePortForBit(system);
        }
    }

    private Port findUnsuitablePortForSquare(GeneralSystem system) {
        for (Port port : system.getOutputPorts()) {
            if ((!(port instanceof SquarePort)) && port.getWire().getPacket() == null) {
                return port;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof SquarePort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        return null;
    }

    private Port findUnsuitablePortForTriangle(GeneralSystem system) {
        for (Port port : system.getOutputPorts()) {
            if ((!(port instanceof TrianglePort)) && port.getWire().getPacket() == null) {
                return port;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof TrianglePort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        return null;
    }

    private Port findUnsuitablePortForBit(GeneralSystem system) {
        for (Port port : system.getOutputPorts()) {
            if ((!(port instanceof BitPort)) && port.getWire().getPacket() == null) {
                return port;
            }
        }
        for (Port port : system.getOutputPorts()) {
            if (port instanceof BitPort && port.getWire().getPacket() == null) {
                return port;
            }
        }
        return null;
    }
}
