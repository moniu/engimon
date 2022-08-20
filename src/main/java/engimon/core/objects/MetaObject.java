package engimon.core.objects;

import engimon.core.Game;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class MetaObject {
    private ArrayList<GameObject> childrenGameObjects;
    private Game game;

    public MetaObject(Game game) {
        this.game = game;
        childrenGameObjects = new ArrayList<>();
    }

    public double getGlobalX() {
        return 0.0;
    }

    public double getGlobalY() {
        return 0.0;
    }

    public final void addChild(GameObject child) {
        this.childrenGameObjects.add(child);
        child.setMaster(this);
    }

    public final void removeChild(GameObject child) {
        child.removeAllChildren();
        this.childrenGameObjects.remove(child);
    }

    public final void removeAllChildren() {
        this.childrenGameObjects.clear();
    }

    public final void tick(double deltaTime) {
        this.customTick(deltaTime);
        for (GameObject childrenGameObject : childrenGameObjects) {
            childrenGameObject.tick(deltaTime);
        }
    }

    public void customTick(double deltaTime) {
    }
}
