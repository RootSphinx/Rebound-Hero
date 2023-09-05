package org.sphinx.sphinxengine.engine;

/**
 * 场景接口
 */
public interface Scene {
     void initScene();
     static void finsh(){
          SceneController.isLoadingNextScene = false;
     }
}
