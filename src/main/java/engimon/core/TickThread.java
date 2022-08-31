package engimon.core;

import lombok.NoArgsConstructor;

@SuppressWarnings("InfiniteLoopStatement")
@NoArgsConstructor
public class TickThread implements Runnable {

    @Override
    public synchronized void run() {
        long now;
        long lastTime = System.nanoTime();
        double deltaTime;
        double secondCounter = 0.0;
        int frameCounter = 0;
        while (true) {
            now = System.nanoTime();
            deltaTime = now - lastTime;
            deltaTime *= Game.instance.getGameVariables().getDeltaScale();
            secondCounter += deltaTime;
            lastTime = now;
            frameCounter++;
            Game.instance.getScene().tick(deltaTime / 1_000_000_000);
            if (secondCounter >= 1_000_000_000) {
                Game.instance.setLastFPS((int) (frameCounter * Game.instance.getGameVariables().getDeltaScale()));
                secondCounter = 0;
                frameCounter = 0;
            }
        }
    }

}
