import org.sphinx.sphinxengine.engine.*;
import org.sphinx.sphinxengine.util.Debug;

public class Test {
    public static void main(String[] args) {
        Debug.setDebugMod(true);
        GameEngine gameEngine = GameEngine.getGameEngine();
        gameEngine.setWindowAttribute(1400,800,"你好");
        gameEngine.setFps(60);
        gameEngine.init();
        SceneController.register(new GameTestScene());
        gameEngine.start();
    }
}
