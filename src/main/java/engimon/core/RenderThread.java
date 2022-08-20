package engimon.core;

@SuppressWarnings("InfiniteLoopStatement")
public class RenderThread implements Runnable {
    private final Game game;

    public RenderThread(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (true) {
            try {
                game.getRenderEngine().render();
            }
            catch (Exception e) {
                //ignore
            }
        }
    }

}
