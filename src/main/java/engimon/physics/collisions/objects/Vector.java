package engimon.physics.collisions.objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vector {
    public double x, y;

    public static void subtract(Vector first, Vector second, Vector dest) {
        dest.x = first.x - second.x;
        dest.y = first.y - second.y;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double dotProduct(Vector other) {
        return this.x * other.x + this.y * other.y;
    }

    public void normalizeInto(Vector dest) {
        double length = Math.sqrt(x * x + y * y);
        dest.x = x / length;
        dest.y = y / length;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public void perpendicularTo(Vector dest) {
        dest.set(-this.y, this.x);
    }

    public void getProjection(Projection projection, Vector[] vertices) {
        double[] dots = new double[4];
        dots[0] = dotProduct(vertices[0]);
        dots[1] = dotProduct(vertices[1]);
        dots[2] = dotProduct(vertices[2]);
        dots[3] = dotProduct(vertices[3]);
        projection.max = Math.max(Math.max(dots[0], dots[1]), Math.max(dots[2], dots[3]));
        projection.min = Math.min(Math.min(dots[0], dots[1]), Math.min(dots[2], dots[3]));
    }

}
