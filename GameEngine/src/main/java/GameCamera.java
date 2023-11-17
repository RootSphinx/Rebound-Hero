import org.lwjgl.glfw.GLFW;
import org.sphinx.engine.*;

public class GameCamera extends Camera {
    public GameObject target;
    public Sprite backGround;
    Rigidbody rigidbody;
    public GameCamera(int width, int height) {
        super(width, height);
        setZoom(5.2f);
    }
    @Override
    public void start(){
        rigidbody = new Rigidbody(this);
        rigidbody.setGravity(false);
    }
    @Override
    public void update(){
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_O) == GLFW.GLFW_PRESS){
            this.addZoom(0.01f);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_P) == GLFW.GLFW_PRESS){
            this.addZoom(-0.01f);
        }
        rigidbody.addForce(target.getPosition().added(new Vector2D(0,400)).added(getPosition().reversed()).multiplied(1/12f));
        if (this.getPosition().x - getWidth()*getZoom()/2 < backGround.getGameObject()
                .getPosition().x - backGround.getTexture().getWidth()*backGround.getGameObject().transform.scale.x/2){
            this.transform.position.x =  (backGround.getGameObject()
                                .getPosition().x - backGround.getTexture().getWidth()*backGround.getGameObject().transform.scale.x/2 + getWidth()*getZoom()/2);
        }
        else if (this.getPosition().x + getWidth()*getZoom()/2 > backGround.getGameObject()
                .getPosition().x + backGround.getTexture().getWidth()*backGround.getGameObject().transform.scale.x/2){
            this.transform.position.x = (backGround.getGameObject()
                    .getPosition().x + backGround.getTexture().getWidth()*backGround.getGameObject().transform.scale.x/2 - getWidth()*getZoom()/2);
        }
        if (this.getPosition().y - getHeight()*getZoom()/2 < backGround.getGameObject()
                .getPosition().y - backGround.getTexture().getHeight()*backGround.getGameObject().transform.scale.y/2){
            this.transform.position.y = (backGround.getGameObject()
                    .getPosition().y - backGround.getTexture().getHeight()*backGround.getGameObject().transform.scale.y/2 + getHeight()*getZoom()/2);
        }
        else if (this.getPosition().y + getHeight()*getZoom()/2 > backGround.getGameObject()
                .getPosition().y + backGround.getTexture().getHeight()*backGround.getGameObject().transform.scale.y/2){
            this.transform.position.y = (backGround.getGameObject()
                    .getPosition().y + backGround.getTexture().getHeight()*backGround.getGameObject().transform.scale.y/2 - getHeight()*getZoom()/2);
        }
    }
}
