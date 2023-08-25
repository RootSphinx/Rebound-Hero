package org.sphinx.sphinxengine.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL11.glGenTextures;

public class GameEngine {
    public static GameEngine gameEngine = null;
    private GameEngine(){}
    public static GameEngine getGameEngine(){
        if (gameEngine==null)
            gameEngine = new GameEngine();
        return gameEngine;
    }
    private final WindowController windowController = WindowController.getInstance();

    /**
     * 设置窗口属性
     * @param windowSizeX 窗口宽度
     * @param windowSizeY 窗口高度
     * @param windowTitle 窗口标题
     */
    public void setWindowInfo(int windowSizeX, int windowSizeY, String windowTitle){
        windowController.setWindowInfo(windowSizeX, windowSizeY, windowTitle);
    }

    /**
     * 引擎初始化
     */
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
    protected void cleanup(){
        windowController.windowDestroy();
        SceneController.cleanScene();
    }

    /**
     * 启动引擎循环
     */
    public void start() {
       try {
           SceneController.loadScene(0);
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
