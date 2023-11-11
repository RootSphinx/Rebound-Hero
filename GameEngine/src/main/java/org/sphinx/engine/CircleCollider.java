package org.sphinx.engine;

import org.sphinx.util.Debug;

import java.awt.*;
import java.util.List;

public class CircleCollider extends Collider{
    public float r;

    public CircleCollider(Collision collision, Rigidbody rigidbody, float r) {
        super(collision, rigidbody, "Circle");
        this.r = r;
        painter = new Painter(rigidbody.gameObject) {
            @Override
            public void draw() {
                if (!Debug.isIsDebugging()) return;
                this.type = UsageType.Item;
                setOutLineColor(color);
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
                    switch (collider.currentType) {
                        case "Circle" -> circleCollided((CircleCollider) collider);
                        case "Box" -> boxCollided((BoxCollider) collider);
                    }
                }
            }
        }
    }
    private void circleCollided(CircleCollider collider){
        Vector2D currentCollider = this.gameObject.getPosition().added(offset);
        Vector2D currentColliderVector = currentCollider.added(this.rigidbody.velocity);
        Vector2D colliderVector = collider.gameObject.getPosition().added(collider.offset).added(collider.rigidbody.velocity);
        float distance = currentColliderVector.getDistance(colliderVector);
        if (distance <= this.r + collider.r) {
            collided(collider);
            if (!collider.isTrigger&&!isTrigger) {
                if (currentCollider.added(new Vector2D(this.rigidbody.velocity.x,0)).getDistance(colliderVector) <=this.r + collider.r){
                    rigidbody.velocity.x = 0;
                }
                if (currentCollider.added(new Vector2D(0,this.rigidbody.velocity.y)).getDistance(colliderVector) <=this.r + collider.r){
                    rigidbody.velocity.y = 0;
                }
/*                System.out.println(this.rigidbody.velocity.getLength() + "\t\t" +this.rigidbody.velocity.lengthModify(distance-this.r)+"\t\t"+ (distance-this.r));
                this.rigidbody.velocity = this.rigidbody.velocity.lengthModify(distance-(this.r+ collider.r));*/
            }
            color = Color.red;
        }
        else{
            shakeOff(collider);
            color = Color.green;
        }
    }
    private void boxCollided(BoxCollider collider){
        Vector2D vector2D = this.gameObject.getPosition().added(offset).added(rigidbody.velocity);
        Vector2D vector2Dx = this.gameObject.getPosition().added(offset).added(new Vector2D(this.rigidbody.velocity.x,0));
        Vector2D vector2Dy = this.gameObject.getPosition().added(offset).added(new Vector2D(0,this.rigidbody.velocity.y));

        double a = PointToSegDist(vector2D.x, vector2D.y, collider.point.x - collider.width / 2, collider.point.y - collider.height / 2, collider.point.x + collider.width / 2, collider.point.y - collider.height / 2);
        double b = PointToSegDist(vector2D.x, vector2D.y, collider.point.x + collider.width / 2, collider.point.y - collider.height / 2, collider.point.x + collider.width / 2, collider.point.y + collider.height / 2);
        double c = PointToSegDist(vector2D.x, vector2D.y, collider.point.x + collider.width / 2, collider.point.y + collider.height / 2, collider.point.x - collider.width / 2, collider.point.y + collider.height / 2);
        double d = PointToSegDist(vector2D.x, vector2D.y, collider.point.x - collider.width / 2, collider.point.y + collider.height / 2, collider.point.x - collider.width / 2, collider.point.y - collider.height / 2);
        double a1 = PointToSegDist(vector2Dx.x, vector2Dx.y, collider.point.x - collider.width / 2, collider.point.y - collider.height / 2, collider.point.x + collider.width / 2, collider.point.y - collider.height / 2);
        double b1 = PointToSegDist(vector2Dx.x, vector2Dx.y, collider.point.x + collider.width / 2, collider.point.y - collider.height / 2, collider.point.x + collider.width / 2, collider.point.y + collider.height / 2);
        double c1 = PointToSegDist(vector2Dx.x, vector2Dx.y, collider.point.x + collider.width / 2, collider.point.y + collider.height / 2, collider.point.x - collider.width / 2, collider.point.y + collider.height / 2);
        double d1 = PointToSegDist(vector2Dx.x, vector2Dx.y, collider.point.x - collider.width / 2, collider.point.y + collider.height / 2, collider.point.x - collider.width / 2, collider.point.y - collider.height / 2);
        double a2 = PointToSegDist(vector2Dy.x, vector2Dy.y, collider.point.x - collider.width / 2, collider.point.y - collider.height / 2, collider.point.x + collider.width / 2, collider.point.y - collider.height / 2);
        double b2 = PointToSegDist(vector2Dy.x, vector2Dy.y, collider.point.x + collider.width / 2, collider.point.y - collider.height / 2, collider.point.x + collider.width / 2, collider.point.y + collider.height / 2);
        double c2 = PointToSegDist(vector2Dy.x, vector2Dy.y, collider.point.x + collider.width / 2, collider.point.y + collider.height / 2, collider.point.x - collider.width / 2, collider.point.y + collider.height / 2);
        double d2 = PointToSegDist(vector2Dy.x, vector2Dy.y, collider.point.x - collider.width / 2, collider.point.y + collider.height / 2, collider.point.x - collider.width / 2, collider.point.y - collider.height / 2);

        if (a < r || b < r || c < r || d < r){
            color = Color.red;
            if (!collider.isTrigger&&!isTrigger) {
                if (a1 < r || b1 < r || c1 < r || d1 < r) {
                    rigidbody.velocity.x = 0;
                }
                if (a2 < r || b2 < r || c2 < r || d2 < r) {
                    rigidbody.velocity.y = 0;
                }
            }
            collided(collider);
        }
        else {
            color = Color.green;
            shakeOff(collider);
        }
    }
    double PointToSegDist(double x, double y, double x1, double y1, double x2, double y2)
    {
        double cross = (x2 - x1) * (x - x1) + (y2 - y1) * (y - y1);
        if (cross <= 0) return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));

        double d2 = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
        if (cross >= d2) return Math.sqrt((x - x2) * (x - x2) + (y - y2) * (y - y2));

        double r = cross / d2;
        double px = x1 + (x2 - x1) * r;
        double py = y1 + (y2 - y1) * r;
        return Math.sqrt((x - px) * (x - px) + (y - py) * (y - py));
    }

}
