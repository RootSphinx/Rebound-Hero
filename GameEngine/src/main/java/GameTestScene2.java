import org.sphinx.engine.Renderer;
import org.sphinx.engine.Scene;

public class GameTestScene2 implements Scene {
    @Override
    public void initScene() {
        GameCamera gameCamera = new GameCamera(1400, 800);
        Renderer.setActiveCamera(gameCamera);
        //org.sphinx.main.BackGround1 backGround1 = new org.sphinx.main.BackGround1();
        Player player = new Player();
    }
}
