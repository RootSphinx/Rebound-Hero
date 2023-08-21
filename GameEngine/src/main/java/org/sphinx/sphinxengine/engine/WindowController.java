package org.sphinx.sphinxengine.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

public class WindowController {
    private int windowSizeX = 1200;
    private int windowSizeY = 800;
    private String windowTitle = "hello, world";
    public long window = 0;
    private static WindowController instance;
    public static WindowController getInstance(){
        if (instance == null){
            instance = new WindowController();
        }
        return instance;
    }
    private WindowController() {

    }
    public void setWindowInfo(int windowSizeX, int windowSizeY, String windowTitle){
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.windowTitle = windowTitle;
    }
    public void glInit(){
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()){
            throw new RuntimeException("glfw初始化失败");
        }
        glfwWindowHint(GLFW_RESIZABLE,GLFW_FALSE);
        window = glfwCreateWindow(windowSizeX,windowSizeY,windowTitle,0,0);
        if (window==0){
            throw new RuntimeException("窗口创建失败");
        }
        context();
        glfwSwapInterval(1);
    }
    public void context(){
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }
    public void hideWindow(){
        glfwHideWindow(window);
    }
    public void showWindow(){
        glfwShowWindow(window);
    }

    public int getWindowSizeX() {
        return windowSizeX;
    }

    public int getWindowSizeY() {
        return windowSizeY;
    }
    public void swapBuffer(){
        glfwSwapBuffers(window);
    }
    public void windowDestroy(){
        glfwTerminate();
    }
}
