package org.sphinx.sphinxengine.ui;

import org.sphinx.sphinxengine.engine.GameTimer;
import org.sphinx.sphinxengine.engine.Text;

import java.awt.*;

public class Dialog extends Canvas{
    private Color fillColor = new Color(0,0,0,0);
    private Color wordColor = new Color(255,255,255,255);
    Lable lable;
    int size = 30;
    int index = 0,indexX = 0,line = 0;
    double timeInterval = 0.1;
    String str = "";
    GameTimer timer = new GameTimer();
    public Dialog(Canvas canvas,int width, int height) {
        super(canvas,0, 0, width, height);
        this.name = "Dialog";
        setColor(fillColor);
        lable = new Lable(this,width,height);
        lable.setText("你好Hello",30,Color.white);
    }
    public void setText(String str){
        this.str = str;
        index = 0;
        indexX = 0;
        line = 0;
        lable.setText("",30,Color.white);
    }
    public void setColor(Color wordColor,Color fillColor){
        this.wordColor = wordColor;
        this.fillColor = fillColor;
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
                String ch = str.substring(index,++index);
                if (indexX + Text.getCharWidth(ch.toCharArray()[0], size) >= getSize().x-5){
                    newLine();
                }
                lable.text.getTexture().updateTexture(indexX,line * size,size,size,Text.getCharBuffer(ch,size,wordColor));
                indexX+=Text.getCharWidth(ch.toCharArray()[0], size);
            }
        }
    }
}
