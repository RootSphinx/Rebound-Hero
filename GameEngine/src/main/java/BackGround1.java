import org.sphinx.engine.GameObject;
import org.sphinx.engine.GameTimer;
import org.sphinx.engine.Render;
import org.sphinx.engine.Sprite;
import org.sphinx.ui.Canvas;
import org.sphinx.ui.Lable;

import java.awt.*;

public class BackGround1 extends GameObject {
    Sprite sprite;
    Lable lable;
    Canvas canvas;
    GameTimer gameTimer = new GameTimer();
    int frame = 0;

    @Override
    public void start() {
        sprite = new Sprite(this, "/background.jpg", Render.UsageType.Item);
        sprite.setLayout(1);
        this.name = "BackGround1";
        canvas = new Canvas(0,0,30,30);
        canvas.setColor(new Color(0,0,0,0));
        lable = new Lable(canvas,100,20);
    }

    @Override
    public void update() {
        frame++;
        if (gameTimer.time > 1){
            lable.setText("FPS : "+ (int)(frame / gameTimer.time),20, Color.white);
            //System.out.println("FPS : "+ (int)(frame / gameTimer.time));
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
}
