package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.*;
import org.sphinx.sphinxengine.ui.Canvas;

import static org.lwjgl.opengl.GL30.*;

public class BackGround extends GameObject {
    //Sprite sprite;
    GameTimer gameTimer = new GameTimer();
    Canvas canvas;
    Canvas canvas1;
    Canvas canvas2;

    @Override
    public void start() {
        canvas = new Canvas(20,20,200,300);
        canvas.setColor(0, 1,0,1);
        canvas1 = new Canvas(canvas,10,10,200,200);
        canvas2 = new Canvas(canvas1,10,10,200,200);
        canvas2.setColor(1,1,0,1);
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
