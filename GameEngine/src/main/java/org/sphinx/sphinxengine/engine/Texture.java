package org.sphinx.sphinxengine.engine;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL40.*;

public class Texture {
    private static final List<Integer> TEXTURE_LIST = new ArrayList<>();
    private int textureId;
    private int width;
    private int height;

    /**
     * 创建一个纹理
     * @param path 图片的路径
     */
    public Texture(String path){
        createTexture(path,1,1,0);
    }

    /**
     * 创建一个纹理
     * @param path 图片的路径
     * @param row   图片切割的行数
     * @param col   图片切割的列数
     * @param index 返回切割后第几个纹理
     */
    public Texture(String path,int row, int col, int index){
        createTexture(path,row,col,index);
    }
    private void createTexture(String path,int row, int col, int index){
        ByteBuffer buffer = null;

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(Texture.class.getResource(path)));
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            width = imageWidth / row;
            height = imageHeight / col;
            int[] pixels =  image.getRGB((index % row ) * width,(index / col ) * height,width,height,null,0,width);

            buffer  = MemoryUtil.memAlloc(width * height * 4);

            for (int y = 0; y < height; y++){
                for (int x = 0; x < width; x++){
                    Color color = new Color(pixels[y * width + x],true);
                    buffer.put((byte) color.getRed());
                    buffer.put((byte) color.getGreen());
                    buffer.put((byte) color.getBlue());
                    buffer.put((byte) color.getAlpha());
                }
            }
            buffer.flip();
            textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureId);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            TEXTURE_LIST.add(textureId);
        }
        catch (IOException | NullPointerException e){
            e.printStackTrace();
            glDeleteTextures(textureId);
        }
        finally {
            MemoryUtil.memFree(buffer);
        }
    }

    /**
     * @return 图片的宽
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return 图片的高
     */
    public int getHeight() {
        return height;
    }

    /**
     * 绑定纹理
     */
    public void bind(){
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureId);
    }
    /**
     * 解绑纹理
     */
    public void unbind(){
        glDisable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
     * 纹理切割
     * @param path 图片的路径
     * @param row 切割行数
     * @param col 切割列数
     * @return 返回切割后纹理列表
     */
    public static List<Texture> textureSplite(String path,int row, int col){
        List<Texture> textures = new ArrayList<>();
        for (int i = 0; i < row * col; i++){
            textures.add(new Texture(path, row, col,i));
        }
        return textures;
    }
    /**
     * 纹理切割
     * @param path 图片的路径
     * @param row 切割行数
     * @param col 切割列数
     * @param indexes 指定切割的纹理排序
     * @return 返回切割后纹理列表
     */
    public static List<Texture> textureSplite(String path,int row, int col,int ... indexes){
        List<Texture> textures = new ArrayList<>();
        for (int i : indexes){
            textures.add(new Texture(path, row, col,i));
        }
        return textures;
    }
    protected static void destroyAllTexture(){
        Debug.log("纹理----正在释放纹理");
        while (!TEXTURE_LIST.isEmpty()){
            glDeleteTextures(TEXTURE_LIST.get(0));
            TEXTURE_LIST.remove(0);
        }
    }
}
