package org.sphinx.engine;

/**
 * 场景接口
 */
public interface Scene {
     void initScene();
     static void finish(){
          SceneController.isLoadingNextScene = false;
     }
}
