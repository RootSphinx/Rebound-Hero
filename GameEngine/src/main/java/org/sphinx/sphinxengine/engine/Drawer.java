package org.sphinx.sphinxengine.engine;

public abstract class Drawer {
    public enum Type{
        UI,Item
    }
    Type type = Type.UI;
    public Drawer(){
        Renderer.drawerListAdd(this);
    }
    public abstract void draw();
}
