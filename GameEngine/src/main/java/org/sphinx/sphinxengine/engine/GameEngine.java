package org.sphinx.sphinxengine.engine;
import org.lwjgl.glfw.GLFW;
import org.sphinx.sphinxengine.util.Debug;

/**
 * 游戏引擎
 */
public class GameEngine {
    public static GameEngine gameEngine = null;
    private int fps = 30;
    private GameEngine(){}
    public static GameEngine getGameEngine(){
        if (gameEngine==null) {
            gameEngine = new GameEngine();
            Debug.log("引擎实例创建成功");
        }
        return gameEngine;
    }
    private final WindowController windowController = WindowController.getInstance();

    /**
     * 设置窗口属性
     * @param windowSizeWidth 窗口宽度
     * @param windowSizeHeight 窗口高度
     * @param windowTitle 窗口标题
     */
    public void setWindowAttribute(int windowSizeWidth, int windowSizeHeight, String windowTitle){
        windowController.setWindowInfo(windowSizeWidth, windowSizeHeight, windowTitle);
    }

    /**
     * 引擎初始化
     */
    public void init(){
        windowController.glInit();
        EventSystem.init();
        ShaderProgram.defaultShaderInit();
        Renderer.init();
        Text.init();
        Debug.log("引擎初始化成功");
    }
    private void loop(){
        Debug.log("引擎循环启动");
        while (!GLFW.glfwWindowShouldClose(windowController.window)){
            GLFW.glfwPollEvents();
            GameTimer.timerUpdate();
            GameObject.gameObjectsUpdate();     //对象更新
            GameObject.gameObjectStatusUpdate();    //对象状态更新
            Animator.animatorsUpdate();         //动画更新
            Renderer.render();                  //渲染器渲染
            windowController.swapBuffer();  //交换缓冲区
            Scene.finsh();
            sleep();
        }
    }
    private void cleanup(){
        Debug.log("开始资源释放");
        windowController.windowDestroy();
        SceneController.cleanScene();
    }
    private void sleep(){
        double onceLoopTime = GameTimer.onceLoopTime();
        double v = (1d / fps - onceLoopTime)*1000;
        v = v > 0 ? v : 0;
        try {
            Thread.sleep((int) v);
            //System.out.println(v>1?1000/(int) v:0);
            //System.out.println(onceLoopTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    /**
     * 启动引擎循环
     */
    public void start() {
       try {
           SceneController.loadSceneIndex(0);
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
