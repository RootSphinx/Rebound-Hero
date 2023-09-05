package org.sphinx.sphinxengine.engine;

import java.security.Key;
import java.util.*;

public abstract class Render implements Component{
    protected static final Map<Integer,List<Render>> RENDER_MAP = new HashMap<>();
    private int layout = 0;
    private final GameObject gameObject;
    enum Type{
        sprite,drawer
    }
    Type type;
    Render(GameObject gameObject,Type type){
        this.type = type;
        RENDER_MAP.computeIfAbsent(gameObject.getId(), key->new ArrayList<>());
        RENDER_MAP.get(gameObject.getId()).add(this);
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
        RENDER_MAP.forEach((id,renderList)->{
            renderList.forEach(render->{
                if (Objects.requireNonNull(render.type) == Type.sprite) {
                    render.toSprite().getMesh().destroy();
                }
            });
        });
        RENDER_MAP.clear();
    }
    public static List<Render> getComponent(int id) {
        return RENDER_MAP.get(id);
    }
}
