package engimon.physics.hitbox;

import engimon.core.objects.GameObject;

public abstract class HitboxUtils {
    public static double getDistance(GameObject first, GameObject second) {
        double distX = first.getGlobalX() - second.getGlobalX();
        double distY = first.getGlobalY() - second.getGlobalY();

        return Math.sqrt(distX * distX + distY * distY);
    }
}
