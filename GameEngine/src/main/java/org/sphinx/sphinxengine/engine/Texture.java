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
    private int textureId;
    private int width;
    private int height;

    /**
     * todo 纹理加载
     *
     */
    public Texture(String path){
        createTexture(path,1,1,0);
    }
    public Texture(String path,int row, int col, int count){
        createTexture(path,row,col,count);
    }
    private void createTexture(String path,int row, int col, int count){
        ByteBuffer buffer = null;
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(Texture.class.getResource(path)));
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            width = imageWidth / row;
            height = imageHeight / col;
            //System.out.println((count / row - 1) * oneWidth);
            System.out.format("%d %d %d\n",count,col,(count / col ) * height);
            //System.out.println((count % col - 1) * oneHeight);
            int[] pixels =  image.getRGB((count % row ) * width,(count / col ) * height,width,height,null,0,width);

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
            buffer.flip();
            textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureId);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        }
        catch (IOException e){
            e.printStackTrace();
            glDeleteTextures(textureId);
        }
        finally {
            MemoryUtil.memFree(buffer);
        }
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void bind(){
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureId);
    }
    public void unbind(){
        glDisable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, 0);
    }
    public static List<Texture> textureSplite(String path,int row, int col){
        List<Texture> textures = new ArrayList<>();
        for (int i = 0; i < row * col; i++){
            textures.add(new Texture(path, row, col,i));
        }
        return textures;
    }
    public static List<Texture> textureSplite(String path,int row, int col,int start, int end){
        List<Texture> textures = new ArrayList<>();
        for (int i = start; i <= end; i++){
            textures.add(new Texture(path, row, col,i));
        }
        return textures;
    }
}
