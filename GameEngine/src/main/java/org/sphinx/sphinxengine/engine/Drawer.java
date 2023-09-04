package org.sphinx.sphinxengine.engine;

import static org.lwjgl.opengl.GL30.*;

import java.awt.*;

public abstract class Drawer extends Render{
    public enum Type{
        UI,Item
    }
    Type type = Type.UI;
    private Color fillColor = new Color(1,1,1,1);
    private Color outLineColor = new Color(0,0,0,1);
    private int outLineWidth = 2;

    public Drawer(GameObject gameObject){
        super(gameObject,Render.Type.drawer);
    }
    public abstract void draw();

    protected void drawQuads(float x, float y, float width, float height){
        setColor(fillColor);
        glBegin(GL_QUADS);
        glVertex2f(x,y);
        glVertex2f(x + width,y);
        glVertex2f(x + width,y + height);
        glVertex2f(x,y + height);
        glEnd();
    }
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
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public void setFillColor(float red, float green, float blue, float alpha) {
        this.fillColor = new Color(red, green, blue, alpha);

    }
    public void setOutLineWidth(int width){
        this.outLineWidth = width;
    }

    public int getOutLineWidth() {
        return outLineWidth;
    }

    public void setOutLineColor(Color color) {
        this.outLineColor = color;
    }
    private void setColor(Color color){
        ShaderProgram.defaultShader.setUniform("UIcolor", color);
    }
}
