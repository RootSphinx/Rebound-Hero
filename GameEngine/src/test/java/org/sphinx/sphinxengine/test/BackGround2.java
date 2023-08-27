package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.GameObject;
import org.sphinx.sphinxengine.engine.GameTimer;
import org.sphinx.sphinxengine.engine.Sprite;
import org.sphinx.sphinxengine.engine.Vector2D;

public class BackGround2 extends GameObject {
    Sprite sprite;
    GameTimer gameTimer = new GameTimer();

    @Override
    public void start() {
        this.transform.position = new Vector2D(0,0);
        sprite = new Sprite(this, "/sprite.png", Sprite.Type.Item);
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
