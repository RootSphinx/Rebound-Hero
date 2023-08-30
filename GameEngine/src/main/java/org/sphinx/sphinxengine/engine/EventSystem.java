package org.sphinx.sphinxengine.engine;

import org.lwjgl.glfw.GLFWCharModsCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;

public class EventSystem {
    private static final Point cursorPos = new Point();
    static GLFWCursorPosCallback cursorPosCallback;
    static GLFWMouseButtonCallback mouseButtonCallback;
    private static boolean mouseButton1 = false;
    private static boolean mouseButton2 = false;
    static void init(){

        glfwSetCursorPosCallback(WindowController.getInstance().window, cursorPosCallback = new GLFWCursorPosCallback() {
            public void invoke(long window, double xpos, double ypos) {
                cursorPos.x = (int) xpos;
                cursorPos.y = (int) ypos;
            }
        });
        glfwSetMouseButtonCallback(WindowController.getInstance().window, mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (button == GLFW_MOUSE_BUTTON_1){
                    if (action == GLFW_PRESS){
                        mouseButton1 = true;
                    }
                    else if (action == GLFW_RELEASE){
                        mouseButton1 = false;
                    }
                }
                if (button == GLFW_MOUSE_BUTTON_2){
                    if (action == GLFW_PRESS){
                        mouseButton2 = true;
                    }
                    else if (action == GLFW_RELEASE){
                        mouseButton2 = false;
                    }
                }
            }
        });
    }

    public static Point getCursorPos() {
        return cursorPos;
    }
    public static boolean getMouseButton1(){
        return mouseButton1;
    }
    public static boolean getMouseButton2(){
        return mouseButton2;
    }
}
