package engimon.physics.objects;

import engimon.core.Game;
import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.physics.collisions.SATCollisionDetection;
import engimon.physics.hitbox.Hitbox;
import engimon.physics.hitbox.RectangleHitbox;
import engimon.physics.signals.PhysicalObjectsCollisionSignal;
import engimon.signals.Signal;
import engimon.signals.SignalQueue;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * Physical objects are a type of GameObject which can interact with
 * each other through physics' engine.
 * Main function of physics objects is to provide the functionality
 * of collisions, which will be handled by engine, relieving the
 * game developer from having to implement it themselves.
 * <p>
 * PhysicalObject adds physical behaviour to its parent.
 */
@Getter
@Setter
public final class PhysicalObject extends GameObject {
    /**
     * Weight affects the way objects interact.
     * Heavier objects can't be easily moved by light ones.
     * Gravitational acceleration isn't affected by weight, but
     * heavier objects will accumulate more energy over given distance.
     */
    private double weight;

    /**
     * Speed variables define how much the object will move in
     * corresponding axis.
     * The unit of speed is distance units per second
     */
    private double speedX, speedY;

    /**
     * Rotary speed defines how much will object rotate around
     * its center.
     * The unit of rotations is radians per second.
     */
    private double rotarySpeed;

    /**
     * Gravity of object represent force inflicted upon
     * object at all times. It is relevant to object itself
     * and does not directly affect other objects.
     * The unit of acceleration is distance units per square second
     */
    private double gravityX, gravityY;
    /**
     * Frozen objects will not move until unfrozen.
     * Can still inflict forces on other objects.
     */
    private boolean frozen;

    /**
     * Determines how much does object bounce off on collision
     */
    private double bounciness;
    @Deprecated
    private boolean colliding;
    private CollisionType[] collisionLayers;
    private Hitbox hitbox;

    public PhysicalObject(Scene scene) {
        super(scene);
        weight = 1;
        gravityX = 0;
        gravityY = 0;
        speedX = 0;
        speedY = 0;
        frozen = false;
        colliding = true;
        collisionLayers = new CollisionType[32];
        Arrays.fill(collisionLayers, CollisionType.NO_COLLISIONS);

        SignalQueue.registerListener(PhysicalObjectsCollisionSignal.class, this::onCollision);
    }

    public void checkCollision(PhysicalObject other) {
        if (this == other) {
            return;
        }
        CollisionPositionAndForce collisionPositionAndForce;
        collisionPositionAndForce = SATCollisionDetection.calculateCollisionPositionAndForce(this, other);

        if (collisionPositionAndForce == null) {
            return;
        }
        SignalQueue.addToQueue(new PhysicalObjectsCollisionSignal(this, other, collisionPositionAndForce));
    }

    public void customTick(double deltaTime) {

        if (frozen) {
            this.speedX = 0.0;
            this.speedY = 0.0;
            this.rotarySpeed = 0.0;
            return;
        }

        GameObject m = (GameObject) getMaster();
        m.setLocalX(m.getLocalX() + this.speedX * deltaTime);
        m.setLocalY(m.getLocalY() + this.speedY * deltaTime);
        m.setLocalAngle(m.getLocalAngle() + this.rotarySpeed * deltaTime);

        this.speedX += (this.gravityX * this.weight * deltaTime);
        this.speedY += (this.gravityY * this.weight * deltaTime);

        for (PhysicalObject o : Game.instance.getScene().getPhysicalObjects()) {
            this.checkCollision(o);
        }

    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
        hitbox.setPhysicalObject(this);
        addChild(hitbox);
    }

    private void onCollision(Signal signal) {
        PhysicalObjectsCollisionSignal collisionSignal = (PhysicalObjectsCollisionSignal) signal;
        if (collisionSignal.getFirst() != this) {
            return;
        }
        CollisionPositionAndForce collisionPositionAndForce = collisionSignal.getCollisionPositionAndForce();
        GameObject m = (GameObject) getMaster();

        ((RectangleHitbox) this.getHitbox()).getSketch().setForceX(collisionPositionAndForce.getForceX());
        ((RectangleHitbox) this.getHitbox()).getSketch().setForceY(collisionPositionAndForce.getForceY());

        this.setSpeedX(collisionPositionAndForce.getForceX() / getWeight() * bounciness);
        this.setSpeedY(collisionPositionAndForce.getForceY() / getWeight() * bounciness);
        m.setLocalX(m.getLocalX() + collisionPositionAndForce.getForceX());
        m.setLocalY(m.getLocalY() + collisionPositionAndForce.getForceY());
    }

}
