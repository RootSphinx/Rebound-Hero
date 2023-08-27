package org.sphinx.sphinxengine.ui;

import org.sphinx.sphinxengine.engine.Drawer;
import org.sphinx.sphinxengine.engine.GameObject;
import org.sphinx.sphinxengine.engine.Vector2D;

import java.awt.*;

public class Canvas extends GameObject {
    private Drawer drawer;
    private int width = 0;
    private int height = 0;
    public Color color =new Color(1,1,1,1);
    public Canvas(int x, int y, int width, int height){
        this(null,x,y,width,height);
    }
    public Canvas(Canvas canvas, int x, int y, int width, int height){
        this.setParent(canvas);
        this.transform.position = new Vector2D(x, y);
        this.width = width;
        this.height = height;
        this.name = "Canvas";
        this.tag = "UI";
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void setColor(float red, float green, float blue, float alpha) {
        this.color = new Color(red, green, blue, alpha);
    }
    @Override
    public void start() {
        drawer = new Drawer(this) {
            @Override
            public void draw() {
                this.setColor(color);
                drawQuads(getPosition().x, getPosition().y, width, height);
            }
        };
    }

    @Override
    public void update() {

    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
}
