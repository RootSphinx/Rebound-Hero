package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.*;
import org.sphinx.sphinxengine.ui.Canvas;
import org.sphinx.sphinxengine.ui.Lable;

import java.awt.*;

import static org.lwjgl.opengl.GL30.*;

public class BackGround extends GameObject {
    //Sprite sprite;
    GameTimer gameTimer = new GameTimer();
    Canvas canvas;
    Lable lable;

    @Override
    public void start() {
        canvas = new Canvas(800,100,400,50);
        lable = new Lable(canvas,400,50);
        lable.setText("你好，世界!",50, Color.black);
        canvas.setLayout(1);
        lable.setLayout(2);
    }

    @Override
    public void update() {
        //if (gameTimer.time>5){

            //System.out.println("YESSSSSSSSSSSSSSSSSSS");
        //}
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
}
