package org.sphinx.sphinxengine.engine;

import static org.lwjgl.opengl.GL30.*;

import java.awt.*;

public abstract class Drawer extends Render{
    public enum Type{
        UI,Item
    }
    Type type = Type.UI;
    private final GameObject gameObject;
    private Color color = new Color(1,1,1,1);
    public Drawer(GameObject gameObject){
        super(Render.Type.drawer);
        this.gameObject = gameObject;
        Renderer.drawerListAdd(this);
    }
    public abstract void draw();

    protected void drawQuads(float x, float y, int width, int height){
        glBegin(GL_QUADS);
        glVertex2f(x,y);
        glVertex2f(x + width,y);
        glVertex2f(x + width,y + height);
        glVertex2f(x,y + height);
        glEnd();
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setColor(float red, float green, float blue, float alpha) {
        this.color = new Color(red, green, blue, alpha);
    }

    public Color getColor() {
        return color;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
