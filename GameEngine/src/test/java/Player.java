import org.lwjgl.glfw.GLFW;
import org.sphinx.sphinxengine.engine.GameObject;
import org.sphinx.sphinxengine.engine.Sprite;
import org.sphinx.sphinxengine.engine.Texture;
import org.sphinx.sphinxengine.engine.WindowController;

public class Player extends GameObject {
    Sprite sprite;
    @Override
    public void start(){

        //System.out.println("player.start()");
        tag = "Player";
        name = "Player";
        transform.scale = 3;
        sprite = new Sprite(this, "/图片包/image0.png", Sprite.Type.Item);
        sprite.setLayout(2);
    }

    @Override
    public void update(){
        //System.out.println("player.update()");
        move();
    }

    @Override
    public void enable() {
        System.out.println("player.enable()");
    }

    @Override
    public void disable() {

    }
    void move(){
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_W)==GLFW.GLFW_PRESS){
            transform.position.y++;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_A)==GLFW.GLFW_PRESS){
            transform.position.x--;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_S)==GLFW.GLFW_PRESS){
            transform.position.y--;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_D)==GLFW.GLFW_PRESS){
            transform.position.x++;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_Q)==GLFW.GLFW_PRESS){
            addRotation(0.01f);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_E)==GLFW.GLFW_PRESS){
            addRotation(-0.01f);
        }
    }
}
