package org.sphinx.sphinxengine.test;

import org.sphinx.sphinxengine.engine.*;

public class BackGround1 extends GameObject {
    Sprite sprite;
    GameTimer gameTimer = new GameTimer();
    int fps = 0;

    @Override
    public void start() {
        //this.transform.position = new Vector2D(1000,0);
        sprite = new Sprite(this, "/background.jpg", Sprite.Type.Item);
        sprite.setLayout(1);
        this.name = "BackGround1";
    }

    @Override
    public void update() {
/*        fps++;
        if (gameTimer.time > 1){
            System.out.println(fps / gameTimer.time);
            gameTimer.time-=1;
            fps = 0;
        }*/
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }
}
