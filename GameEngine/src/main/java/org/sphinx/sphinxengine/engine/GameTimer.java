package org.sphinx.sphinxengine.engine;
import static org.lwjgl.glfw.GLFW.*;
import java.util.ArrayList;
import java.util.List;

public class GameTimer {
    private static final List<GameTimer> gameTimers = new ArrayList<>();
    public double time = 0;
    public static double deltaTime;
    private static double lastTimer = glfwGetTime();
    public boolean isFreeze = false;

    GameTimer(){
        gameTimers.add(this);
    }
    public void reset(){
        this.time = 0;
    }
    public static void timerUpdate(){
        deltaTime = glfwGetTime() - lastTimer;
        lastTimer = glfwGetTime();
        for (GameTimer gameTimer : gameTimers){
            if (!gameTimer.isFreeze)
                gameTimer.time += deltaTime;
        }
    }
}
