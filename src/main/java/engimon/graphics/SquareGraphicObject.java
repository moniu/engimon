package engimon.graphics;

import engimon.core.Scene;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class SquareGraphicObject extends GraphicObject {
    private Color color;
    private double sizeX;
    private double sizeY;


    public SquareGraphicObject(Scene scene) {
        super(scene);
        color = Color.BLACK;
        sizeX = 0.0;
        sizeY = 0.0;
        this.setRelative(true);
    }

    @Override
    public synchronized void render(RelativeGraphics relativeGraphics) {
        relativeGraphics.setColor(color);
        if (this.isRelative()) {
            relativeGraphics.fillRect(this.getGlobalX() - sizeX / 2, this.getGlobalY() - sizeY / 2, sizeX, sizeY);
        }
        else {
            relativeGraphics.getGraphics().fillRect((int) this.getGlobalX(), (int) this.getGlobalY(), (int) sizeX,
                                                    (int) sizeY
            );
        }
    }

}
