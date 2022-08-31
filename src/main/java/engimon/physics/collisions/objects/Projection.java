package engimon.physics.collisions.objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Projection {
    public double max, min;

    public double overlaping(Projection other, Projection dest) {
        if (this.max < this.min) {
            double m = this.max;
            this.max = this.min;
            this.min = m;
        }
        if (other.max < other.min) {
            double m = other.max;
            other.max = other.min;
            other.min = m;
        }
        if (this.max < other.min || other.max < this.min) {
            dest.min = 0.0;
            dest.max = 0.0;
            return 0.0;
        }

        dest.min = Math.max(this.min, other.min);
        dest.max = Math.min(this.max, other.max);
        return dest.max - dest.min;

    }
}
