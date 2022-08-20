package engimon.core.objects;

import engimon.core.Scene;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameObject extends MetaObject {
    private MetaObject master;
    private Scene scene;
    private double localX;
    private double localY;

    public GameObject(Scene scene) {
        super();
        this.scene = scene;
        this.localX = 0.0;
        this.localY = 0.0;
        this.master = null;
    }

    public final MetaObject getMaster() {
        return this.master;
    }

    public final void setMaster(MetaObject master) {
        MetaObject oldMaster = getMaster();
        if (oldMaster == master) {
            return;
        }
        if (master == this) {
            return;
        }
        if (oldMaster != null) {
            oldMaster.removeChild(this);
        }
        this.master = master;
        master.addChild(this);
    }

    @Override
    public final double getGlobalX() {
        return getMaster().getGlobalX() + this.localX;
    }

    @Override
    public final double getGlobalY() {
        return getMaster().getGlobalY() + this.localY;
    }

    public void customTick(double deltaTime) {
    }

}
