package org.sphinx.sphinxengine.ui;

import org.sphinx.sphinxengine.engine.GameTimer;
import org.sphinx.sphinxengine.engine.Text;

import java.awt.*;

public class Dialog extends Canvas{
    public Color wordColor = new Color(255,255,255,255);
    Lable lable;
    public int size = 30;
    int index = 0,indexX = 0,line = 0;
    double timeInterval = 0.05;
    String str = "";
    GameTimer timer = new GameTimer();
    public Dialog(Canvas canvas,int width, int height) {
        super(canvas,0, 0, width, height);
        this.name = "Dialog";
        fillColor = new Color(0,0,0,0);
        setColor(fillColor);
        lable = new Lable(this,width+10,height);
    }
    public void setText(String str,int size){
        this.str = str;
        this.size = size;
        index = 0;
        indexX = 0;
        line = 0;
        lable.setText("",1,Color.white);
    }
    public void newLine(){
        indexX = 0;
        line++;
    }
    @Override
    public void update(){
        if (timer.time > timeInterval){
            timer.reset();
            if (index < str.length()){
                char ch = str.charAt(index++);
                if (indexX + Text.getCharWidth(ch, size) >= getSize().x)
                    newLine();
                lable.text.getTexture().updateTexture(indexX,line * size,Text.getCharWidth(ch, size),size,Text.getCharBuffer(ch,size,wordColor));
                indexX+=Text.getCharWidth(ch, size);
            }
        }
    }
}
