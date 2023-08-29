package org.sphinx.sphinxengine.engine;

import java.security.Key;

public abstract class Render {
    private int layout = 0;
    enum Type{
        sprite,drawer
    }
    Type type;
    Render(Type type){
        this.type = type;
        Renderer.renderListAdd(this);
    }
    public void setLayout(int layout){
        this.layout = layout;
    }

    public int getLayout() {
        return layout;
    }
}
