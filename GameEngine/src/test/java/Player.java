import org.lwjgl.glfw.GLFW;
import org.sphinx.sphinxengine.engine.*;

public class Player extends GameObject {
    Sprite sprite;
    Animator animator;
    @Override
    public void start(){

        //System.out.println("player.start()");
        tag = "Player";
        name = "Player";
        transform.scale = 3;
        sprite = new Sprite(this, "/图片包/image0.png", Sprite.Type.Item);
        sprite.setLayout(2);
        animator = new Animator(this,sprite);
        animator.createAction("left", 0.2f, Animator.Type.instant,Texture.textureSplite("/sprite.png",4,4,4,7));
        animator.createAction("right", 0.2f, Animator.Type.instant,Texture.textureSplite("/sprite.png",4,4,8,11));
        animator.createAction("back", 0.2f, Animator.Type.instant,Texture.textureSplite("/sprite.png",4,4,12,15));
        animator.createAction("front", 0.2f, Animator.Type.instant,Texture.textureSplite("/sprite.png",4,4,0,3));
    }

    @Override
    public void update(){
/*        System.out.println("player.update()");
        System.out.println("    player.transform.position = "+transform.position);*/
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
            animator.setAction("back");
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_A)==GLFW.GLFW_PRESS){
            transform.position.x--;
            animator.setAction("left");
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_S)==GLFW.GLFW_PRESS){
            transform.position.y--;
            animator.setAction("front");
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_D)==GLFW.GLFW_PRESS){
            transform.position.x++;
            animator.setAction("right");
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_Q)==GLFW.GLFW_PRESS){
            addRotation(0.01f);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_E)==GLFW.GLFW_PRESS){
            addRotation(-0.01f);
        }
    }
}
