package org.sphinx.sphinxengine.ui;

import org.sphinx.sphinxengine.engine.GameObject;

public abstract class UI extends GameObject {
    Canvas canvas;
    public int layout = 15;
    UI(Canvas canvas){
        this.canvas = canvas;
        setParent(canvas);
        this.tag = "UI";
    }

    public void resetLayout() {
        if (canvas != null){
            this.layout = Math.min(canvas.layout + 1, 20);
        }
    }
}
