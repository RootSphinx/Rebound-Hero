package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.Renderer;
import org.sphinx.sphinxengine.engine.Scene;

public class GameTestScene1 implements Scene {

    @Override
    public void initScene() {
        GameCamera gameCamera = new GameCamera(1400, 800);
        Renderer.setActiveCamera(gameCamera);
        BackGround backGround = new BackGround();
        //org.sphinx.sphinxengine.test.BackGround1 backGround1 = new org.sphinx.sphinxengine.test.BackGround1();
        //org.sphinx.sphinxengine.test.BackGround2 backGround2 = new org.sphinx.sphinxengine.test.BackGround2();
        Player player = new Player();
    }
}
