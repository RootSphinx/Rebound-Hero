package org.sphinx.sphinxengine.engine;

import javax.lang.model.element.Name;
import java.util.*;

public class Animator {
    public enum Type{
        instant,finished
    }
    private static final List<Animator> animators = new ArrayList<>();
    private final GameObject gameObject;
    private final Sprite currentSprite;

    private final Map<String,List<Texture>> texturesMap = new HashMap<>();
    private final Map<String,Double> timeIntervalMap = new HashMap<>();
    private final Map<String,Type> typeMap = new HashMap<>();
    private String nextAction = null;

    private String currentAction = null;
    private int currentTexture = 0;
    GameTimer timer = new GameTimer();

    public Animator(GameObject gameObject,Sprite sprite){
        this.gameObject = gameObject;
        this.currentSprite = sprite;
        animators.add(this);
    }
    public void createAction(String name, double timeInterval,Type type){
        texturesMap.put(name,new ArrayList<>());
        mCreateAction(name, timeInterval, type);
    }
    public void createAction(String name, double timeInterval,Type type, List<Texture> textures){
        texturesMap.put(name,textures);
        mCreateAction(name, timeInterval, type);
    }
    private void mCreateAction(String name, double timeInterval,Type type){
        timeIntervalMap.put(name,timeInterval);
        typeMap.put(name,type);
        if (currentAction == null){ nextAction = name; currentAction = name;}
    }
    public void addTexture(String name, Texture texture){
        texturesMap.get(name).add(texture);
    }
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
    protected static void animatorsUpdate(){
        for (Animator animator : animators){
            animator.update();
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

    public GameObject getGameObject() {
        return gameObject;
    }
}
