package engimon.graphics;

import engimon.core.Scene;

public class CameraPosDisplay extends GraphicObject {
    public CameraPosDisplay(Scene scene) {
        super(scene);
    }

    @Override
    public void render(RelativeGraphics relativeGraphics) {
        Camera camera = getScene().getCamera();

        relativeGraphics.getGraphics().drawString(Double.toString(camera.getX()), 50, 50);
        relativeGraphics.getGraphics().drawString(Double.toString(camera.getY()), 50, 100);

    }
}
