package org.sphinx.engine;

import java.util.List;

public class Rigidbody extends Component{
    private float Mass = 1;
    private boolean isGravity = true;
    public static Vector2D Gravity = new Vector2D(0,-9.81f);
    public Vector2D velocity = new Vector2D();
    public Rigidbody(GameObject gameObject){
        super(gameObject, "Rigidbody");
    }
    public void addForce(Vector2D force) {
        velocity.add(force);
    }

    public void setMass(float mass) {
        Mass = (float) Math.max(1,Math.min(mass,1000));//10000
    }


    public void setGravity(boolean gravity) {
        isGravity = gravity;
    }
    private void collide(){

    }
    private void update(){
        if (!gameObject.isEnable())
            return;

        if (isGravity)
            addForce(Gravity);
        if (Math.abs(velocity.getLength()) > 0.001){
            velocity.add(velocity.multiplied(-1f/(Mass+1)));
            //System.out.println(velocity.multiplied(-Mass));
        }
        else {
            velocity = new Vector2D(0,0);
        }
        //System.out.println(this.velocity);
    }
    private void move(){
        gameObject.transform.position.add(velocity);
    }
    static void rigidbodyUpdate(){
        for (List<Component> componentList : components.get("Rigidbody").values()){
            ((Rigidbody) componentList.get(0)).update();
        }
    }
    static void rigidbodyMove(){
        for (List<Component> componentList : components.get("Rigidbody").values()){
            ((Rigidbody) componentList.get(0)).move();
        }
    }
}
