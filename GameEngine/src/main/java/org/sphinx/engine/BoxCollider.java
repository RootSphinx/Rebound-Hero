package org.sphinx.engine;

import java.awt.*;
import java.util.List;

public class BoxCollider extends Collider{
    Vector2D point;
    float width;
    float height;
    public BoxCollider(Collision collision, Rigidbody rigidbody, Vector2D point, float width, float height) {
        super(collision, rigidbody, "Box");
        this.point = point;
        this.width = width;
        this.height = height;
        painter = new Painter(rigidbody.gameObject) {
            @Override
            public void draw() {
                this.type = UsageType.Item;
                setOutLineColor(Color.GREEN);
                this.setLayout(14);
                drawQuadsOutLine(point.x - width / 2,point.y - height / 2,width, height);
            }
        };
    }

    @Override
    protected void update() {
        for (List<Component> component : components.get("Collider").values()) {
            for (Component component1 : component) {
                Collider collider = (Collider) component1;
                if (collider.collision != this.collision && collider.gameObject.isEnable()){
                    switch (collider.currentType) {
                        case "Circle" -> circleCollided((CircleCollider) collider);
                        case "Box" -> boxCollided((BoxCollider) collider);
                    }
                }
            }
        }
    }

    private void boxCollided(BoxCollider collider) {

    }

    private void circleCollided(CircleCollider collider) {

    }
}
