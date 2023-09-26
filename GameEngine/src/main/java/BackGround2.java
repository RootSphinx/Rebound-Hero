import org.sphinx.engine.*;

import java.util.Random;

public class BackGround2 extends GameObject implements Collision{
    Sprite sprite;
    GameTimer gameTimer = new GameTimer();
    Rigidbody rigidbody;
    CircleCollider collider;
    Random random;

    @Override
    public void start() {
        this.name = "BackGround2";
        random = new Random();
        this.transform.position = new Vector2D(random.nextFloat(-100,100),random.nextInt(-100,100));
        sprite = new Sprite(this, "/sprite.png", Sprite.UsageType.Item);
        sprite.setLayout(1);

        rigidbody = new Rigidbody(this);
        rigidbody.setGravity(false);

        collider = new CircleCollider(this,rigidbody,60);
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

    }

    @Override
    public void onTriggerUpdate(Collider collider) {

    }

    @Override
    public void onTriggerExit(Collider collider) {

    }

    @Override
    public void onCollisionEnter(Collider collider) {
        //System.out.println("BACK");
    }
}
