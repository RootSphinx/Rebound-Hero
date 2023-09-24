package org.sphinx.engine;

public class Rigidbody extends Component{
    private float Mass = 0.01f;
    private float frag = 0.01f;
    public Vector2D velocity = new Vector2D();
    private Vector2D force = new Vector2D();
    Rigidbody(GameObject gameObject){
        super(gameObject, "Rigidbody");
    }
    public void addForce(Vector2D force) {
        this.force = force;
    }

    public void setMass(float mass) {
        Mass = (float) Math.max(0.01,mass);
    }

    public void setFrag(float frag) {
        this.frag = (float) Math.max(0.01,frag);
    }

    private void update(){
        boolean b = this.force.getLength() < force.getLength();
    }
}
