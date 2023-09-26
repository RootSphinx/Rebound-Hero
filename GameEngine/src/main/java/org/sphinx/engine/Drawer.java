package org.sphinx.engine;

import static org.lwjgl.opengl.GL30.*;

import java.awt.*;

/**
 * 绘画器
 */
public abstract class Drawer extends Render{
    public UsageType type = UsageType.UI;
    private Color fillColor = new Color(1,1,1,1);
    private Color outLineColor = new Color(0,0,0,1);
    private int outLineWidth = 2;

    /**
     * 创建一个绘画器
     * @param gameObject 加载该绘画器的游戏对象
     */
    public Drawer(GameObject gameObject){
        super(gameObject,Render.Type.drawer);
    }

    /**
     * 绘画
     */
    public abstract void draw();

    /**
     * 绘制一个矩形
     * @param x 矩形的x坐标
     * @param y 矩形的y坐标
     * @param width 矩形宽度
     * @param height 矩形高度
     */
    protected void drawQuads(float x, float y, float width, float height){
        setColor(fillColor);
        glBegin(GL_QUADS);
        glVertex2f(x,y);
        glVertex2f(x + width,y);
        glVertex2f(x + width,y + height);
        glVertex2f(x,y + height);
        glEnd();
    }

    /**
     * 绘制矩形外框线
     * @param x 矩形的x坐标
     * @param y 矩形的y坐标
     * @param width 矩形宽度
     * @param height 矩形高度
     */
    protected void drawQuadsOutLine(float x, float y, float width, float height){
        setColor(outLineColor);
        glLineWidth(outLineWidth);
        glBegin(GL_LINE_LOOP);
        glVertex2f(x,y);
        glVertex2f(x + width,y);
        glVertex2f(x + width,y + height);
        glVertex2f(x,y + height);
        glEnd();
    }
    protected void drawCircle(float x, float y,float r){
        setColor(fillColor);
        glBegin(GL_POLYGON);
        for (int i = 0; i < 80; i++) {
            glVertex2d(r*Math.cos(2 * Math.PI*i / 80), r*Math.sin(2 *  Math.PI*i / 80));
        }
        glEnd();
    }
    protected void drawCircleOutLine(float x, float y,float r){
        setColor(outLineColor);
        glLineWidth(outLineWidth);
        glBegin(GL_LINE_LOOP);
        for (int i = 0; i < 80; i++) {
            glVertex2d(r*Math.cos(2 * Math.PI*i / 80)+x, r*Math.sin(2 *  Math.PI*i / 80)+y);
        }
        glEnd();
    }
    /**
     * 设置绘画器的填充色
     * @param color 填充色
     */
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    /**
     * 设置绘画器的填充色
     *   范围0-1
     * @param red 红色
     * @param green 绿色
     * @param blue 蓝色
     * @param alpha alpha
     */
    public void setFillColor(float red, float green, float blue, float alpha) {
        this.fillColor = new Color(red, green, blue, alpha);

    }

    /**
     * 设置外框线宽度
     * @param width 宽度
     */
    public void setOutLineWidth(int width){
        this.outLineWidth = width;
    }

    /**
     * 获得外框线的宽度
     * @return 宽度
     */
    public int getOutLineWidth() {
        return outLineWidth;
    }

    /**
     * 设置外框线的绘制颜色
     * @param color 颜色
     */
    public void setOutLineColor(Color color) {
        this.outLineColor = color;
    }
    private void setColor(Color color){
        ShaderProgram.defaultShader.setUniform("UIcolor", color);
    }
}
