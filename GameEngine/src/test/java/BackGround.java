import org.sphinx.sphinxengine.engine.GameObject;
import org.sphinx.sphinxengine.engine.Sprite;

public class BackGround extends GameObject {
    Sprite sprite;

    @Override
    public void start() {
        sprite = new Sprite(this, "/background.jpg", Sprite.Type.Item);
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
