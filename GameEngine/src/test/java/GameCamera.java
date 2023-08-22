import org.joml.Vector2d;
import org.lwjgl.glfw.GLFW;
import org.sphinx.sphinxengine.engine.Camera;
import org.sphinx.sphinxengine.engine.Vector2D;
import org.sphinx.sphinxengine.engine.WindowController;

import java.util.Vector;

public class GameCamera extends Camera {
    public GameCamera(int width, int height) {
        super(width, height);
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
    }
}
