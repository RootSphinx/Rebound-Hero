package org.sphinx.sphinxengine.engine;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
    private static final List<GameObject> gameObjects = new ArrayList<>();
    private int Id;
    public String tag;
    public String name;
    public boolean isEnable = true;
    private boolean beforeIsEnable = false;
    public Transform transform = new Transform();
    public GameObject(){
        gameObjects.add(this);
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
        for(GameObject gameObject : gameObjects){
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
        for (GameObject gameObject : gameObjects){
            gameObject.destroy();
        }
    }
    public boolean isEnable(){
        return isEnable && beforeIsEnable;
    }
    public void destroy(){
        gameObjects.remove(this);
    }

    public static List<GameObject> getGameObjects() {
        return gameObjects;
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
