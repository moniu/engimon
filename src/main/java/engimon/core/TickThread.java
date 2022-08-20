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
            secondCounter += deltaTime;
            lastTime = now;
            frameCounter++;
            Game.instance.getScene().tick(deltaTime);
            if (secondCounter >= 1_000_000_000) {
                Game.instance.setLastFPS(frameCounter);
                secondCounter = 0;
                frameCounter = 0;
            }
        }
    }

}
