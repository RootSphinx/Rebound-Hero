package org.sphinx.sphinxengine.test;
import org.sphinx.sphinxengine.engine.*;

public class Test {
    public static void main(String[] args) {
        Debug.setDebugMod(true);
        GameEngine gameEngine = GameEngine.getGameEngine();
        gameEngine.setWindowInfo(1400,800,"你好");
        gameEngine.setFps(60);
        gameEngine.init();
        SceneController.register(new GameTestScene());
        SceneController.register(new GameTestScene1());
        SceneController.register(new GameTestScene2());
        gameEngine.start();
    }
}
