package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.*;
import org.sphinx.sphinxengine.ui.Button;
import org.sphinx.sphinxengine.ui.Canvas;
import org.sphinx.sphinxengine.ui.Lable;

import java.awt.*;

import static org.lwjgl.opengl.GL30.*;

class TestEvent implements Runnable{

    @Override
    public void run() {
        System.out.println("YESSSSSSSS");
    }
}

public class BackGround extends GameObject {
    //Sprite sprite;
    GameTimer gameTimer = new GameTimer();
    Canvas canvas;
    Lable lable;
    Button button;

    @Override
    public void start() {
        canvas = new Canvas(800,100,400,400);
        canvas.setFillColor(1,1,1,1);
        canvas.setOutLineColor(0,0,0,1);
        lable = new Lable(canvas,400,50);
        lable.setText("你好，世界!",50, Color.black);
        canvas.setLayout(1);
        lable.setLayout(2);
        button = new Button(canvas,380,100);
        button.transform.position = new Vector2D(10,100);
        button.setLayout(3);
        button.setEvent(new TestEvent());
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
