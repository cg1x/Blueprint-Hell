package game.controller;

public class Utils {
    public static double measureDistance(double x2, double y2,
                                         double x1, double y1) {
        double x = x2 - x1;
        double y = y2 - y1;
        return Math.sqrt(x * x + y * y);
    }
}
