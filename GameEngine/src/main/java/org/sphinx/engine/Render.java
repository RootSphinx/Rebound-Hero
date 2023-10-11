package org.sphinx.engine;

import org.sphinx.util.Debug;

import java.util.*;

public abstract class Render extends Component{
    public enum UsageType{
        UI,Item
    }
    private int layout = 0;
    enum Type{
        sprite, painter
    }
    Type type;
    Render(GameObject gameObject,Type type){
        super(gameObject,"Render");
        this.type = type;
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
    public Painter toPainter(){
        return this instanceof Painter ? (Painter) this : null;
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
    static void destroyAllRender(){
        Debug.log("渲染----开始释放渲染对象");

        components.get("Render").forEach((id,renderList)->{
            renderList.forEach(component->{
                Render render = (Render) component;
                if (Objects.requireNonNull(render.type) == Type.sprite) {
                    render.toSprite().getMesh().destroy();
                }
            });
        });
        components.get("Render").clear();
    }

}
