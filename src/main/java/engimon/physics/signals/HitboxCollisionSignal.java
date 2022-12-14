package engimon.physics.signals;

import engimon.physics.hitbox.Hitbox;
import engimon.physics.objects.CollisionPositionAndForce;
import engimon.signals.Signal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class HitboxCollisionSignal extends Signal {
    private final Hitbox first;
    private final Hitbox second;
    private final CollisionPositionAndForce collisionPositionAndForce;
}
