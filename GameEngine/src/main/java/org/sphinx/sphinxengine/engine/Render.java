package org.sphinx.sphinxengine.engine;

import java.util.*;

public abstract class Render implements Component{
    protected static final Map<Integer,Render> RENDER_MAP = new HashMap<>();
    private int layout = 0;
    private final GameObject gameObject;
    enum Type{
        sprite,drawer
    }
    Type type;
    Render(GameObject gameObject,Type type){
        this.type = type;
        RENDER_MAP.put(gameObject.getId(),this);

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
    protected static void destroyAllRender(){
        Debug.log("渲染器----开始释放精灵");
        RENDER_MAP.forEach((id,render)->{
            if (Objects.requireNonNull(render.type) == Type.sprite) {
                render.toSprite().getMesh().destroy();
            }
        });
        RENDER_MAP.clear();
    }
    public static Render getComponent(int id) {
        return RENDER_MAP.get(id);
    }
}
