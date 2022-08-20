package engimon.particles;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Random;

@Getter
@Setter
public abstract class ParticleEmmiter<ParticleType extends Particle> extends GameObject {
    private final ArrayList<ParticleType> particles;
    private final Random random;
    private int maxParticles;

    public ParticleEmmiter(Scene scene) {
        super(scene);
        random = new Random();
        particles = new ArrayList<>();
    }


    // @Override
    // public void customTick(double deltaTime) {
    //     Iterator<ParticleType> pIterator = particles.iterator();
    //     ParticleType p;
    //     while(pIterator.hasNext()) {
    //         p = pIterator.next();
    //         double livedFor = p.getLivedFor();
    //         double lifeSpan = p.getLifeSpan();
    //         double newLivedFor = livedFor+deltaTime/1_000_000_000;
    //         if (newLivedFor > lifeSpan) {
    //             p.kill();
    //             pIterator.remove();
    //             continue;
    //         }
    //         p.setLivedFor(newLivedFor);
    //     }

    //     double chance = deltaTime/1_000_000_000 * (maxParticles-particles.size()) / maxParticles;


    //     if (random.nextDouble() < chance) {
    //         ParticleType particle = new ParticleType(getScene());
    //         particles.add(particle);
    //         addChild(particle);

    //     }

    // }


}
