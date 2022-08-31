package engimon.physics.collisions;

import engimon.physics.collisions.objects.Projection;
import engimon.physics.collisions.objects.Vector;
import engimon.physics.hitbox.Hitbox;
import engimon.physics.hitbox.HitboxShape;
import engimon.physics.hitbox.RectangleHitbox;
import engimon.physics.objects.CollisionPositionAndForce;
import engimon.physics.objects.PhysicalObject;

import static engimon.physics.hitbox.HitboxUtils.calculateVerticesOfRectangle;

public class SATCollisionDetection {

    static double h1GlobalX;
    static double h1GlobalY;
    static double h1GlobalAngle;
    static double h1SizeX;
    static double h1SizeY;

    static double h2GlobalX;
    static double h2GlobalY;
    static double h2GlobalAngle;
    static double h2SizeX;
    static double h2SizeY;
    static Vector[] firstRectVertices = {new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), new Vector(0, 0)};
    static Vector[] secondRectVertices = {new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), new Vector(0, 0)};
    static Vector[] edges = {new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), new Vector(0, 0)};
    static Vector[] normals = {new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), new Vector(0, 0)};
    static Vector[] axis = {new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), new Vector(0, 0)};
    static Projection[] firstProjections = {new Projection(0, 0), new Projection(0, 0), new Projection(0, 0),
            new Projection(0, 0)};
    static Projection[] secondProjections = {new Projection(0, 0), new Projection(0, 0), new Projection(0, 0),
            new Projection(0, 0)};
    static Projection[] overlaps = {new Projection(0, 0), new Projection(0, 0), new Projection(0, 0),
            new Projection(0, 0)};
    static double[] overlapSizes = new double[4];

    static CollisionPositionAndForce defaultCPAF = new CollisionPositionAndForce(0, 0, 0, 0);

    public static CollisionPositionAndForce calculateCollisionPositionAndForce(PhysicalObject first,
                                                                               PhysicalObject second) {
        return calculateCollisionPositionAndForce(first.getHitbox(), second.getHitbox());
    }

    public static CollisionPositionAndForce calculateCollisionPositionAndForce(Hitbox first, Hitbox second) {
        if (first.getShape() != HitboxShape.RECTANGLE) {
            throw new RuntimeException("Not implemented");
        }
        if (second.getShape() != HitboxShape.RECTANGLE) {
            throw new RuntimeException("Not implemented");
        }

        RectangleHitbox h1 = (RectangleHitbox) first;
        RectangleHitbox h2 = (RectangleHitbox) second;

        h1GlobalX = h1.getGlobalX();
        h1GlobalY = h1.getGlobalY();
        h1GlobalAngle = h1.getGlobalAngle();
        h1SizeX = h1.getSizeX();
        h1SizeY = h1.getSizeY();

        h2GlobalX = h2.getGlobalX();
        h2GlobalY = h2.getGlobalY();
        h2GlobalAngle = h2.getGlobalAngle();
        h2SizeX = h2.getSizeX();
        h2SizeY = h2.getSizeY();

        calculateVerticesOfRectangle(firstRectVertices, h1GlobalAngle, h1SizeX, h1SizeY, h1GlobalX, h1GlobalY);
        calculateVerticesOfRectangle(secondRectVertices, h2GlobalAngle, h2SizeX, h2SizeY, h2GlobalX, h2GlobalY);
        //A stands for Axis - as in Separating Axis Theorem

        int minOverlapIndex = 0;

        Vector.subtract(firstRectVertices[0], firstRectVertices[1], edges[0]);
        Vector.subtract(firstRectVertices[1], firstRectVertices[2], edges[1]);
        Vector.subtract(secondRectVertices[0], secondRectVertices[1], edges[2]);
        Vector.subtract(secondRectVertices[1], secondRectVertices[2], edges[3]);

        for (int i = 0; i < 4; i++) {
            edges[i].normalizeInto(normals[i]);
            normals[i].perpendicularTo(axis[i]);
            axis[i].getProjection(firstProjections[i], firstRectVertices);
            axis[i].getProjection(secondProjections[i], secondRectVertices);
            overlapSizes[i] = firstProjections[i].overlaping(secondProjections[i], overlaps[i]);
            if (overlapSizes[i] == 0.0) {
                return null;
            }
            if (overlapSizes[i] < overlapSizes[minOverlapIndex]) {
                minOverlapIndex = i;
            }
        }

        CollisionPositionAndForce collisionPositionAndForce = new CollisionPositionAndForce();
        collisionPositionAndForce.setPositionX(h1GlobalX); //Póki co ustawiam na środek pierwszego obiektu
        collisionPositionAndForce.setPositionY(h1GlobalY);
        collisionPositionAndForce.setForceX(overlapSizes[minOverlapIndex] * axis[minOverlapIndex].x);
        collisionPositionAndForce.setForceY(overlapSizes[minOverlapIndex] * axis[minOverlapIndex].y);

        return collisionPositionAndForce;
    }


}
