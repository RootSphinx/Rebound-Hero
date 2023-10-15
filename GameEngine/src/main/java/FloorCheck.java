import org.sphinx.engine.*;

public class FloorCheck extends GameObject implements Collision{
    CircleCollider collider;
    Rigidbody rigidbody;

    Player player;
    FloorCheck(Player player){
        this.player = player;
        setParent(player);
    }
    @Override
    public void start() {
        rigidbody =  new Rigidbody(this);
        rigidbody.setGravity(false);
        collider = new CircleCollider(this,rigidbody,40);
        collider.isTrigger = true;
        transform.position = new Vector2D(-88,-230);
    }

    @Override
    public void update() {

    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void onTriggerEnter(Collider collider) {
        if (collider.getGameObject().tag .equals("floor")) {
            player.isOnFloor = true;
        }
    }

    @Override
    public void onTriggerUpdate(Collider collider) {

    }

    @Override
    public void onTriggerExit(Collider collider) {
        if (collider.getGameObject().tag .equals("floor")) {
            player.isOnFloor = false;
        }
    }

    @Override
    public void onCollisionEnter(Collider collider) {

    }
}
