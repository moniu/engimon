package engimon.graphics;

import engimon.core.Scene;
import engimon.core.objects.GameObject;

public abstract class GraphicObject extends GameObject implements Comparable<GraphicObject> {
    protected int layer;
    protected boolean relative;

    public GraphicObject(Scene scene) {
        super(scene);
        this.layer = 0;
    }

    public abstract void render(RelativeGraphics relativeGraphics);

    public final int getLayer() {
        return this.layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public final boolean getRelative() {
        return this.relative;
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
    }

    @Override
    public int compareTo(GraphicObject o) {
        return Integer.compare(layer, o.layer);
    }
}
