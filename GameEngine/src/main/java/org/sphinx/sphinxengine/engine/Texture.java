package org.sphinx.sphinxengine.engine;
import org.lwjgl.system.MemoryUtil;
import org.sphinx.sphinxengine.util.Debug;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.lwjgl.opengl.GL40.*;

public class Texture {
    private static final List<Integer> TEXTURE_LIST = new ArrayList<>();
    private static final List<Integer> GLOBAL_TEXTURE_LIST = new ArrayList<>();
    private int textureId;
    private int width;
    private int height;

    /**
     * 创建一个纹理
     * @param width 纹理宽
     * @param height 纹理高
     * @param buffer 纹理像素缓冲区
     */
    public Texture(int width,int height,ByteBuffer buffer) {
        this.width =width;
        this.height = height;
        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        glBindTexture(GL_TEXTURE_2D,0);
    }
    /**
     * 创建一个纹理
     * @param path 图片的路径
     */
    public Texture(String path){
        createTexture(path,1,1,0,false);
    }

    /**
     * 创建一个纹理
     * @param path 图片的路径
     * @param globalSign 是否不会被删除
     */
    public Texture(String path,boolean globalSign){
        createTexture(path,1,1,0,globalSign);
    }
    /**
     * 创建一个纹理
     * @param path 图片的路径
     * @param row   图片切割的行数
     * @param col   图片切割的列数
     * @param index 返回切割后第几个纹理
     */
    public Texture(String path,int row, int col, int index){
        createTexture(path,row,col,index,false);
    }

    /**
     * 创建一个纹理
     * @param path 图片的路径
     * @param row   图片切割的行数
     * @param col   图片切割的列数
     * @param index 返回切割后第几个纹理
     * @param globalSign 是否不会被删除
     */
    public Texture(String path,int row, int col, int index,boolean globalSign){
        createTexture(path,row,col,index,globalSign);
    }
    private void createTexture(String path,int row, int col, int index, boolean globalSign){
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
            if (globalSign)
                GLOBAL_TEXTURE_LIST.add(textureId);
            else {
                TEXTURE_LIST.add(textureId);
            }
        }
        catch (IOException | NullPointerException e){
            glDeleteTextures(textureId);
            e.printStackTrace();
        }
        finally {
            MemoryUtil.memFree(buffer);
        }
    }

    /**
     * 更新局部纹理
     * @param x 更新位置的X
     * @param y 更新位置的Y
     * @param width 更新宽度
     * @param height 更新高度
     * @param buffer 更新的缓冲区
     */
    public void updateTexture(int x,int y,int width,int height,ByteBuffer buffer){
        bind();
        glTexSubImage2D(GL_TEXTURE_2D,0,x,y,width,height,GL_RGBA,GL_UNSIGNED_BYTE,buffer);
        unbind();
    }
    /**
     * 更新纹理
     * @param width 更新宽度
     * @param height 更新高度
     * @param buffer 更新的缓冲区
     */
    public void updateTexture(int width,int height,ByteBuffer buffer){
        bind();
        glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,width,height,0,GL_RGBA,GL_UNSIGNED_BYTE,buffer);
        unbind();
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

    static void destroyAllTexture(){
        Debug.log("纹理----正在释放纹理");
        while (!TEXTURE_LIST.isEmpty()){
            glDeleteTextures(TEXTURE_LIST.get(0));
            TEXTURE_LIST.remove(0);
        }
    }
}
