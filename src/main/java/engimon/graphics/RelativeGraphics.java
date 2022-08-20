package engimon.graphics;

import engimon.core.Game;
import engimon.core.Scene;

import java.awt.*;
import java.awt.image.ImageObserver;

public class RelativeGraphics {
    private final Graphics graphics;
    private final Scene scene;

    public RelativeGraphics(Graphics graphics, Scene scene) {
        this.graphics = graphics;
        this.scene = scene;
    }

    public Color getColor() {
        return graphics.getColor();
    }

    public void setColor(Color color) {
        graphics.setColor(color);

    }

    public Font getFont() {
        return graphics.getFont();
    }

    public void setFont(Font font) {
        graphics.setFont(font);

    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        int ix1 = translateX(x1);
        int iy1 = translateY(y1);
        int ix2 = translateX(x2);
        int iy2 = translateY(y2);
        graphics.drawLine(ix1, iy1, ix2, iy2);
    }

    public void fillRect(double x, double y, double width, double height) {
        int ix = translateX(x);
        int iy = translateY(y);
        int translatedWidth = translateSize(width);
        int translatedHeight = translateSize(height);

        graphics.fillRect(ix, iy, translatedWidth, translatedHeight);

    }

    public void drawRoundRect(double x, double y, double width, double height, double arcWidth, double arcHeight) {
        int ix = translateX(x);
        int iy = translateY(y);
        int translatedWidth = translateSize(width);
        int translatedHeight = translateSize(height);
        int translatedArcWidth = translateSize(arcWidth);
        int translatedArcHeight = translateSize(arcHeight);

        graphics.drawRoundRect(
                ix,
                iy,
                translatedWidth,
                translatedHeight,
                translatedArcWidth,
                translatedArcHeight
        );

    }

    public void fillRoundRect(double x, double y, double width, double height, double arcWidth, double arcHeight) {
        int ix = translateX(x);
        int iy = translateY(y);
        int translatedWidth = translateSize(width);
        int translatedHeight = translateSize(height);
        int translatedArcWidth = translateSize(arcWidth);
        int translatedArcHeight = translateSize(arcHeight);

        graphics.fillRoundRect(ix, iy, translatedWidth, translatedHeight, translatedArcWidth, translatedArcHeight);

    }

    public void drawOval(double x, double y, double width, double height) {
        int ix = translateX(x);
        int iy = translateY(y);
        int translatedWidth = translateSize(width);
        int translatedHeight = translateSize(height);

        graphics.drawOval(ix, iy, translatedWidth, translatedHeight);

    }

    public void fillOval(double x, double y, double width, double height) {
        int ix = translateX(x);
        int iy = translateY(y);
        int translatedWidth = translateSize(width);
        int translatedHeight = translateSize(height);

        graphics.fillOval(ix, iy, translatedWidth, translatedHeight);

    }

    public void drawArc(double x, double y, double width, double height, int startAngle, int arcAngle) {
        int ix = translateX(x);
        int iy = translateY(y);
        int translatedWidth = translateSize(width);
        int translatedHeight = translateSize(height);


        graphics.drawArc(ix, iy, translatedWidth, translatedHeight, startAngle, arcAngle);

    }

    public void fillArc(double x, double y, double width, double height, int startAngle, int arcAngle) {
        int ix = translateX(x);
        int iy = translateY(y);
        int translatedWidth = translateSize(width);
        int translatedHeight = translateSize(height);

        graphics.fillArc(ix, iy, translatedWidth, translatedHeight, startAngle, arcAngle);

    }

    public void drawPolyline(double[] xPoints, double[] yPoints, int nPoints) {
        int[] ixPoints = new int[nPoints];
        int[] iyPoints = new int[nPoints];

        for (int i = 0; i < nPoints; i++) {
            int ix = (int) xPoints[i];
            int iy = (int) yPoints[i];
            ixPoints[i] = translateX(ix);
            iyPoints[i] = translateY(iy);
        }
        graphics.drawPolyline(ixPoints, iyPoints, nPoints);

    }

    public void drawPolygon(double[] xPoints, double[] yPoints, int nPoints) {
        int[] ixPoints = new int[nPoints];
        int[] iyPoints = new int[nPoints];

        for (int i = 0; i < nPoints; i++) {
            int ix = (int) xPoints[i];
            int iy = (int) yPoints[i];
            ixPoints[i] = translateX(ix);
            iyPoints[i] = translateY(iy);
        }
        graphics.drawPolygon(ixPoints, iyPoints, nPoints);

    }

    public void fillPolygon(double[] xPoints, double[] yPoints, int nPoints) {
        int[] ixPoints = new int[nPoints];
        int[] iyPoints = new int[nPoints];

        for (int i = 0; i < nPoints; i++) {
            int ix = (int) xPoints[i];
            int iy = (int) yPoints[i];
            ixPoints[i] = translateX(ix);
            iyPoints[i] = translateY(iy);
        }
        graphics.fillPolygon(ixPoints, iyPoints, nPoints);

    }

    public void drawString(String str, double x, double y) {
        int ix = translateX(x);
        int iy = translateY(y);

        graphics.drawString(str, ix, iy);

    }

    public boolean drawImage(Image img, double x, double y, ImageObserver observer) {
        int ix = translateX(x);
        int iy = translateY(y);
        return graphics.drawImage(img, ix, iy, observer);
    }

    public void drawImage(Image img, double x, double y, double width, double height, ImageObserver observer) {
        int ix = translateX(x);
        int iy = translateY(y);
        int translatedWidth = translateSize(width);
        int translatedHeight = translateSize(height);
        graphics.drawImage(img, ix, iy, translatedWidth, translatedHeight, observer);
    }

    private int translateX(double x) {
        Camera camera = scene.getCamera();
        double relativeX = x - camera.getX();
        relativeX += Game.instance.getGameWidth() / 2.0 / camera.getZoom();
        return (int) relativeX;
    }

    private int translateY(double y) {
        Camera camera = scene.getCamera();
        return (int) (y - camera.getY() + Game.instance.getGameHeight() / 2.0 / camera.getZoom());

    }

    private int translateSize(double a) {
        Camera camera = scene.getCamera();
        return (int) (a / camera.getZoom());
    }

    public Graphics getGraphics() {
        return this.graphics;
    }


}
