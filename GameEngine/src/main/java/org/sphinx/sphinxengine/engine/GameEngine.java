package org.sphinx.sphinxengine.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL11.glGenTextures;


public class GameEngine {
    private final WindowController windowController = WindowController.getInstance();
    public void setWindowInfo(int windowSizeX, int windowSizeY, String windowTitle){
        windowController.setWindowInfo(windowSizeX, windowSizeY, windowTitle);
    }

    public void init(){
        windowController.glInit();
        ShaderProgram.defaultShaderInit();
        Renderer.init();
    }
    private void loop(){
        while (!GLFW.glfwWindowShouldClose(windowController.window)){
            GLFW.glfwPollEvents();
            GameTimer.timerUpdate();
            GameObject.gameObjectStatusUpdate();    //对象更新
            GameObject.gameObjectsUpdate();     //对象状态更新
            Animator.animatorsUpdate();         //动画更新
            Renderer.startSpriteRender();     //纹理渲染
            Renderer.startNormalRender();   //无纹理渲染
            windowController.swapBuffer();  //交换缓冲区
        }
    }
    private void cleanup(){
        windowController.windowDestroy();
        GameObject.gameObjectsRemoveAll();
    }

    public void runLoop() {
       try {
           loop();
       }
       catch (Exception e){
           e.printStackTrace();
       }
       finally {
           cleanup();
       }
    }
}
