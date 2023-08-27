package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.*;
import static org.lwjgl.opengl.GL30.*;

public class BackGround extends GameObject {
    //Sprite sprite;
    GameTimer gameTimer = new GameTimer();
    Drawer drawer;

    @Override
    public void start() {
        drawer = new Drawer() {
            @Override
            public void draw() {
                glBegin(GL_QUADS);
                glVertex2f(100,100);
                glVertex2f(100,200);
                glVertex2f(200,200);
                glVertex2f(200,100);
                glEnd();
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
