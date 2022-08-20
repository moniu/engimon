package engimon.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameVariables {
    private int ticksPerSecond;
    private int windowWidth;
    private int windowHeight;

    public GameVariables() {
        ticksPerSecond = 60;
    }

}
