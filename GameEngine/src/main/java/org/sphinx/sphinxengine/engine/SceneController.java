package org.sphinx.sphinxengine.engine;

import java.util.ArrayList;
import java.util.List;

public class SceneController {
    public static final List<Scene> SCENE_LIST = new ArrayList<>();
    private static Scene activeScene = null;
    public static boolean isLoadingNextScene = false;
    private static int activeSceneId = 0;
    public static void register(Scene scene){
        SCENE_LIST.add(scene);
    }
    public static void loadScene(int sceneId){
        isLoadingNextScene = true;
        activeScene = SCENE_LIST.get(sceneId);
        cleanScene();
        System.out.println("SceneController.loadScene()");
        activeScene.initScene();
        activeSceneId = sceneId;
    }
    public static void loadNextScene(){
        isLoadingNextScene = true;
        activeScene = SCENE_LIST.get(++activeSceneId);
        System.out.println("场景 : "+activeSceneId);
        cleanScene();
        System.out.println("SceneController.loadNextScene()");
        activeScene.initScene();
    }
    public static void cleanScene(){
        GameObject.gameObjectsRemoveAll();
        Texture.destroyAllTexture();
        Renderer.destroyAllSprite();
        GameTimer.destroyAllGameTimer();
        Animator.destroyAllAnimator();
    }
}
