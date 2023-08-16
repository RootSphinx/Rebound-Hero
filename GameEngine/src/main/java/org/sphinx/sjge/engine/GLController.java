package org.sphinx.sjge.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

public class GLController {
    private final int WINDOW_SIZE_X;
    private final int WINDOW_SIZE_Y;
    private final String WINDOW_TITLE;
    public long window = 0;
    public GLController(int windowSizeX, int windowSizeY, String windowTitle) {
        WINDOW_SIZE_X = windowSizeX;
        WINDOW_SIZE_Y = windowSizeY;
        WINDOW_TITLE = windowTitle;
    }
    public void glInit(){
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()){
            throw new RuntimeException("glfw初始化失败");
        }
        glfwWindowHint(GLFW_RESIZABLE,GLFW_FALSE);
        window = glfwCreateWindow(WINDOW_SIZE_X,WINDOW_SIZE_Y,WINDOW_TITLE,0,0);
        if (window==0){
            throw new RuntimeException("窗口创建失败");
        }
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
    }
    public void hideWindow(){
        glfwHideWindow(window);
    }
    public void showWindow(){
        glfwShowWindow(window);
    }
}
