import org.lwjgl.glfw.GLFW;
import org.sphinx.engine.*;
import org.sphinx.util.Debug;
import org.sphinx.util.Utils;

import java.util.Objects;

public class Player extends GameObject implements Collision{

    Sprite sprite;
    Animator animator;
    Rigidbody rigidbody;
    Utils.ObjectPool objectPool = new Utils.ObjectPool(BackGround2.class,10);
    CircleCollider collider;
    CircleCollider collider1;
    Boolean isJumped = false , isMoving = false , isJumping = false, isOnFloor = false,isAttacking = false;
    float speed = 5;
    @Override
    public void start(){
        //System.out.println("player.start()");
        tag = "Player";
        name = "Player";
        transform.scale = new Vector2D(12,12);

        SplitTexture splitTexture = new SplitTexture("/Image/Hero/Warrior_Sheet-Effect.png", 6, 17);
        System.out.println(splitTexture.getTextureList().size());
        sprite = new Sprite(this, splitTexture.getTexture(0), Render.UsageType.Item);
        sprite.setLayout(2);

        rigidbody = new Rigidbody(this);
        rigidbody.setGravity(false);
        rigidbody.setMass(10);
        collider = new CircleCollider(this,rigidbody,90);
        collider.setOffset(new Vector2D(-88,-170));

        animator = new Animator(this,sprite);

        animator.createAction("idle", 0.2f, Animator.Policy.instant,Animator.Type.loop,splitTexture.getTextures(0,5));

        animator.createAction("running", 0.1f, Animator.Policy.instant,Animator.Type.loop,splitTexture.getTextures(6,14));
        animator.createAction("sliding", 0.1f, Animator.Policy.instant,Animator.Type.once,splitTexture.getTextureIndex(75,73,74,75));
        animator.createAction("jumping",0.1f,Animator.Policy.instant,Animator.Type.loop,splitTexture.getTextures(41,43));
        animator.createAction("falling-mid",0.05f,Animator.Policy.instant,Animator.Type.once,splitTexture.getTextures(44,45));
        animator.createAction("falling",0.1f,Animator.Policy.instant,Animator.Type.loop,splitTexture.getTextures(46,48));

        animator.createAction("attack-1",0.1f,Animator.Policy.instant, Animator.Type.once,splitTexture.getTextures(15,23));
        animator.createAction("attack-2",0.2f,Animator.Policy.instant, Animator.Type.loop,splitTexture.getTextures(22,26));
    }

    @Override
    public void update(){
        move();
        jump();
        attack();
        animateSwitch();
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_K)== GLFW.GLFW_PRESS){
            System.out.println("K");
            GameObject backGround2 = GameObject.findGameObject("BackGround2");
            Render component = (Render) backGround2.getComponent("Render").get(0);
            System.out.println(component.getGameObject().name);
            objectPool.get();
        } else if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_F1) == GLFW.GLFW_PRESS) {
            Debug.setDebugMod(false);
        }
    }

    private void attack() {
/*        if (EventSystem.getMouseButton1() && isOnFloor) {
            isAttacking = true;
        }

        if (isAttacking){

        }*/
    }

    @Override
    public void enable() {
        System.out.println("player.enable()");
    }

    @Override
    public void disable() {

    }
    private void move(){
        Vector2D moveVector = new Vector2D();

        isMoving = false;

        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_A)==GLFW.GLFW_PRESS){
            moveVector.x = -1;
            flip(false);
            isMoving = true;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_D)==GLFW.GLFW_PRESS){
            moveVector.x = 1;
            flip(true);
            isMoving = true;
        }

        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_H)==GLFW.GLFW_PRESS){
            rigidbody.setGravity(true);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_J)==GLFW.GLFW_PRESS){
            rigidbody.setGravity(false);
        }

        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_G)==GLFW.GLFW_PRESS){
            transform.position = new Vector2D(0,0);
        }

        moveVector.normalize();
        if (moveVector.getLength() != 0)
            rigidbody.addForce( new Vector2D(moveVector.multiplied(speed)));

    }
    private void jump(){
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_SPACE)==GLFW.GLFW_PRESS && !isJumped && isOnFloor){
            rigidbody.addForce(new Vector2D(0,200));
            isJumped = true;
            isJumping = true;
        }
        else if (isOnFloor){
            isJumped = false;
            isJumping = false;
        }
    }
    private void flip(boolean isRight){
        if(isRight) {
            sprite.offset = new Vector2D(0, 0);
            transform.scale.x = Math.abs(transform.scale.x);
        }
        else {
            sprite.offset = new Vector2D(-150, 0);
            transform.scale.x = -Math.abs(transform.scale.x);
        }
    }
    private void animateSwitch() {
        if (isJumping || rigidbody.velocity.y < -10) {
            if (rigidbody.velocity.y > 0) {
                animator.setAction("jumping");
            } else {
                if (animator.getCurrentAction().equals("falling-mid") && animator.isFinished())
                    animator.setAction("falling");
                else if (!animator.getLastAction().equals("falling-mid"))
                    animator.setAction("falling-mid");
            }
        } else if ((animator.getCurrentAction().equals("falling") || animator.getCurrentAction().equals("falling-mid")) && isOnFloor) {
            animator.setAction("sliding");
        } else if (isMoving)
            animator.setAction("running");
        else if (animator.getCurrentAction().equals("running") && !isMoving) {
            animator.setAction("sliding");
        }
        if (animator.getCurrentAction().equals("sliding") && animator.isFinished()) {
            animator.setAction("idle");
        }
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
        //System.out.println("Player");

    }
}
