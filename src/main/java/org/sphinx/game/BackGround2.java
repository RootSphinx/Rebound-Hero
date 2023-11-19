package org.sphinx.game;

import org.sphinx.engine.*;
import org.sphinx.ui.Canvas;
import org.sphinx.ui.Lable;

import java.awt.*;

public class BackGround2 extends GameObject implements Collision{
    Sprite sprite;
    Rigidbody rigidbody;
    BoxCollider collider;
    BoxCollider collider1;
    BoxCollider collider2;
    Lable lable;
    Canvas canvas;
    GameTimer gameTimer;
    int frame = 0;
    @Override
    public void start() {
        this.name = "BackGround2";
        this.tag = "floor";
        sprite = new Sprite(this,"/Image/BackGround/ThirdMap.png", Render.UsageType.Item);
        transform.scale = new Vector2D(2,2);
        rigidbody = new Rigidbody(this);
        rigidbody.setGravity(false);
        collider = new BoxCollider(this,rigidbody,sprite.getTexture().getWidth() * transform.scale.x,
                sprite.getTexture().getHeight() * transform.scale.y);
        gameTimer = new GameTimer();
        canvas = new Canvas(0,0,30,30);
        canvas.setColor(new Color(0,0,0,0));
        lable = new Lable(canvas,100,20);

        collider1 = new BoxCollider(this,rigidbody,new Vector2D(-sprite.getTexture().getWidth()/2 * transform.scale.x -400,1000),800,2000);
        collider2 = new BoxCollider(this,rigidbody,new Vector2D(+sprite.getTexture().getWidth()/2 * transform.scale.x +400,1000),800,2000);
    }

    @Override
    public void update() {
        frame++;
        if (gameTimer.time > 1){
            lable.setText("FPS : "+ (int)(frame / gameTimer.time),20, Color.white);
            gameTimer.time-=1;
            frame = 0;
        }
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void onTriggerEnter(Collider collider) {

    }

    @Override
    public void onTriggerUpdate(Collider collider) {

    }

    @Override
    public void onTriggerExit(Collider collider) {

    }

    @Override
    public void onCollisionEnter(Collider collider) {

    }
}
