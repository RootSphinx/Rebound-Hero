package org.sphinx.sphinxengine.engine;

public class Debug {
    private static boolean isDebugging = false;
    private static boolean isTestDebugging = false;
    public static void log(String text){
        if (isDebugging){
            System.out.println(text);
        }
    }
    public static void testLog(String text){
        if (isTestDebugging){
            System.out.println(text);
        }
    }
    public static void err(String text){
        if (isDebugging){
            System.err.println(text);
        }
    }
    public static void setDebugMod(boolean sign){
        isDebugging = sign;
    }
    public static void setTestDebugMod(boolean sign){
        isTestDebugging = sign;
    }
}
