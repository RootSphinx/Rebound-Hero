package org.sphinx.sphinxengine.ui;

import org.sphinx.sphinxengine.engine.*;

import java.awt.*;


public class Lable extends GameObject {
    public Text text;
    Sprite sprite;
    Canvas canvas;
    public Lable(Canvas canvas,int width,int height){
        this.canvas = canvas;
        setParent(canvas);
        text = new Text(width,height);
        text.setStr("",80,0,80);
        sprite = new Sprite(this,text.getTexture(), Sprite.Type.UI);
        sprite.offset = new Vector2D(text.getTexture().getWidth()/2f,text.getTexture().getHeight()/2f);
        this.name = "Lable";
        this.tag = "UI";
    }

    public void setText(String str,int size) {
        this.text.setStr(str,size,0,size);
        sprite.offset = new Vector2D(text.getTexture().getWidth()/2f,text.getTexture().getHeight()/2f+20);
    }
    public void setText(String str, int size, Color color) {
        this.text.setStr(str,size,0,size,color,Color.black);
        sprite.offset = new Vector2D(text.getTexture().getWidth()/2f,text.getTexture().getHeight()/2f);
    }
    public void setLayout(int layout){
        sprite.setLayout(layout);
    }
    @Override
    public void start() {

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
