package engimon.core;

import lombok.NoArgsConstructor;

@SuppressWarnings("InfiniteLoopStatement")
@NoArgsConstructor
public class RenderThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Game.instance.getRenderEngine().render();
            }
            catch (Exception e) {
                //ignore
            }
        }
    }

}
