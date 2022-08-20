package engimon.graphics;

import engimon.core.Scene;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Animation extends GraphicObject {

    private final int frames;
    private ArrayList<ImageObject> images;
    private double frameTime;
    private double frameLength;
    private int currentFrame;
    private double sizeX;
    private double sizeY;

    public Animation(Scene scene, double frameLength, String[] filenames) {
        super(scene);
        images = new ArrayList<>();
        frameTime = 0;
        this.frameLength = frameLength * 1_000_000_000;
        this.frames = filenames.length;

        ImageObject imageObject;
        for (String f : filenames) {
            imageObject = new ImageObject(scene, f);
            imageObject.setSizeX(sizeX);
            imageObject.setSizeY(sizeY);
            imageObject.setLayer(layer);
            imageObject.setRelative(relative);

            images.add(imageObject);
            addChild(imageObject);
        }
    }

    @Override
    public synchronized void render(RelativeGraphics relativeGraphics) {
        ImageObject imageObject = images.get(currentFrame);
        imageObject.render(relativeGraphics);

    }

    @Override
    public synchronized void customTick(double deltaTime) {
        frameTime += deltaTime;
        while (frameTime > frameLength) {
            currentFrame++;
            if (currentFrame >= frames) {
                currentFrame = 0;
            }
            frameTime -= frameLength;
        }
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
        for (ImageObject io : images) {
            io.setSizeX(sizeX);
        }
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
        for (ImageObject io : images) {
            io.setSizeY(sizeY);
        }
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
        for (ImageObject io : images) {
            io.setRelative(relative);
        }
    }

}
