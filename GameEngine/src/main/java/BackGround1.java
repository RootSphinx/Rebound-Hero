import org.sphinx.engine.*;
import org.sphinx.ui.Canvas;
import org.sphinx.ui.Lable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BackGround1 extends GameObject implements Collision{
    Sprite sprite;
    Lable lable;
    Canvas canvas;
    GameTimer gameTimer = new GameTimer();
    List<BoxCollider> boxColliders;
    Rigidbody rigidbody;
    int frame = 0;

    @Override
    public void start() {
        sprite = new Sprite(this, "/Image/BackGround/20231011-游戏背景.jpg", Render.UsageType.Item);
        sprite.setLayout(1);
        transform.scale = new Vector2D(8,8);
        this.name = "BackGround1";
        this.tag = "floor";
        canvas = new Canvas(0,0,30,30);
        canvas.setColor(new Color(0,0,0,0));
        lable = new Lable(canvas,100,20);
        rigidbody = new Rigidbody(this);
        rigidbody.setGravity(false);

        boxColliders = new ArrayList<>(17);
        boxColliders.add(0, new BoxCollider(this, rigidbody, new Vector2D(-6990, -1810), 1420, 1180));
        boxColliders.add(1, new BoxCollider(this, rigidbody, new Vector2D(-5190, -1690), 2180, 1420));
        boxColliders.add(2, new BoxCollider(this, rigidbody, new Vector2D(-3255, -1930), 1690, 940));
        boxColliders.add(3, new BoxCollider(this, rigidbody, new Vector2D(-2055, -1800), 710, 1200));
        boxColliders.add(4, new BoxCollider(this, rigidbody, new Vector2D(-1450, -1925), 500, 950));
        boxColliders.add(5, new BoxCollider(this, rigidbody, new Vector2D(-360, -1685), 720, 1430));
        boxColliders.add(6, new BoxCollider(this, rigidbody, new Vector2D(245, -1565), 490, 1670));
        boxColliders.add(7, new BoxCollider(this, rigidbody, new Vector2D(730, -1445), 480, 1910));
        boxColliders.add(8, new BoxCollider(this, rigidbody, new Vector2D(1330, -1320), 720, 2160));
        boxColliders.add(9, new BoxCollider(this, rigidbody, new Vector2D(2525, -1200), 1670, 2400));
        boxColliders.add(10, new BoxCollider(this, rigidbody, new Vector2D(3480, -1325), 240, 2150));
        boxColliders.add(11, new BoxCollider(this, rigidbody, new Vector2D(3845, -1445), 490, 1910));
        boxColliders.add(12, new BoxCollider(this, rigidbody, new Vector2D(4330, -1565), 480, 1670));
        boxColliders.add(13, new BoxCollider(this, rigidbody, new Vector2D(5300, -1685), 1460, 1430));
        boxColliders.add(14, new BoxCollider(this, rigidbody, new Vector2D(6500, -1565), 940, 1670));
        boxColliders.add(15, new BoxCollider(this, rigidbody, new Vector2D(7335, -1685), 730, 1430));
        boxColliders.add(16, new BoxCollider(this, rigidbody, new Vector2D(-965, -1805), 490, 1190));
    }

    @Override
    public void update() {
        frame++;
        if (gameTimer.time > 1){
            lable.setText("FPS : "+ (int)(frame / gameTimer.time),20, Color.white);
            //System.out.println("FPS : "+ (int)(frame / gameTimer.time));
            gameTimer.time-=1;
            frame = 0;
        }
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

    }
}
