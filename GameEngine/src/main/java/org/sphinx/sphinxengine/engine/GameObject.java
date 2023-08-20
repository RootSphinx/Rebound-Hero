package org.sphinx.sphinxengine.engine;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
    private static final List<GameObject> gameObjects = new ArrayList<>();
    private int Id;
    public boolean isEnable = true;
    private boolean beforeIsEnable = false;
    private Transform transform;
    public GameObject(){
        gameObjects.add(this);
        start();
    }
    public abstract void start();

    public abstract void update();
    public abstract void enable();
    public abstract void disable();
    public boolean isEnable(){
        return isEnable && beforeIsEnable;
    }

    public static List<GameObject> getGameObjects() {
        return gameObjects;
    }
    protected static void gameObjectsUpdate(){
        for(GameObject gameObject : GameObject.getGameObjects()){
            if (gameObject.isEnable()){
                gameObject.update();
            }
        }
    }
    protected static void gameObjectStatusUpdate(){
        for(GameObject gameObject : GameObject.getGameObjects()){
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
    public Vector2D getPosition(){
        return this.transform.position;
    }
    public float getRotation(){
        return this.transform.rotation;
    }
    public float getScale(){
        return this.transform.scale;
    }
    public void setActive(boolean bool){
        this.isEnable = bool;
    }
}
