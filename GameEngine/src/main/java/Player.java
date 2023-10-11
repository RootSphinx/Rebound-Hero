import org.lwjgl.glfw.GLFW;
import org.sphinx.engine.*;
import org.sphinx.util.Utils;

public class Player extends GameObject implements Collision{


    enum VectorType{
        left ("left") ,right("right"),back("back"),front("front");
        public final String value;
        VectorType(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
    VectorType vector = VectorType.front;
    Sprite sprite;
    Animator animator;
    Rigidbody rigidbody;
    Utils.ObjectPool objectPool = new Utils.ObjectPool(BackGround2.class,10);
    CircleCollider collider;
    CircleCollider collider1;
    Boolean isJumped = false;
    @Override
    public void start(){
        //System.out.println("player.start()");
        tag = "Player";
        name = "Player";
        transform.scale = 6;
        sprite = new Sprite(this, "/图片包/image0.png", Sprite.UsageType.Item);
        sprite.setLayout(2);
        SplitTexture textures = new SplitTexture("/sprite.png", 4, 4);

        rigidbody = new Rigidbody(this);
        rigidbody.setGravity(false);
        rigidbody.setMass(10);
        collider = new CircleCollider(this,rigidbody,60);
        collider.setOffset(new Vector2D(0,-70));

/*        collider1 = new CircleCollider(this,rigidbody,30);
        collider1.setOffset(new Vector2D(0,-10));*/
        animator = new Animator(this,sprite);
        animator.createAction("left-idle", 0.2f, Animator.Type.instant,textures.getTextureIndex(4));
        animator.createAction("right-idle", 0.2f, Animator.Type.instant,textures.getTextureIndex(8));
        animator.createAction("back-idle", 0.2f, Animator.Type.instant,textures.getTextureIndex(12));
        animator.createAction("front-idle", 0.2f, Animator.Type.instant,textures.getTextureIndex(0));

        animator.createAction("left", 0.2f, Animator.Type.instant,textures.getTextureIndex(5,6,7,4));
        animator.createAction("right", 0.2f, Animator.Type.instant,textures.getTextureIndex(9,10,11,8));
        animator.createAction("back", 0.2f, Animator.Type.instant,textures.getTextureIndex(13,14,15,12));
        animator.createAction("front", 0.2f, Animator.Type.instant,textures.getTextureIndex(1,2,3,0));
    }

    @Override
    public void update(){
        move1();
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_K)== GLFW.GLFW_PRESS){
            System.out.println("K");
            GameObject backGround2 = GameObject.findGameObject("BackGround2");
            Render component = (Render) backGround2.getComponent("Render").get(0);
            System.out.println(component.getGameObject().name);
            objectPool.get();
        }
    }

    @Override
    public void enable() {
        System.out.println("player.enable()");
    }

    @Override
    public void disable() {

    }
    void move1(){
        Vector2D moveVector = new Vector2D();

        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_W)==GLFW.GLFW_PRESS){
            moveVector.y = 1;
            vector = VectorType.back;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_S)==GLFW.GLFW_PRESS){
            moveVector.y = -1;
            vector = VectorType.front;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_A)==GLFW.GLFW_PRESS){
            moveVector.x = -1;
            vector = VectorType.left;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_D)==GLFW.GLFW_PRESS){
            moveVector.x = 1;
            vector = VectorType.right;
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_Q)==GLFW.GLFW_PRESS){
            addRotation(0.01f);
        }
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_E)==GLFW.GLFW_PRESS){
            addRotation(-0.01f);
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
        if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_SPACE)==GLFW.GLFW_PRESS && !isJumped){
            rigidbody.addForce(new Vector2D(0,100));
            isJumped = true;
        }
        else if (GLFW.glfwGetKey(WindowController.getInstance().window, GLFW.GLFW_KEY_B)==GLFW.GLFW_PRESS && isJumped){
            isJumped = false;
        }
        moveVector.normalize();
        if (moveVector.getLength() != 0)
            rigidbody.addForce( new Vector2D(moveVector.multiplied(2)));
        if (moveVector.getLength() < 0.1f){
            animator.setAction(vector.value+"-idle");
        }
        else {
            animator.setAction(vector.value);
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
        isJumped = false;
    }
}
