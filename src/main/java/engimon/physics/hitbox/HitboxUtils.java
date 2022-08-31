package engimon.physics.hitbox;

import engimon.core.objects.GameObject;
import engimon.physics.collisions.objects.Vector;

public abstract class HitboxUtils {
    public static double getDistance(GameObject first, GameObject second) {
        double distX = first.getGlobalX() - second.getGlobalX();
        double distY = first.getGlobalY() - second.getGlobalY();

        return Math.sqrt(distX * distX + distY * distY);
    }

    /**
     * <a href="https://stackoverflow.com/questions/41898990/find-corners-of-a-rotated-rectangle-given-its-center-point-and-rotation/56848101#56848101">Source</a>
     */
    public static void calculateVerticesOfRectangle(
            Vector[] vertices,
            double angle,
            double width,
            double height,
            double centerX,
            double centerY) {

        double w2 = width / 2.0;
        double h2 = height / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        vertices[0].set(w2 * cos - h2 * sin + centerX, w2 * sin + h2 * cos + centerY);
        vertices[1].set(-w2 * cos - h2 * sin + centerX, -w2 * sin + h2 * cos + centerY);
        vertices[2].set(-w2 * cos + h2 * sin + centerX, -w2 * sin - h2 * cos + centerY);
        vertices[3].set(w2 * cos + h2 * sin + centerX, w2 * sin - h2 * cos + centerY);
    }

    private static double dotProduct(double x1, double y1, double x2, double y2) {
        return x1 * x2 + y1 * y2;
    }

    private static double lenghtOfVector(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }
}
