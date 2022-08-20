package engimon.signals;

import lombok.Getter;

public abstract class Signal {
    @Getter
    final private long emissionTime;

    public Signal() {
        emissionTime = System.nanoTime();
    }
}
