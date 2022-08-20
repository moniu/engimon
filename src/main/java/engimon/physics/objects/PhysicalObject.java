package engimon.physics.objects;

import engimon.core.Game;
import engimon.core.Scene;
import engimon.core.objects.GameObject;
import engimon.physics.hitbox.Hitbox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhysicalObject extends GameObject {
    private double weight;
    private double speedX, speedY;
    private double gravityX, gravityY;
    private boolean frozen;
    private boolean colliding;

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

    }

    public void collideWithObject(PhysicalObject other) {
        //double distance;
        //double collisionNormalX, collisionNormalY;
        //GameObject m = (GameObject) master;

        //distance = Math.sqrt(Math.pow(this.getGlobalX() - other.getGlobalX(), 2) + Math.pow(this.getGlobalY() -
        // other.getGlobalY(), 2));
        //collisionNormalX = (this.getGlobalX() - other.getGlobalX()) / distance;
        //collisionNormalY = (this.getGlobalY() - other.getGlobalY()) / distance;

        if (other.isColliding()) {
            this.speedX = 0;
            this.speedY = 0;
        }
        //m.setLocalX(m.getLocalX() + collisionNormalX);
        //m.setLocalY(m.getLocalY() + collisionNormalY);

    }

    public void customTick(double deltaTime) {

        // @TODO dodać algorytm np AABB Minkowskiego
        if (frozen) {
            this.speedX = 0;
            this.speedY = 0;
            return;
        }

        deltaTime /= 1000000000;

        GameObject m = (GameObject) getMaster();
        m.setLocalX(m.getLocalX() + this.speedX * deltaTime);
        m.setLocalY(m.getLocalY() + this.speedY * deltaTime);

        this.speedX += (this.gravityX * this.weight * deltaTime);
        this.speedY += (this.gravityY * this.weight * deltaTime);

        for (PhysicalObject o : Game.instance.getScene().getPhysicalObjects()) {
            if (this.getHitbox().checkCollision(o.getHitbox())) {
                this.collideWithObject(o);
            }
        }

    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
        hitbox.setPhysicalObject(this);
        addChild(hitbox);
    }

}
