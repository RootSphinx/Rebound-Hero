import org.lwjgl.opengl.GL;
import org.sphinx.sphinxengine.engine.Camera;
import org.sphinx.sphinxengine.engine.GameEngine;
import org.sphinx.sphinxengine.engine.Renderer;

import static org.lwjgl.opengl.GL11.glGenTextures;

public class Test {
    public static void main(String[] args) {

        GameEngine gameEngine = new GameEngine();
        gameEngine.setWindowInfo(1400,800,"你好");
        Renderer.setActiveCamera(new Camera(1400, 800));
        gameEngine.init();
        Player player = new Player();
        gameEngine.runLoop();
    }
}
