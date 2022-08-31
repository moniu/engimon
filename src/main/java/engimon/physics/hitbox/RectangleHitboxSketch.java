package engimon.physics.hitbox;

import engimon.core.Scene;
import engimon.graphics.GraphicObject;
import engimon.graphics.RelativeGraphics;
import engimon.physics.collisions.objects.Vector;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class RectangleHitboxSketch extends GraphicObject {

    private final RectangleHitbox rectangleHitbox;
    private final Vector[] vertices = {new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), new Vector(0, 0)};
    private double forceX;
    private double forceY;

    public RectangleHitboxSketch(Scene scene, RectangleHitbox rectangleHitbox) {
        super(scene);
        this.layer = 500;
        this.rectangleHitbox = rectangleHitbox;
        this.relative = true;
    }

    @Override
    public void render(RelativeGraphics relativeGraphics) {
        HitboxUtils.calculateVerticesOfRectangle(vertices,
                                                 getGlobalAngle(),
                                                 rectangleHitbox.getSizeX(),
                                                 rectangleHitbox.getSizeY(),
                                                 rectangleHitbox.getGlobalX(),
                                                 rectangleHitbox.getGlobalY()
        );

        relativeGraphics.setColor(Color.RED);
        relativeGraphics.drawLine(vertices[0].x, vertices[0].y,
                                  vertices[1].x, vertices[1].y
        );
        relativeGraphics.drawLine(vertices[1].x, vertices[1].y,
                                  vertices[2].x, vertices[2].y
        );
        relativeGraphics.drawLine(vertices[2].x, vertices[2].y,
                                  vertices[3].x, vertices[3].y
        );
        relativeGraphics.drawLine(vertices[3].x, vertices[3].y,
                                  vertices[0].x, vertices[0].y
        );

        relativeGraphics.setColor(Color.GREEN);
        relativeGraphics.drawLine(
                rectangleHitbox.getGlobalX(), rectangleHitbox.getGlobalY(),
                rectangleHitbox.getGlobalX() + forceX * 100, rectangleHitbox.getGlobalY() + forceY * 100
        );
    }
}
