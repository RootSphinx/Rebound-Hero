package org.sphinx.sphinxengine.engine;

public abstract class Scene {
    private static Scene activeScene;
    abstract void initScene();
    public static void loadScene(Scene scene){

    }
}
