package org.sphinx.sphinxengine.util;

/**
 * Debug工具
 */
public class Debug {
    private static boolean isDebugging = false;
    private static boolean isTesting = false;

    /**
     * 当处于Debug模式时,在控制台输出指定文字
     * @param text 输出的文字
     */
    public static void log(String text){
        if (isDebugging){
            System.out.println(text);
        }
    }

    /**
     * 当处于Test模式时,在控制台输出指定文字
     * @param text 输出的文字
     */
    public static void testLog(String text){
        if (isTesting){
            System.out.println(text);
        }
    }

    /**
     * 输出错误信息
     * @param text 错误信息
     */
    public static void err(String text){
        if (isDebugging){
            System.err.println(text);
        }
    }

    /**
     * 设置Debug模式
     * @param sign bool
     */
    public static void setDebugMod(boolean sign){
        isDebugging = sign;
    }
    /**
     * 设置Test模式
     * @param sign bool
     */
    public static void setTestDebugMod(boolean sign){
        isTesting = sign;
    }
}
