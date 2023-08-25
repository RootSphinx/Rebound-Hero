import org.sphinx.sphinxengine.engine.GameObject;
import org.sphinx.sphinxengine.engine.GameTimer;
import org.sphinx.sphinxengine.engine.SceneController;
import org.sphinx.sphinxengine.engine.Sprite;

public class BackGround extends GameObject {
    //Sprite sprite;
    GameTimer gameTimer = new GameTimer();

    @Override
    public void start() {
/*        sprite = new Sprite(this, "/background.jpg", Sprite.Type.Item);
        sprite.setLayout(1);*/
    }

    @Override
    public void update() {
        if (gameTimer.time > 5){
            SceneController.loadNextScene();
        }
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
}
