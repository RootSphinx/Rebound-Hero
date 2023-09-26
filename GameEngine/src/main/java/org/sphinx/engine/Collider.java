package org.sphinx.engine;

import java.util.ArrayList;
import java.util.List;

public abstract class Collider extends Component {
    protected Rigidbody rigidbody;
    protected Collision collision;
    protected boolean isTrigger = false;
    protected String currentType;
    protected Vector2D offset = new Vector2D();
    protected List<Collider> colliderInTriggerList = new ArrayList<>();
    public Drawer drawer;

    protected Collider(Collision collision,Rigidbody rigidbody,String currentType) {
        super(rigidbody.gameObject, "Collider");
        this.rigidbody = rigidbody;
        this.collision = collision;
        this.currentType = currentType;
    }
    protected abstract void update();

    static void colliderUpdate(){
        for (List<Component> component : components.get("Collider").values()) {
            for (Component component1 : component) {
                if (component1.gameObject.isEnable()) {
                    ((Collider) component1).update();
                    for (Collider collider : ((Collider) component1).colliderInTriggerList) {
                        if (collider.gameObject.isEnable()) {
                            ((Collider) component1).collision.onTriggerUpdate(collider);
                        }
                    }
                }
            }
        }
    }
    protected void collided(Collider collider){
        if (isTrigger && !colliderInTriggerList.contains(collider)) {
            collision.onTriggerEnter(collider);
            colliderInTriggerList.add(collider);
        }
        else {
            collision.onCollisionEnter(collider);
        }
    }
    protected void shakeOff(Collider collider){
        colliderInTriggerList.remove(collider);
        collision.onTriggerExit(collider);
    }
    public void setOffset(Vector2D offset) {
        this.offset = offset;
    }
}



