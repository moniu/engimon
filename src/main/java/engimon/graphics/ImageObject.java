package engimon.graphics;

import engimon.core.Game;
import engimon.core.Scene;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class ImageObject extends GraphicObject {
    private final BufferedImage image;
    private double sizeX;
    private double sizeY;

    public ImageObject(Scene scene, String imageName) {
        super(scene);
        image = Game.instance.getImagesManager().loadImage(imageName);
    }

    @Override
    public void render(RelativeGraphics relativeGraphics) {
        double angle = isRelative() ? getGlobalAngle() : getLocalAngle();
        Image img = image;
        double rotatedSizeX = getSizeX();
        double rotatedSizeY = getSizeY();
        if (angle > 0.005 || getSizeX() < 3000) {
            double locationX = image.getWidth() / 2.0;
            double locationY = image.getHeight() / 2.0;

            double[] rotatedSizes = getRotatedSize(angle, getSizeX(), getSizeY());
            rotatedSizeX = rotatedSizes[0];
            rotatedSizeY = rotatedSizes[1];

            AffineTransform scaleInstance = AffineTransform.getScaleInstance(getSizeX() / image.getWidth(),
                                                                             getSizeY() / image.getHeight()
            );
            AffineTransform rotateInstance = AffineTransform.getRotateInstance(angle, locationX, locationY);
            scaleInstance.concatenate(rotateInstance);
            AffineTransformOp operation = new AffineTransformOp(scaleInstance,
                                                                Game.instance.getGameVariables().getInterpolationType()
            );
            img = operation.filter(image, null);

        }

        if (this.isRelative()) {
            relativeGraphics.drawImage(
                    img,
                    this.getGlobalX() - rotatedSizeX / 2,
                    this.getGlobalY() - rotatedSizeY / 2,
                    rotatedSizeX, rotatedSizeY, null
            );
        }
        else {
            relativeGraphics.getGraphics().drawImage(
                    img,
                    (int) (this.getGlobalX()),
                    (int) (this.getGlobalY()),
                    (int) rotatedSizeX, (int) rotatedSizeX, null
            );
        }
    }

    private double[] getRotatedSize(double angle, double width, double height) {
        double[] sizes = new double[2];
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        sizes[0] = Math.abs(cos * width) + Math.abs(sin * height);
        sizes[1] = Math.abs(sin * width) + Math.abs(cos * height);

        return sizes;
    }

}
