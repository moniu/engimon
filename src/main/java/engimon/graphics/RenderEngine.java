package engimon.graphics;

import engimon.core.Game;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Collections;

@Setter
@Getter
public class RenderEngine {
    private final int width;
    private final int height;

    public RenderEngine() {
        Game.instance.createBufferStrategy(2);

        width = Game.instance.getGameVariables().getWindowWidth();
        height = Game.instance.getGameVariables().getWindowHeight();
    }

    public synchronized void render() {
        BufferStrategy bufferStrategy = Game.instance.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Game.instance.getScene().getBackgroundColor());
        graphics.fillRect(0, 0, width, height);
        RelativeGraphics relativeGraphics = new RelativeGraphics(graphics, Game.instance.getScene());

        //Render all objects in current scene
        ArrayList<GraphicObject> graphicObjects = Game.instance.getScene().getGraphicObjects();
        Collections.sort(graphicObjects);
        for (GraphicObject go : graphicObjects) {
            go.render(relativeGraphics);
        }


        Font font = new Font("Monospace", Font.PLAIN, 12);
        graphics.setFont(font);
        graphics.setColor(Color.RED);
        String fps = Integer.toString(Game.instance.getLastFPS());
        graphics.drawChars(fps.toCharArray(), 0, fps.length(), 20, 20);
        graphics.dispose();
        bufferStrategy.show();
    }
}
