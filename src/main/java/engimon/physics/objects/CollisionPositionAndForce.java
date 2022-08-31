package engimon.physics.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CollisionPositionAndForce {
    private double positionX;
    private double positionY;
    private double forceX;
    private double forceY;
}
