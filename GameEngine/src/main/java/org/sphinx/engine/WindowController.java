package org.sphinx.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;
import org.sphinx.util.Debug;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Objects;

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
    private WindowController() {}
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
    public void setWindowShouldClose(){
        glfwSetWindowShouldClose(window,true);
    }
    public void setWindowIcon(String path){
        ByteBuffer buffer = null;
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(Texture.class.getResource(path)));
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int[] pixels =  image.getRGB(0,0,imageWidth,imageHeight,null,0,imageWidth);
            buffer  = MemoryUtil.memAlloc(imageWidth * imageHeight * 4);

            for (int y = 0; y < imageHeight; y++){
                for (int x = 0; x < imageWidth; x++){
                    Color color = new Color(pixels[y * imageWidth + x],true);
                    buffer.put((byte) color.getRed());
                    buffer.put((byte) color.getGreen());
                    buffer.put((byte) color.getBlue());
                    buffer.put((byte) color.getAlpha());
                }
            }
            buffer.flip();

            final GLFWImage img = GLFWImage.malloc();
            final GLFWImage.Buffer imagebf = GLFWImage.malloc(1);
            img.set(image.getWidth(), image.getHeight(), buffer);
            imagebf.put(0, img);

            glfwSetWindowIcon(window,imagebf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
