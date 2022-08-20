package engimon.physics.hitbox;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.physics.objects.PhysicalObject;
import engimon.physics.signals.HitboxCollisionSignal;
import engimon.signals.SignalQueue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Hitbox extends GameObject {
    public HitboxShape shape;
    private PhysicalObject physicalObject;

    public Hitbox(Scene scene) {
        super(scene);
    }

    public abstract boolean checkCollision(Hitbox other);

    public HitboxShape getShape() {
        return shape;
    }

    public PhysicalObject getPhysicalObject() {
        return this.physicalObject;
    }

    public void setPhysicalObject(PhysicalObject physicalObject) {
        this.physicalObject = physicalObject;
        physicalObject.setHitbox(this);
    }

    @Override
    public void customTick(double deltaTime) {
        for (PhysicalObject physicalObject : getScene().getPhysicalObjects()) {
            Hitbox otherHitbox = physicalObject.getHitbox();
            if (checkCollision(otherHitbox)) {
                SignalQueue.INSTANCE.addToQueue(
                        new HitboxCollisionSignal(this, otherHitbox)
                );
            }
        }
    }
}
