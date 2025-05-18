package game.controller;

import game.model.PortType;
import game.view.SquarePortView;
import game.view.TrianglePortView;

public class Controller {
    public static boolean connectable(SquarePortView startPort, SquarePortView endPort) {
        boolean bool1 = startPort.port.getPortType() == PortType.OUTPUT;
        boolean bool2 = endPort.port.getPortType() == PortType.INPUT;
        boolean bool3 = startPort.port.getSystem() != endPort.port.getSystem();
        boolean bool4 = endPort.port.available;

        return bool1 && bool2 && bool3 && bool4;
    }

    public static boolean connectable(TrianglePortView startPort, TrianglePortView endPort) {
        boolean bool1 = startPort.port.getPortType() == PortType.OUTPUT;
        boolean bool2 = endPort.port.getPortType() == PortType.INPUT;
        boolean bool3 = startPort.port.getSystem() != endPort.port.getSystem();
        boolean bool4 = endPort.port.available;

        return bool1 && bool2 && bool3 && bool4;
    }
}
