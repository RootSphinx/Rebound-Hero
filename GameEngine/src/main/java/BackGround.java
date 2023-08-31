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
    Canvas canvas1;
    Lable lable;
    Dialog dialog;
    Button button;

    static class TestEvent implements Runnable{
        static Dialog dialog;
        static Canvas canvas;
        static boolean sign = false;
        TestEvent(Canvas canvas,Dialog dialog){
            TestEvent.canvas = canvas;
            TestEvent.dialog = dialog;
        }
        @Override
        public void run() {
            if (!sign){
                canvas.setActive(true);
                dialog.setText("你好，世界。just monika just monika just monika just monika just monika just monika just monika just monika just monika .",30);
            }
            else {
                canvas.setActive(false);
            }
            sign = !sign;
        }
    }

    @Override
    public void start() {
        this.name = "BackGround";

        canvas = new Canvas(30,450,WindowController.getInstance().getWindowWidth() - 60, 330);
        canvas.setColor(new Color(0,0,0,180));

        lable = new Lable(canvas,200,40);
        lable.setText("Player",40,Color.white);
        lable.transform.position = new Vector2D(20,10);
        dialog = new Dialog(canvas,WindowController.getInstance().getWindowWidth() - 120,280);
        dialog.transform.position = new Vector2D(30,50);
        dialog.setText("你好,世界",30);

        canvas1 = new Canvas(1000,20,100,100);
        button = new Button(canvas1,300,100);
        button.lable.setText("开始",30,Color.black,true);
        button.setEvent(new TestEvent(canvas ,dialog));
        canvas.setActive(false);
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
