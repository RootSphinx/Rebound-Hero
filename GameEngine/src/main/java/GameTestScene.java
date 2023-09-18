import org.sphinx.engine.Renderer;
import org.sphinx.engine.Scene;

public class GameTestScene implements Scene {
    @Override
    public void initScene() {
        GameCamera gameCamera = new GameCamera(1400, 800);
        Renderer.setActiveCamera(gameCamera);
        BackGround backGround = new BackGround();
        BackGround1 backGround1 = new BackGround1();
        BackGround2 backGround2 = new BackGround2();
        Player player = new Player();
    }
}
