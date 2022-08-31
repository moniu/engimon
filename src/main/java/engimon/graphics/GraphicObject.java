package engimon.graphics;

import engimon.core.Scene;
import engimon.core.objects.GameObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GraphicObject extends GameObject implements Comparable<GraphicObject> {
    protected int layer;
    protected boolean relative;

    public GraphicObject(Scene scene) {
        super(scene);
        this.layer = 0;
    }

    public abstract void render(RelativeGraphics relativeGraphics);

    @Override
    public int compareTo(GraphicObject o) {
        return Integer.compare(layer, o.layer);
    }
}
