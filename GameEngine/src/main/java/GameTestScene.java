import org.sphinx.engine.Renderer;
import org.sphinx.engine.Scene;
import org.sphinx.engine.Vector2D;

public class GameTestScene implements Scene {
    @Override
    public void initScene() {
        GameCamera gameCamera = new GameCamera(1400, 800);
        Renderer.setActiveCamera(gameCamera);
        BackGround backGround = new BackGround();
        BackGround1 backGround1 = new BackGround1();

        Player player = new Player();

        gameCamera.target = player;
        gameCamera.backGround = backGround1.sprite;
    }
}
