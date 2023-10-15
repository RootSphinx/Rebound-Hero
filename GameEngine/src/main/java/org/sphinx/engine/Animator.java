package org.sphinx.engine;

import org.sphinx.util.Debug;

import java.util.*;

/**
 * 动画器
 */
public class Animator extends Component{
    /**
     * 动作切换类型
     */
    public enum Policy {
        instant,finished
    }
    public enum Type{
        loop,once
    }
    private final Sprite currentSprite;

    private final Map<String,List<Texture>> texturesMap = new HashMap<>();
    private final Map<String,Double> timeIntervalMap = new HashMap<>();
    private final Map<String, Policy> policyMap = new HashMap<>();
    private final Map<String, Type> typeMap = new HashMap<>();

    private String nextAction = null;
    private String currentAction = null;
    private String lastAction = null;
    private boolean isFinished = false;
    private int currentTexture = 0;
    GameTimer timer = new GameTimer();

    /**
     * 创建一个动画器
     * @param gameObject 加载动画器组件的游戏对象
     * @param sprite 动画器需要控制的精灵
     */
    public Animator(GameObject gameObject,Sprite sprite){
        super(gameObject, "Animator");
        this.currentSprite = sprite;
    }

    /**
     * 创建一个动作
     * @param name 动作的名字
     * @param timeInterval 动作动画的帧间隔
     * @param policy 动作的切换类型
     */
    public void createAction(String name, double timeInterval, Policy policy,Type type){
        texturesMap.put(name,new ArrayList<>());
        mCreateAction(name, timeInterval, policy,type);
    }

    /**
     * 创建一个动作
     * @param name 动作的名字
     * @param timeInterval 动作动画的帧间隔
     * @param policy 动作的切换类型
     * @param textures 指定动作动画的纹理列表
     */
    public void createAction(String name, double timeInterval, Policy policy, Type type,List<Texture> textures){
        texturesMap.put(name,textures);
        mCreateAction(name, timeInterval, policy,type);
    }
    private void mCreateAction(String name, double timeInterval, Policy policy,Type type){
        timeIntervalMap.put(name,timeInterval);
        policyMap.put(name, policy);
        typeMap.put(name,type);
        if (currentAction == null){ nextAction = name; currentAction = name;lastAction = name;}
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
        if(currentAction.equals("sliding")){
            //System.out.println((timer.time > timeIntervalMap.get(currentAction))+" \t\t"+timer.time +" \t\t"+ timeIntervalMap.get(currentAction));
            System.out.println(currentAction+"\t\t"+name);
        }
        if (!texturesMap.containsKey(name) || currentAction.equals(name)) return;
        lastAction = currentAction;
        if (policyMap.get(name) == Policy.instant){
            timer.reset();
            currentTexture = 0;
            currentAction = name;
            nextAction = name;
        }
        else if (policyMap.get(name) == Policy.finished){
            nextAction = name;
        }
        isFinished = false;
    }

    /**
     * 动画器更新
     */
    static void animatorsUpdate(){
        for (List<Component> animators : components.get("Animator").values()){
            for (Component animator : animators){
                ((Animator)animator).update();
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
                    if (++currentTexture == texturesMap.get(currentAction).size()){
                        if (typeMap.get(currentAction)==Type.loop){
                            currentTexture = 0;
                        }
                        else if (typeMap.get(currentAction) == Type.once){
                            currentTexture--;
                            isFinished = true;
                        }
                        System.out.println(currentAction+typeMap.get(currentAction)+isFinished());
                    }
                    //currentTexture =(currentTexture+1) % texturesMap.get(currentAction).size();
                }
                timer.reset();
            }
            else {

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
    static void destroyAllAnimator(){
        Debug.log("动画器----正在释放动画器");
        components.get("Animator").clear();
    }

    /**
     * 销毁当前动画器
     */
    public void destroy(){
        components.get("Animator").remove(gameObject.getId());
    }


    public boolean isFinished() {
        return isFinished;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public String getLastAction() {
        return lastAction;
    }
}
