package org.sphinx.sphinxengine.engine;

import org.sphinx.sphinxengine.util.Debug;

import java.util.*;

/**
 * 动画器
 */
public class Animator implements Component{
    /**
     * 动作切换类型
     */
    public enum Type{
        instant,finished
    }
    private static final Map<Integer,List<Animator>> ANIMATOR_MAP = new HashMap<>();
    private final GameObject gameObject;
    private final Sprite currentSprite;

    private final Map<String,List<Texture>> texturesMap = new HashMap<>();
    private final Map<String,Double> timeIntervalMap = new HashMap<>();
    private final Map<String,Type> typeMap = new HashMap<>();
    private String nextAction = null;

    private String currentAction = null;
    private int currentTexture = 0;
    GameTimer timer = new GameTimer();

    /**
     * 创建一个动画器
     * @param gameObject 加载动画器组件的游戏对象
     * @param sprite 动画器需要控制的精灵
     */
    public Animator(GameObject gameObject,Sprite sprite){
        this.gameObject = gameObject;
        this.currentSprite = sprite;
        ANIMATOR_MAP.computeIfAbsent(gameObject.getId(), k -> new ArrayList<>());
        ANIMATOR_MAP.get(gameObject.getId()).add(this);
    }

    /**
     * 创建一个动作
     * @param name 动作的名字
     * @param timeInterval 动作动画的帧间隔
     * @param type 动作的切换类型
     */
    public void createAction(String name, double timeInterval,Type type){
        texturesMap.put(name,new ArrayList<>());
        mCreateAction(name, timeInterval, type);
    }

    /**
     * 创建一个动作
     * @param name 动作的名字
     * @param timeInterval 动作动画的帧间隔
     * @param type 动作的切换类型
     * @param textures 指定动作动画的纹理列表
     */
    public void createAction(String name, double timeInterval,Type type, List<Texture> textures){
        texturesMap.put(name,textures);
        mCreateAction(name, timeInterval, type);
    }
    private void mCreateAction(String name, double timeInterval,Type type){
        timeIntervalMap.put(name,timeInterval);
        typeMap.put(name,type);
        if (currentAction == null){ nextAction = name; currentAction = name;}
    }

    /**
     * 为动作添加一个纹理
     * @param name 指定动作的名字
     * @param texture 添加的纹理
     */
    public void addTexture(String name, Texture texture){
        texturesMap.get(name).add(texture);
    }

    /**
     * 设置当前的动作
     * @param name 动作的名字
     */
    public void setAction(String name){
        if (!texturesMap.containsKey(name) || Objects.equals(currentAction, name)) return;

        if (typeMap.get(name) == Type.instant){
            timer.reset();
            currentTexture = 0;
            currentAction = name;
            nextAction = name;
        }
        else if (typeMap.get(name) == Type.finished){
            nextAction = name;
        }
    }

    /**
     * 动画器更新
     */
    protected static void animatorsUpdate(){
        for (List<Animator> animators : ANIMATOR_MAP.values()){
            for (Animator animator : animators){
                animator.update();
            }
        }
    }
    private void update(){
        try {
            if(timer.time > timeIntervalMap.get(currentAction)){
                if (!Objects.equals(nextAction, currentAction)){
                    currentAction = nextAction;
                    currentTexture = 0;
                }
                else {
                    currentTexture =(currentTexture+1) % texturesMap.get(currentAction).size();
                }
                timer.reset();
            }
            currentSprite.setTexture(texturesMap.get(currentAction).get(currentTexture));
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     * 销毁所有动画器
     */
    protected static void destroyAllAnimator(){
        Debug.log("动画器----正在释放动画器");
        ANIMATOR_MAP.clear();
    }

    /**
     * 销毁当前动画器
     */
    public void destroy(){
        ANIMATOR_MAP.remove(gameObject.getId());
    }

    /**
     * 获得加载当前动画器的游戏对象
     * @return 加载当前动画器的游戏对象
     */
    public GameObject getGameObject() {
        return gameObject;
    }

    /**
     * 获得指定游戏对象加载的所有动画器
     * @param id 加载动画器的游戏对象id
     * @return 指定游戏对象加载的所有动画器
     */
    public static List<Animator> getComponent(int id) {
        return ANIMATOR_MAP.get(id);
    }
}
