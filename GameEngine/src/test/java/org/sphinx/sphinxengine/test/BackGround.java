package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.*;
import org.sphinx.sphinxengine.ui.Button;
import org.sphinx.sphinxengine.ui.Canvas;
import org.sphinx.sphinxengine.ui.Dialog;
import org.sphinx.sphinxengine.ui.Lable;

import java.awt.*;



public class BackGround extends GameObject {
    //Sprite sprite;
    GameTimer gameTimer = new GameTimer();
    Canvas canvas;
    Lable lable;
    Button button;
    static class TestEvent implements Runnable{
        Dialog dialog;
        @Override
        public void run() {
           dialog.setText("你好，世界。just monika just monika just monika just monika just monika just monika just monika just monika just monika .");
        }
    }

    @Override
    public void start() {
        this.name = "BackGround";
        canvas = new Canvas(800,100,400,600);
        canvas.setFillColor(1,1,1,1);
        canvas.setOutLineColor(0,0,0,1);
        lable = new Lable(canvas,400,50);
        lable.setText("Hello,world",50, Color.black);
        button = new Button(canvas,380,100);
        button.transform.position = new Vector2D(10,100);
        button.lable.setText("开始",30,Color.black,true);
        Dialog dialog = new Dialog(canvas,400,300);
        dialog.setColor(Color.BLACK,new Color(0,0,0,0));
        dialog.transform.position = new Vector2D(0,300);
        TestEvent testEvent = new TestEvent();
        testEvent.dialog =dialog;
        button.setEvent(testEvent);
        Player player = new Player();
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
