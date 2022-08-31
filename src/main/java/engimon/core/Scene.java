package engimon.core;

import engimon.core.objects.MetaObject;
import engimon.graphics.Camera;
import engimon.graphics.GraphicObject;
import engimon.physics.hitbox.Hitbox;
import engimon.physics.objects.PhysicalObject;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class Scene extends MetaObject {
    private final ArrayList<GraphicObject> graphicObjects;
    private final ArrayList<PhysicalObject> physicalObjects;
    private final ArrayList<Hitbox> hitboxes;
    protected Camera camera;
    protected Color backgroundColor;

    public Scene() {
        graphicObjects = new ArrayList<>();
        physicalObjects = new ArrayList<>();
        hitboxes = new ArrayList<>();
        camera = new Camera(0.0, 0.0, 1.0);
        backgroundColor = Color.WHITE;
    }

    public ArrayList<GraphicObject> getGraphicObjects() {
        return this.graphicObjects;
    }

    public ArrayList<PhysicalObject> getPhysicalObjects() {
        return this.physicalObjects;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public boolean registerGraphicObject(GraphicObject graphicObject) {
        synchronized (graphicObjects) {
            if (this.graphicObjects.contains(graphicObject)) {
                return false;
            }
            this.graphicObjects.add(graphicObject);
            return true;
        }
    }

    public boolean registerPhysicalObject(PhysicalObject physicalObject) {
        synchronized (physicalObjects) {
            if (physicalObjects.contains(physicalObject)) {
                return false;
            }
            physicalObjects.add(physicalObject);
            return true;
        }
    }

    public boolean registerHitbox(Hitbox hitbox) {
        synchronized (hitboxes) {
            if (hitboxes.contains(hitbox)) {
                return false;
            }
            hitboxes.add(hitbox);
            return true;
        }
    }

    public boolean unregisterPhysicalObject(PhysicalObject physicalObject) {
        synchronized (physicalObjects) {
            if (!physicalObjects.contains(physicalObject)) {
                return false;
            }
            physicalObjects.remove(physicalObject);
            return true;
        }
    }

    public boolean unregisterGraphicObject(GraphicObject graphicObject) {
        synchronized (physicalObjects) {
            if (!graphicObjects.contains(graphicObject)) {
                return false;
            }
            graphicObjects.remove(graphicObject);
            return true;
        }
    }

    public boolean unregisterHitbox(Hitbox hitbox) {
        synchronized (hitboxes) {
            if (!hitboxes.contains(hitbox)) {
                return false;
            }
            hitboxes.remove(hitbox);
            return true;
        }
    }

    public final MetaObject getMaster() {
        return this;
    }
}