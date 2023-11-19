package org.sphinx.game;

import org.sphinx.engine.GameEngine;
import org.sphinx.engine.SceneController;
import org.sphinx.util.Debug;

class Demo{
    public static void main(String[] args) {
        Debug.setDebugMod(true);
        GameEngine gameEngine = GameEngine.getGameEngine();
        gameEngine.setWindowAttribute(1400,800,"GameDemo");
        gameEngine.setFps(60);
        gameEngine.init();
        SceneController.register(new SecondScene());
        SceneController.register(new ThirdScene());
        gameEngine.start();
    }
}