package engimon.physics.signals;

import engimon.physics.objects.CollisionPositionAndForce;
import engimon.physics.objects.PhysicalObject;
import engimon.signals.Signal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhysicalObjectsCollisionSignal extends Signal {
    private final PhysicalObject first;
    private final PhysicalObject second;
    private final CollisionPositionAndForce collisionPositionAndForce;
}
