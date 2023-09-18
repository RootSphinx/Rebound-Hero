import org.sphinx.engine.GameObject;
import org.sphinx.engine.GameTimer;
import org.sphinx.engine.Sprite;
import org.sphinx.engine.Vector2D;

import java.util.Random;

public class BackGround2 extends GameObject {
    Sprite sprite;
    GameTimer gameTimer = new GameTimer();
    Random random;

    @Override
    public void start() {
        this.name = "BackGround2";
        random = new Random();
        this.transform.position = new Vector2D(random.nextFloat(-100,100),random.nextInt(-100,100));
        sprite = new Sprite(this, "/sprite.png", Sprite.UsageType.Item);
        sprite.setLayout(1);
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
}
