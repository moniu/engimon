package engimon.physics.hitbox;

import engimon.core.Scene;
import engimon.physics.objects.PhysicalObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircleHitbox extends Hitbox {
    private double radius;
    private PhysicalObject physicalObject;

    public CircleHitbox(Scene scene, double radius) {
        super(scene);
        this.radius = radius;
        this.shape = HitboxShape.CIRCLE;
    }

    @Override
    public boolean checkCollision(Hitbox other) {
        if (other == this) {
            return false;
        }
        return switch (other.shape) {
            case RECTANGLE -> checkCollisionWithRectangle(other);
            case CIRCLE -> checkCollisionWithCircle(other);
        };
    }

    public boolean checkCollisionWithRectangle(Hitbox other) {
        RectangleHitbox rectangleHitbox = (RectangleHitbox) other;
        return rectangleHitbox.checkCollisionWithCircle(this);
    }

    public boolean checkCollisionWithCircle(Hitbox other) {
        CircleHitbox circleHitbox = (CircleHitbox) other;

        double distance = HitboxUtils.getDistance(this, other);

        return this.getRadius() + circleHitbox.getRadius() > distance;

    }
}
