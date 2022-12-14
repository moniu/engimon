package engimon.graphics;

import engimon.core.Scene;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class AnimatedObject extends GraphicObject {
    private final HashMap<String, Animation> animations;
    private String currentAnimation;

    private double sizeX;
    private double sizeY;

    public AnimatedObject(Scene scene, HashMap<String, Animation> animations) {
        super(scene);

        for (Animation a : animations.values()) {
            addChild(a);
        }
        this.animations = animations;
    }

    public void render(RelativeGraphics relativeGraphics) {
        Animation animation = animations.get(currentAnimation);
        animation.render(relativeGraphics);
    }

    public void changeAnimation(String animation) {
        this.currentAnimation = animation;
    }

    public void setSizeX(double sizeX) {
        this.sizeX = sizeX;
        for (Animation a : animations.values()) {
            a.setSizeX(sizeX);
        }
    }

    public void setSizeY(double sizeY) {
        this.sizeY = sizeY;
        for (Animation a : animations.values()) {
            a.setSizeY(sizeY);
        }
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
        for (Animation a : animations.values()) {
            a.setRelative(relative);
        }
    }

    @Override
    public void setLayer(int layer) {
        this.layer = layer;
        for (Animation a : animations.values()) {
            a.setLayer(layer);
        }
    }

}
