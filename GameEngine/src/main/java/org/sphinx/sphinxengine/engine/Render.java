package org.sphinx.sphinxengine.engine;

import org.sphinx.sphinxengine.util.Debug;

import java.util.*;

public abstract class Render implements Component{
    public enum UsageType{
        UI,Item
    }
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

    /**
     * 设置当前渲染对象的图层
     * @param layout 图层
     */
    public void setLayout(int layout){
        if (layout <= 20 && layout > 0){
            this.layout = layout;
        }
    }

    /**
     * 获得当前渲染对象的图层
     * @return 当前渲染对象的图层
     */
    public int getLayout() {
        return layout;
    }

    /**
     * 获得加载当前渲染对象的游戏对象
     * @return 加载当前渲染对象的游戏对象
     */
    public GameObject getGameObject() {
        return gameObject;
    }

    /**
     * 将当前渲染对象转化为绘画器
     * @return 转化后的绘画器
     */
    public Drawer toDrawer(){
        return this instanceof Drawer ? (Drawer) this : null;
    }
    /**
     * 将当前渲染对象转化为精灵
     * @return 转化后的精灵
     */
    public Sprite toSprite(){
        return this instanceof Sprite ? (Sprite) this : null;
    }

    /**
     * 销毁所有渲染对象
     */
    protected static void destroyAllRender(){
        Debug.log("渲染----开始释放渲染对象");
        RENDER_MAP.forEach((id,renderList)->{
            renderList.forEach(render->{
                if (Objects.requireNonNull(render.type) == Type.sprite) {
                    render.toSprite().getMesh().destroy();
                }
            });
        });
        RENDER_MAP.clear();
    }

    /**
     * 获得指定游戏对象加载的所有渲染对象
     * @param id 加载渲染对象的游戏对象id
     * @return 指定游戏对象加载的所有渲染对象
     */
    public static List<Render> getComponent(int id) {
        return RENDER_MAP.get(id);
    }
}
