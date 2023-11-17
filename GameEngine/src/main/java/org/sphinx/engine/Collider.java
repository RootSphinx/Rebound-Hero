package org.sphinx.engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Collider extends Component {
    protected Rigidbody rigidbody;
    protected Collision collision;
    public boolean isTrigger = false;
    protected String currentType;
    protected Vector2D offset = new Vector2D();
    protected List<Collider> colliderInTriggerList = new ArrayList<>();
    public Painter painter;
    Color color = Color.GREEN;

    protected Collider(Collision collision,Rigidbody rigidbody,String currentType) {
        super(rigidbody.gameObject, Collider.class);
        this.rigidbody = rigidbody;
        this.collision = collision;
        this.currentType = currentType;
    }
    protected abstract void colliderUpdate();

    static void update(){
        for (List<Component> component : components.get(Collider.class).values()) {
            for (Component component1 : component) {
                if (component1.gameObject.isEnable()) {
                    ((Collider) component1).colliderUpdate();
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
        if (colliderInTriggerList.contains(collider)){
            colliderInTriggerList.remove(collider);
            collision.onTriggerExit(collider);
        }
    }
    public void setOffset(Vector2D offset) {
        this.offset = offset;
    }
}



