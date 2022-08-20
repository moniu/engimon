package engimon.core;

import engimon.core.managers.ImagesManager;
import engimon.core.managers.SoundManager;
import engimon.graphics.RenderEngine;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Game extends Canvas {

    public static Game instance;
    public int gameHeight;
    private int gameWidth;
    private Window window;
    private Thread tickThread;
    private Thread renderThread;
    private GameVariables gameVariables;
    private RenderEngine renderEngine;
    private Scene scene;
    private KeyHandler keyHandler;
    private ImagesManager imagesManager;
    private SoundManager soundManager;

    private int lastFPS;

//    public static void main(String[] args) {
//        Game game = new Game(800, 600, "game");
//        game.start();
//    } TODO: Document starting the game instance

    protected Game(int width, int height, String name) {
        gameWidth = width;
        gameHeight = height;

        imagesManager = new ImagesManager();
        soundManager = new SoundManager();

        gameVariables = new GameVariables();
        gameVariables.setWindowWidth(width);
        gameVariables.setWindowHeight(height);

        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);

        window = new Window(width, height, name, this);
        renderEngine = new RenderEngine(this);
        scene = new Scene(this);
        lastFPS = 0;
    }

    void initialize(int width, int height, String name) {
        instance = new Game(width, height, name);
    }

    public synchronized void start() {
        tickThread = new Thread(new TickThread(this), "GameTickThread");
        renderThread = new Thread(new RenderThread(this), "GameRenderThread");
        tickThread.start();
        renderThread.start();
    }

    public void stop() {
        try {
            tickThread.join();
            renderThread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        //stop();
        System.exit(0);
    }

}
