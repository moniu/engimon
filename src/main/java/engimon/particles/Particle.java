package engimon.particles;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Particle extends GameObject {
    private double lifeSpan;
    private double livedFor;

    public Particle(Scene scene) {
        super(scene);
    }

    public void kill() {
        removeAllChildren();
        getMaster().removeChild(this);
    }

}
