import org.lwjgl.glfw.GLFW;
import org.sphinx.engine.*;
import org.sphinx.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class LittlePoint extends GameObject implements Collision {
    Rigidbody rigidbody;
    CircleCollider collider;
    //public static Utils.ObjectPool objectPool = new Utils.ObjectPool(LittlePoint.class,10);
    public static List<LittlePoint> littlePoints = new ArrayList<>();
    static LittlePoint littlePoint;
    int id;
    static int currentID = 0;
    LittlePoint(int id){
        this.id = id;
    }
    @Override
    public void start() {
        rigidbody = new Rigidbody(this);
        collider = new CircleCollider(this,rigidbody,3);
        rigidbody.setGravity(false);
    }

    @Override
    public void update() {


    }

    public static void updateA(){
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_K)== GLFW.GLFW_PRESS) {
            System.out.println("K");

        }
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
    public void move(Vector2D vector){
        this.transform.position.add(vector);
    }
    @Override
    public void onTriggerEnter(Collider collider) {

    }

    @Override
    public void onTriggerUpdate(Collider collider) {

    }

    @Override
    public void onTriggerExit(Collider collider) {

    }

    @Override
    public void onCollisionEnter(Collider collider) {

    }
}
