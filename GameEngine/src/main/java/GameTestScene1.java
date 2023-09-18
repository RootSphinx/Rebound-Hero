import org.sphinx.engine.Renderer;
import org.sphinx.engine.Scene;

public class GameTestScene1 implements Scene {

    @Override
    public void initScene() {
        GameCamera gameCamera = new GameCamera(1400, 800);
        Renderer.setActiveCamera(gameCamera);
        BackGround backGround = new BackGround();
        //org.sphinx.main.BackGround1 backGround1 = new org.sphinx.main.BackGround1();
        //BackGround2 backGround2 = new BackGround2();
        Player player = new Player();
    }
}
