package game.view.wire;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.List;

public class QuarticCurve extends Path {
    private List<Double> controlX;
    private List<Double> controlY;

    public QuarticCurve(List<Double> controlX, List<Double> controlY) {
        this.controlX = controlX;
        this.controlY = controlY;
        paint();
    }

    public void paint() {
        getElements().clear();
        getElements().add(new MoveTo(controlX.getFirst(), controlY.getFirst()));

        int segments = 100;
        double t, x, y;
        for (int i = 1; i <= segments; i++) {
            t = i / (double) segments;
            x = getXOnCurve(t);
            y = getYOnCurve(t);
            getElements().add(new LineTo(x, y));
        }
    }

    private double getXOnCurve(double t) {
        double u = 1 - t;
        double uu = u * u, tt = t * t;
        double uuu = uu * u, ttt = tt * t;
        double uuuu = uuu * u, tttt = ttt * t;

        return uuuu * controlX.get(0)
                + 4 * uuu * t * controlX.get(1)
                + 6 * uu * tt * controlX.get(2)
                + 4 * u * ttt * controlX.get(3)
                + tttt * controlX.get(4);
    }

    private double getYOnCurve(double t) {
        double u = 1 - t;
        double uu = u * u, tt = t * t;
        double uuu = uu * u, ttt = tt * t;
        double uuuu = uuu * u, tttt = ttt * t;

        return uuuu * controlY.get(0)
                + 4 * uuu * t * controlY.get(1)
                + 6 * uu * tt * controlY.get(2)
                + 4 * u * ttt * controlY.get(3)
                + tttt * controlY.get(4);
    }

    public void updateCurve(List<Double> controlX, List<Double> controlY) {
        this.controlX = controlX;
        this.controlY = controlY;
        paint();
    }
}
