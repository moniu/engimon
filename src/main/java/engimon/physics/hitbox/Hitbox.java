package engimon.physics.hitbox;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.physics.objects.PhysicalObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Hitbox extends GameObject {
    public String shape;
    private PhysicalObject physicalObject;

    public Hitbox(Scene scene) {
        super(scene);
    }

    public abstract boolean checkCollision(Hitbox hb);

    public String getShape() {
        return shape;
    }

    public PhysicalObject getPhysicalObject() {
        return this.physicalObject;
    }

    public void setPhysicalObject(PhysicalObject physicalObject) {
        this.physicalObject = physicalObject;
        physicalObject.setHitbox(this);
    }
}
