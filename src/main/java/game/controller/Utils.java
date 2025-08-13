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
        return wire.getControlX(0) * (1 - t) + wire.getControlX(1) * t;
    }

    public static double getYOnLine(Wire wire, double t) {
        return wire.getControlY(0) * (1 - t) + wire.getControlY(1) * t;
    }

    public static double getXOnQuad(Wire wire, double t) {
        double u = 1 - t;
        double uu = u * u, tt = t * t;

        return uu * wire.getControlX(0)
                + 2 * u * t * wire.getControlX(1)
                + tt * wire.getControlX(2);
    }

    public static double getYOnQuad(Wire wire, double t) {
        double u = 1 - t;
        double uu = u * u, tt = t * t;

        return uu * wire.getControlY(0)
                + 2 * u * t * wire.getControlY(1)
                + tt * wire.getControlY(2);
    }

    public static double getXOnCubic(Wire wire, double t) {
        double u = 1 - t;
        double uu = u * u, tt = t * t;
        double uuu = uu * u, ttt = tt * t;

        return uuu * wire.getControlX(0)
                + 3 * uu * t * wire.getControlX(1)
                + 3 * u * tt * wire.getControlX(2)
                + ttt * wire.getControlX(3);
    }

    public static double getYOnCubic(Wire wire, double t) {
        double u = 1 - t;
        double uu = u * u, tt = t * t;
        double uuu = uu * u, ttt = tt * t;

        return uuu * wire.getControlY(0)
                + 3 * uu * t * wire.getControlY(1)
                + 3 * u * tt * wire.getControlY(2)
                + ttt * wire.getControlY(3);
    }


    public static double getXOnQuartic(Wire wire, double t) {
        double u = 1 - t;
        double uu = u * u, tt = t * t;
        double uuu = uu * u, ttt = tt * t;
        double uuuu = uuu * u, tttt = ttt * t;

        return uuuu * wire.getControlX(0)
                + 4 * uuu * t * wire.getControlX(1)
                + 6 * uu * tt * wire.getControlX(2)
                + 4 * u * ttt * wire.getControlX(3)
                + tttt * wire.getControlX(4);
    }

    public static double getYOnQuartic(Wire wire, double t) {
        double u = 1 - t;
        double uu = u * u, tt = t * t;
        double uuu = uu * u, ttt = tt * t;
        double uuuu = uuu * u, tttt = ttt * t;

        return uuuu * wire.getControlY(0)
                + 4 * uuu * t * wire.getControlY(1)
                + 6 * uu * tt * wire.getControlY(2)
                + 4 * u * ttt * wire.getControlY(3)
                + tttt * wire.getControlY(4);
    }
}
