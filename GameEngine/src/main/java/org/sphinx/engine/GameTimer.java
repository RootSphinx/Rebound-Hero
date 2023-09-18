package org.sphinx.engine;
import org.sphinx.util.Debug;

import static org.lwjgl.glfw.GLFW.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 计时器
 */
public class GameTimer {
    private static final List<GameTimer> GAME_TIMER_LIST = new ArrayList<>();
    public double time = 0;
    public static double deltaTime;
    private static double lastTimer = glfwGetTime();
    public boolean isFreeze = false;

    /**
     * 创建一个计时器
     */
    public GameTimer(){
        GAME_TIMER_LIST.add(this);
    }

    /**
     * 重置当前计时器
     */
    public void reset(){
        this.time = 0;
    }

    /**
     * 计时器更新
     */
    static void timerUpdate(){
        deltaTime = glfwGetTime() - lastTimer;
        lastTimer = glfwGetTime();
        for (GameTimer gameTimer : GAME_TIMER_LIST){
            if (!gameTimer.isFreeze)
                gameTimer.time += deltaTime;
        }
    }
    static double onceLoopTime(){
        return glfwGetTime() -lastTimer;
    }

    /**
     * 销毁所有计时器
     */
    public static void destroyAllGameTimer(){
        Debug.log("计时器----正在释放计时器");
        while (!GAME_TIMER_LIST.isEmpty()){
            GAME_TIMER_LIST.remove(0);
        }
    }

    /**
     * 销毁当前计时器
     */
    public void destroy(){
        GAME_TIMER_LIST.remove(this);
    }
}
