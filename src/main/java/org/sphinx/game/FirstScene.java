package org.sphinx.game;

import org.sphinx.engine.Camera;
import org.sphinx.engine.Renderer;
import org.sphinx.engine.Scene;
public class FirstScene implements Scene{
    @Override
    public void initScene() {
        Renderer.setActiveCamera(new Camera(100,100));
    }
}
