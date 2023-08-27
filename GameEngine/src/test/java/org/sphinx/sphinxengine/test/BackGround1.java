package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.*;

public class BackGround1 extends GameObject {
    Sprite sprite;
    GameTimer gameTimer = new GameTimer();

    @Override
    public void start() {
        //this.transform.position = new Vector2D(1000,0);
        sprite = new Sprite(this, "/background.jpg", Sprite.Type.Item);
        sprite.setLayout(1);
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
