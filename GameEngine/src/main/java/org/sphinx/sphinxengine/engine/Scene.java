package org.sphinx.sphinxengine.engine;

public interface Scene {
     void initScene();
     static void finsh(){
          SceneController.isLoadingNextScene = false;
     }
}
