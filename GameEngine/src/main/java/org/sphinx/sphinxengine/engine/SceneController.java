package org.sphinx.sphinxengine.engine;

import java.util.ArrayList;
import java.util.List;

public class SceneController {
    public static final List<Scene> SCENE_LIST = new ArrayList<>();
    private static Scene activeScene = null;
    public static void register(Scene scene){
        SCENE_LIST.add(scene);
    }
    public static void loadScene(int sceneId){
        activeScene = SCENE_LIST.get(sceneId);
        cleanScene();
        activeScene.initScene();
    }
    public static void cleanScene(){
        GameObject.gameObjectsRemoveAll();
        Texture.destroyAllTexture();
    }
}
