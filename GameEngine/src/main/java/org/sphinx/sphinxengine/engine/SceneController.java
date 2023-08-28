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
        Debug.log("场景加载器----场景注册成功,当前场景总数 : "+SCENE_LIST.size());
    }
    public static void loadSceneIndex(int sceneId){
        isLoadingNextScene = true;
        activeScene = SCENE_LIST.get(sceneId);
        Debug.log("场景加载器----加载场景ID : "+sceneId);
        activeSceneId = sceneId;
        startLoadScene();
    }
    public static void loadNextScene(){
        isLoadingNextScene = true;
        activeScene = SCENE_LIST.get(++activeSceneId);
        Debug.log("场景加载器----加载下一个场景，ID : "+activeSceneId);
        startLoadScene();
    }
    private static void startLoadScene(){
        cleanScene();
        activeScene.initScene();
    }
    public static void cleanScene(){
        Debug.log("场景资源释放中");
        Renderer.setActiveCamera(null);
        GameObject.gameObjectsRemoveAll();
        Texture.destroyAllTexture();
        Renderer.destroyAllSprite();
        GameTimer.destroyAllGameTimer();
        Animator.destroyAllAnimator();
        System.gc();
    }
}
