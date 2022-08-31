package engimon.physics.hitbox;

import engimon.core.Scene;
import engimon.physics.objects.PhysicalObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RectangleHitbox extends Hitbox {
    private double sizeX, sizeY;
    private PhysicalObject physicalObject;
    @Getter
    private RectangleHitboxSketch sketch;

    public RectangleHitbox(Scene scene, double sizeX, double sizeY) {
        super(scene);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.shape = HitboxShape.RECTANGLE;
        sketch = new RectangleHitboxSketch(scene, this);
        scene.registerGraphicObject(sketch);
        addChild(sketch);
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
        return false;
    }

    /**
     * Source:
     * <a href="https://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection">Stackoverflow question</a>
     * <a href="https://stackoverflow.com/a/402010">Answer</a>
     * by <a href="https://stackoverflow.com/users/33686/e-james">E-James</a>
     **/

    public boolean checkCollisionWithCircle(Hitbox other) {
        CircleHitbox cHitbox = (CircleHitbox) other;
        double circleDistanceX = Math.abs(cHitbox.getGlobalX() - this.getGlobalX());
        double circleDistanceY = Math.abs(cHitbox.getGlobalY() - this.getGlobalY());

        if (circleDistanceX > (this.getSizeX() / 2 + cHitbox.getRadius())) {
            return false;
        }
        if (circleDistanceY > (this.getSizeY() / 2 + cHitbox.getRadius())) {
            return false;
        }

        if (circleDistanceX <= (this.getSizeX() / 2)) {
            return true;
        }
        if (circleDistanceY <= (this.getSizeY() / 2)) {
            return true;
        }

        double cornerDistance_sq = Math.pow(circleDistanceX - this.getSizeX() / 2, 2.0) +
                Math.pow(circleDistanceY - this.getSizeY() / 2, 2.0);
        return cornerDistance_sq <= Math.pow(cHitbox.getRadius(), 2);
    }

}
