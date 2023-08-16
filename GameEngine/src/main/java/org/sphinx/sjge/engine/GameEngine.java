package org.sphinx.sjge.engine;

import org.lwjgl.glfw.GLFW;

public class GameEngine implements Runnable{
    GLController glController;
    private int windowSizeX = 1200;
    private int windowSizeY = 800;
    private String windowTitle = "hello, world";
    public void setWindowInfo(int windowSizeX, int windowSizeY, String windowTitle){
        this.windowSizeX = windowSizeX;
        this.windowSizeY = windowSizeY;
        this.windowTitle = windowTitle;
    }
    private void init(){
         glController = new GLController(windowSizeX, windowSizeY, windowTitle);
         glController.glInit();
    }
    private void loop(){
        while (!GLFW.glfwWindowShouldClose(glController.window)){



            GLFW.glfwPollEvents();
        }
    }
    private void cleanup(){

    }

    @Override
    public void run() {
       try {
           init();
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
