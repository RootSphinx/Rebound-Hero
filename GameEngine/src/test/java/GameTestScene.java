import org.sphinx.sphinxengine.engine.Renderer;
import org.sphinx.sphinxengine.engine.Scene;

public class GameTestScene implements Scene {
    @Override
    public void initScene() {
        GameCamera gameCamera = new GameCamera(1400, 800);
        Renderer.setActiveCamera(gameCamera);
        BackGround backGround = new BackGround();
        Player player = new Player();
    }
}
