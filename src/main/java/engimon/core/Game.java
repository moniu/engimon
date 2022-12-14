package engimon.core;

import engimon.core.managers.ImagesManager;
import engimon.core.managers.SoundManager;
import engimon.graphics.RenderEngine;
import engimon.signals.SignalQueue;
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
    private Thread signalThread;
    private GameVariables gameVariables;
    private RenderEngine renderEngine;
    private Scene scene;
    private KeyHandler keyHandler;
    private ImagesManager imagesManager;
    private SoundManager soundManager;
    private SignalQueue signalQueue;

    private int lastFPS;

//    public static void main(String[] args) {
//        Game game = new Game(800, 600, "game");
//        game.start();
//    } TODO: Document starting the game instance

    protected Game(int width, int height, String name) {
        instance = this;
        gameWidth = width;
        gameHeight = height;

        imagesManager = new ImagesManager();
        soundManager = new SoundManager();
        signalQueue = new SignalQueue();

        gameVariables = new GameVariables();
        gameVariables.setWindowWidth(width);
        gameVariables.setWindowHeight(height);

        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);

        window = new Window(width, height, name, this);
        renderEngine = new RenderEngine();
        scene = new Scene();
        lastFPS = 0;
    }

    public synchronized void start() {
        tickThread = new Thread(new TickThread(), "GameTickThread");
        renderThread = new Thread(new RenderThread(), "GameRenderThread");
        signalThread = new Thread(new SignalThread(), "GameSignalThread");
        tickThread.start();
        renderThread.start();
        signalThread.start();
    }

    public void stop() {
        try {
            tickThread.join();
            renderThread.join();
            signalThread.join();
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
