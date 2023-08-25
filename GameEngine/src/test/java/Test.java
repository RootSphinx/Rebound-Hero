import org.joml.Vector2d;
import org.lwjgl.opengl.GL;
import org.sphinx.sphinxengine.engine.*;

import static org.lwjgl.opengl.GL11.glGenTextures;

public class Test {
    public static void main(String[] args) {
        GameEngine gameEngine = GameEngine.getGameEngine();
        gameEngine.setWindowInfo(1400,800,"你好");
        gameEngine.init();
        SceneController.register(new GameTestScene());
        gameEngine.start();
    }
}
