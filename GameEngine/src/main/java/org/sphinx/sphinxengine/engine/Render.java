package org.sphinx.sphinxengine.engine;

import java.security.Key;

public abstract class Render {
    private int layout = 0;
    private final GameObject gameObject;
    enum Type{
        sprite,drawer
    }
    Type type;
    Render(GameObject gameObject,Type type){
        this.type = type;
        Renderer.renderListAdd(this);
        this.gameObject = gameObject;
    }
    public void setLayout(int layout){
        if (layout <= 20 && layout > 0){
            this.layout = layout;
        }
    }

    public int getLayout() {
        return layout;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
    public Drawer toDrawer(){
        return this instanceof Drawer ? (Drawer) this : null;
    }
    public Sprite toSprite(){
        return this instanceof Sprite ? (Sprite) this : null;
    }
}
