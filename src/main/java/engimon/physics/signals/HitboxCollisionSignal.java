package engimon.physics.signals;

import engimon.physics.hitbox.Hitbox;
import engimon.signals.Signal;
import lombok.Getter;

@Getter
public final class HitboxCollisionSignal extends Signal {
    private final Hitbox first;
    private final Hitbox second;

    public HitboxCollisionSignal(Hitbox first, Hitbox second) {
        this.first = first;
        this.second = second;
    }


}
