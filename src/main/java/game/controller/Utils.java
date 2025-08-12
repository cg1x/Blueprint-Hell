package game.controller;

import game.model.Wire;

public class Utils {
    public static double measureDistance(double x2, double y2,
                                         double x1, double y1) {
        double x = x2 - x1;
        double y = y2 - y1;
        return Math.sqrt(x * x + y * y);
    }

    public static double getXOnLine(Wire wire, double t) {
        return wire.getStartX() * (1 - t) + wire.getEndX() * t;
    }

    public static double getYOnLine(Wire wire, double t) {
        return wire.getStartY() * (1 - t) + wire.getEndY() * t;
    }

    public static double getXOnQuad(Wire wire, double t) {
        double u = 1 - t;
        double uu = u * u;
        double tt = t * t;
        return uu * wire.getStartX() + 2*u*t * wire.getControlX() + tt * wire.getEndX();
    }

    public static double getYOnQuad(Wire wire, double t) {
        double u = 1 - t;
        double uu = u * u;
        double tt = t * t;
        return uu * wire.getStartY() + 2*u*t * wire.getControlY() + tt * wire.getEndY();
    }
}
