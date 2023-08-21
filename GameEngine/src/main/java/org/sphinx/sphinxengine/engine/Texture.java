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
        ByteBuffer buffer = null;

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(Texture.class.getResource(path)));
            width = image.getWidth();
            height = image.getHeight();
            int[] pixels =  image.getRGB(0,0,width,height,null,0,width);

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

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
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

}
