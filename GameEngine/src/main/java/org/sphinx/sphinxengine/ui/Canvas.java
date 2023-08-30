package org.sphinx.sphinxengine.ui;

import org.sphinx.sphinxengine.engine.Drawer;
import org.sphinx.sphinxengine.engine.Vector2D;

import java.awt.*;

public class Canvas extends UI {
    private Drawer drawer;
    private int width = 0;
    private int height = 0;
    public Color fillColor =new Color(1,1,1,1);
    public Color outLineColor =new Color(0,0,0,1);
    private boolean isOutLine = false;
    public Canvas(int x, int y, int width, int height){
        this(null,x,y,width,height);
    }
    public Canvas(Canvas canvas, int x, int y, int width, int height){
        super(canvas);
        this.transform.position = new Vector2D(x, y);
        this.width = width;
        this.height = height;
        this.name = "Canvas";
        resetLayout();
    }

    public void setColor(Color color) {
        this.fillColor = color;
    }
    public void setFillColor(float red, float green, float blue, float alpha) {
        this.fillColor = new Color(red, green, blue, alpha);
    }
    public void setOutLineColor(float red, float green, float blue, float alpha) {
        this.outLineColor = new Color(red, green, blue, alpha);
    }
    public void setLayout(int layout){
        this.layout = layout;
        drawer.setLayout(this.layout);
    }

    public void setOutLine(boolean outLine) {
        isOutLine = outLine;
    }

    @Override
    public void start() {
        drawer = new Drawer(this) {
            @Override
            public void draw() {
                this.setFillColor(fillColor);
                this.setOutLineColor(outLineColor);
                drawQuads(getPosition().x, getPosition().y, width, height);
                if (isOutLine){
                    drawQuadsOutLine(getPosition().x, getPosition().y, width, height);
                }
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
