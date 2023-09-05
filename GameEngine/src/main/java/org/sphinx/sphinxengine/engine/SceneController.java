package org.sphinx.sphinxengine.engine;

import org.sphinx.sphinxengine.util.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * 场景控制器
 */
public class SceneController {
    /**
     * 场景列表
     */
    public static final List<Scene> SCENE_LIST = new ArrayList<>();
    private static Scene activeScene = null;
    /**
     * 场景控制状态
     */
    public static boolean isLoadingNextScene = false;
    private static int activeSceneId = 0;

    /**
     * 注册一个场景
     * @param scene 注册的场景
     */
    public static void register(Scene scene){
        SCENE_LIST.add(scene);
        Debug.log("场景加载器----场景注册成功,当前场景总数 : "+SCENE_LIST.size());
    }

    /**
     * 加载指定场景
     * @param sceneId 场景ID
     */
    public static void loadSceneIndex(int sceneId){
        isLoadingNextScene = true;
        Renderer.render();
        activeScene = SCENE_LIST.get(sceneId);
        Debug.log("场景加载器----加载场景ID : "+sceneId);
        activeSceneId = sceneId;
        startLoadScene();
    }

    /**
     * 加载下一个场景
     */
    public static void loadNextScene(){
        if (activeSceneId+1>SCENE_LIST.size()) return;
        isLoadingNextScene = true;
        activeScene = SCENE_LIST.get(++activeSceneId);
        Debug.log("场景加载器----加载下一个场景，ID : "+activeSceneId);
        startLoadScene();
    }
    private static void startLoadScene(){
        cleanScene();
        activeScene.initScene();
    }

    /**
     * 释放当前场景资源
     *      清空纹理
     *      清空渲染对象
     *      清空计数器
     *      清空动画器
     *      清空游戏对象
     */
    public static void cleanScene(){
        Debug.log("场景资源释放中");
        Renderer.setActiveCamera(null);
        Texture.destroyAllTexture();
        Render.destroyAllRender();
        GameTimer.destroyAllGameTimer();
        Animator.destroyAllAnimator();
        GameObject.gameObjectsRemoveAll();
        System.gc();
    }
}
