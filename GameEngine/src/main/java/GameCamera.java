import org.lwjgl.glfw.GLFW;
import org.sphinx.engine.Camera;
import org.sphinx.engine.GameObject;
import org.sphinx.engine.WindowController;

public class GameCamera extends Camera {
    public GameObject target;
    public GameCamera(int width, int height) {
        super(width, height);
        setZoom(5.8f);
    }
    @Override
    public void update(){
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_UP) == GLFW.GLFW_PRESS){
            this.transform.position.y+=3;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS){
            this.transform.position.x-=3;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS){
            this.transform.position.x+=3;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_DOWN) == GLFW.GLFW_PRESS){
            this.transform.position.y-=3;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_O) == GLFW.GLFW_PRESS){
            this.addZoom(0.01f);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_P) == GLFW.GLFW_PRESS){
            this.addZoom(-0.01f);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_K) == GLFW.GLFW_PRESS){
            this.addRotation(0.01f);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_L) == GLFW.GLFW_PRESS){
            this.addRotation(-0.01f);
        }
/*      System.out.println("Camera.update()");
        System.out.println("    Camera.transform.position = "+transform.position);*/
        this.transform.position = target.getPosition();

    }
}
