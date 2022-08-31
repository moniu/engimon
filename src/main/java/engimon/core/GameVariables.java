package engimon.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameVariables {
    private int ticksPerSecond = 60;
    private int windowWidth = 1200;
    private int windowHeight = 800;
    /**
     * 1 - nearest
     * 2 - bipolar
     * 3 - tripolar
     */
    private int interpolationType = 1;
    private double deltaScale = 1.0;

}
