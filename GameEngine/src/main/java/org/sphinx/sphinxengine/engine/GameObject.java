package org.sphinx.sphinxengine.engine;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
    private static final List<GameObject> GAME_OBJECT_LIST = new ArrayList<>();
    public static int gameObjectCount = 0;
    private final int Id;
    public String tag;
    public String name;
    public boolean isEnable = true;
    private boolean beforeIsEnable = false;
    public Transform transform = new Transform();
    private GameObject parent = null;
    public GameObject(){
        this.Id = gameObjectCount;
        gameObjectCount++;
        GAME_OBJECT_LIST.add(this);
        start();
    }
    public abstract void start();

    public abstract void update();
    public abstract void enable();
    public abstract void disable();

    protected static void gameObjectsUpdate(){
        for(GameObject gameObject : GameObject.getGameObjects()){
            if (gameObject.isEnable()){
                gameObject.update();
            }
        }
    }
    protected static void gameObjectStatusUpdate(){
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
    protected static void gameObjectsRemoveAll(){
        while (!GAME_OBJECT_LIST.isEmpty()){
            GAME_OBJECT_LIST.get(0).destroy();
        }
    }
    public boolean isEnable(){
        return isEnable && beforeIsEnable;
    }
    public void destroy(){
        GAME_OBJECT_LIST.remove(this);
        Mesh.destroy(Id);
    }

    public static List<GameObject> getGameObjects() {
        return GAME_OBJECT_LIST;
    }
    public Vector2D getPosition(){
        return this.transform.position;
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

    public int getId() {
        return Id;
    }
}
