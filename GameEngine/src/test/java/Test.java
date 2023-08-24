import org.joml.Vector2d;
import org.lwjgl.opengl.GL;
import org.sphinx.sphinxengine.engine.Camera;
import org.sphinx.sphinxengine.engine.GameEngine;
import org.sphinx.sphinxengine.engine.Renderer;
import org.sphinx.sphinxengine.engine.Vector2D;

import static org.lwjgl.opengl.GL11.glGenTextures;

public class Test {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.setWindowInfo(1400,800,"你好");
        gameEngine.init();
        Renderer.setActiveCamera(new GameCamera(1400, 800));
        BackGround backGround = new BackGround();
        Player player = new Player();
        gameEngine.runLoop();
    }
}
