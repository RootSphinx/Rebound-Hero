package org.sphinx.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.sphinx.util.Debug;

import static org.lwjgl.glfw.GLFW.*;

public class WindowController {
    private int windowWidth = 1200;
    private int windowHeight = 800;
    private String windowTitle = "hello, world";
    public long window = 0;
    private static WindowController instance;
    public static WindowController getInstance(){
        if (instance == null){
            instance = new WindowController();
            Debug.log("窗口管理器----实例创建成功");
        }
        return instance;
    }
    private WindowController() {

    }
    public void setWindowInfo(int windowWidth, int windowHeight, String windowTitle){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.windowTitle = windowTitle;
        Debug.log("窗口管理器----设置窗口");
        Debug.log("\t宽为 : "+windowWidth);
        Debug.log("\t高为 : "+windowHeight);
        Debug.log("\t标题为 : "+windowTitle);
    }
    public void glInit(){
        Debug.log("窗口管理器----开始初始化");
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()){
            throw new RuntimeException("glfw初始化失败");
        }
        glfwWindowHint(GLFW_RESIZABLE,GLFW_FALSE);
        window = glfwCreateWindow(windowWidth,windowHeight,windowTitle,0,0);
        if (window==0){
            throw new RuntimeException("窗口创建失败");
        }
        context();
        glfwSwapInterval(1);
    }
    public void context(){
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        Debug.log("窗口管理器----上下文创建完毕");
    }
    public void hideWindow(){
        glfwHideWindow(window);
        Debug.log("窗口管理器----窗口隐藏");
    }
    public void showWindow(){
        glfwShowWindow(window);
        Debug.log("窗口管理器----窗口显示");
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
    public void swapBuffer(){
        glfwSwapBuffers(window);
    }
    void windowDestroy(){
        glfwTerminate();
        Debug.log("窗口管理器----窗口销毁");
    }
}
