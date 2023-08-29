package org.sphinx.sphinxengine.engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Text {
    static File fontFile;
    static Font customFont;
    int width ,height;
    Texture texture;
    public Text(int width, int height){
       this.width = width;
       this.height = height;
       texture = new Texture(width,height,null);
    }
    public static void init() {
        try {
            fontFile = new File(Text.class.getResource("/STKAITI.TTF").getPath().toString().substring(1));
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        }
        catch (IOException | FontFormatException e){
            e.printStackTrace();
        }
    }
    public void setStr(String str, float size, int x, int y) {
        texture.updateTexture(width,height,getBuffer(str,size,x,y,width,height,Color.WHITE,Color.black));
    }
    public void setStr(String str, float size, int x, int y,Color frontColor,Color backColor) {
        texture.updateTexture(width,height,getBuffer(str,size,x,y,width,height,frontColor,backColor));
    }
    private static ByteBuffer getBuffer(String str,float size,int x,int y,int width,int height,Color frontColor,Color backColor){
        Font font = customFont.deriveFont(size); // 设置字体大小和样式
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(font);
        g2d.setColor(frontColor);
        g2d.setBackground(backColor);
        g2d.drawString(str, x, (int)(y*0.8));
        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
        ByteBuffer buffer = ByteBuffer.allocateDirect(width * height * 4);
        for (int iy = 0; iy < height; iy++) {
            for (int ix = 0; ix < width; ix++) {
                int pixel = pixels[iy * width + ix];
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // 红色分量
                buffer.put((byte) ((pixel >> 8) & 0xFF)); // 绿色分量
                buffer.put((byte) (pixel & 0xFF)); // 蓝色分量
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha分量
            }
        }
        buffer.flip();
        return buffer;
    }
    public Texture getTexture() {
        return texture;
    }
    public static ByteBuffer getCharBuffer(String str,int size){
        return getBuffer(str,size,0,size,size,size,Color.WHITE,Color.black);
    }
    public static ByteBuffer getCharBuffer(String str,int size,Color color){
        return getBuffer(str,size,0,size,size,size,color,Color.black);
    }
}