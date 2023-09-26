package org.sphinx.engine;

import java.awt.*;
import java.util.List;

public class CircleCollider extends Collider{
    public float r;

    public CircleCollider(Collision collision, Rigidbody rigidbody, float r) {
        super(collision, rigidbody, "Circle");
        this.r = r;
        drawer = new Drawer(rigidbody.gameObject) {
            @Override
            public void draw() {
                this.type = UsageType.Item;
                setOutLineColor(Color.GREEN);
                this.setLayout(14);
                drawCircleOutLine(rigidbody.gameObject.getPosition().x+ offset.x,rigidbody.gameObject.getPosition().y+ offset.y,r);
            }
        };
    }
    @Override
    protected void update() {
        for (List<Component> component : components.get("Collider").values()) {
            for (Component component1 : component) {
                Collider collider = (Collider) component1;
                if (collider.collision != this.collision && collider.gameObject.isEnable()){
                    switch (collider.currentType){
                        case "Circle" ->circleCollided((CircleCollider) collider);
                        case "Box" ->{}
                    }
                }
            }
        }
    }
    void circleCollided(CircleCollider collider){
        Vector2D currentColliderVector = this.gameObject.getPosition().added(offset).added(this.rigidbody.velocity);
        Vector2D colliderVector = collider.gameObject.getPosition().added(collider.offset).added(collider.rigidbody.velocity);
        float distance = currentColliderVector.getDistance(colliderVector);
        if (distance <= this.r + collider.r) {
            collided(collider);
            this.rigidbody.velocity = new Vector2D();
        }
        else{
            shakeOff(collider);
        }
    }
}
