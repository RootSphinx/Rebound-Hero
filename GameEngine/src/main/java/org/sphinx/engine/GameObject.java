package org.sphinx.engine;

import org.sphinx.util.Debug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 游戏对象
 */
public abstract class GameObject {
    private static final List<GameObject> GAME_OBJECT_LIST = new ArrayList<>();
    public static int gameObjectCount = 0;
    private final int Id;
    public String tag;
    public String name;
    private boolean isEnable = true;
    private boolean beforeIsEnable = false;
    public Transform transform = new Transform();
    private GameObject parent = null;

    /**
     * 创建一个游戏对象
     */
    public GameObject(){
        this.Id = gameObjectCount;
        gameObjectCount++;
        GAME_OBJECT_LIST.add(this);
        start();
    }

    /**
     * 当对象被创建时执行
     */
    public abstract void start();
    /**
     * 游戏对象被启用时每一帧被执行
     */
    public abstract void update();

    /**
     * 当游戏对象被启用时执行
     */
    public abstract void enable();

    /**
     * 当游戏对象被禁用时执行
     */
    public abstract void disable();

    /**
     * 游戏对象更新
     */
    static void gameObjectsUpdate(){
        int size = GAME_OBJECT_LIST.size();
        for (int i = 0; i < size; i++){
            if (GAME_OBJECT_LIST.get(i).isEnable()){
                GAME_OBJECT_LIST.get(i).update();
                if (SceneController.isLoadingNextScene){
                    break;
                }
            }
        }
    }

    /**
     * 游戏对象状态更新
     */
    static void gameObjectStatusUpdate(){
        for(GameObject gameObject : GAME_OBJECT_LIST){
            if (gameObject.isEnable != gameObject.beforeIsEnable){
                if (gameObject.isEnable){
                    gameObject.enable();
                }
                else {
                    gameObject.disable();
                }
                gameObject.beforeIsEnable = gameObject.isEnable;
            }
        }
    }
    static void gameObjectsRemoveAll(){
        Debug.log("游戏对象----正在释放游戏对象");
        while (!GAME_OBJECT_LIST.isEmpty()){
            GAME_OBJECT_LIST.get(0).destroy();
        }
    }
    public boolean isEnable(){
        if (parent==null){
            return isEnable && beforeIsEnable;
        }
        else {
            return parent.isEnable() && isEnable && beforeIsEnable;
        }
    }
    public void destroy(){
        Debug.log("\t当前ID : "+ this.Id+" 名字 : "+name+" tag : "+tag+" 正在释放");
        GAME_OBJECT_LIST.remove(this);
        Mesh.destroy(Id);
    }

    public Vector2D getPosition(){
        if (parent == null){
            return new Vector2D(this.transform.position);
        }
        else {
            return parent.getPosition().added(this.transform.position);
        }
    }

    public Transform getTransform() {
        if (parent == null){
            return transform;
        }
        else {
            Transform transform1 = new Transform();
            transform1.position = this.transform.position.added(parent.getTransform().position);
            return transform1;
        }
    }

    public float getRotation(){
        return this.transform.rotation;
    }
    public void addRotation(float add){
        this.transform.rotation += add;
    }
    public float getScale(){
        return this.transform.scale;
    }
    public void setActive(boolean bool){
        this.isEnable = bool;
    }
    public void setParent(GameObject gameObject){
        this.parent = gameObject;
    }
    public GameObject getParent(){
        return parent;
    }
    public static GameObject findGameObject(int id){
        for (GameObject gameObject : GAME_OBJECT_LIST){
            if (gameObject.Id == id){
                return gameObject;
            }
        }
        return null;
    }
    public static GameObject findGameObject(String name){
        for (GameObject gameObject : GAME_OBJECT_LIST){
            if (Objects.equals(gameObject.name, name)){
                return gameObject;
            }
        }
        return null;
    }
    public static List<GameObject> findGameObjects(String tag){
        List<GameObject> gameObjects = new ArrayList<>();
        for (GameObject gameObject : GAME_OBJECT_LIST){
            if (Objects.equals(gameObject.tag, tag)){
                gameObjects.add(gameObject);
            }
        }
        return gameObjects;
    }

/*    public<T extends Component> List<T> getComponent(Class<T> clazz) {
        List<T> tList = null;
        try {
            Method method = clazz.getMethod("getComponent",int.class);
            tList = (List<T>) method.invoke(null,this.Id);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return tList;
    }*/
    public List<Component> getComponent(String typeName) {
        return Component.getComponent(this, typeName);
    }
    public int getId() {
        return Id;
    }
}
