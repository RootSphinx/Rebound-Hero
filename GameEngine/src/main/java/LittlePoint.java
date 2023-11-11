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
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_UP)==GLFW.GLFW_PRESS){
            littlePoints.get(currentID).move(new Vector2D(0,10));
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_DOWN)==GLFW.GLFW_PRESS){
            littlePoints.get(currentID).move(new Vector2D(0,-10));
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_LEFT)==GLFW.GLFW_PRESS){
            littlePoints.get(currentID).move(new Vector2D(-10,0));
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_RIGHT)==GLFW.GLFW_PRESS){
            littlePoints.get(currentID).move(new Vector2D(10,0));
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_F5)==GLFW.GLFW_PRESS) {
            currentID = Math.max(currentID-1,0);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_F6)==GLFW.GLFW_PRESS) {
            currentID = Math.min(currentID+1,littlePoints.size()-1);
        }
        System.out.println(currentID +" "+ littlePoints.get(currentID).getPosition());
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
