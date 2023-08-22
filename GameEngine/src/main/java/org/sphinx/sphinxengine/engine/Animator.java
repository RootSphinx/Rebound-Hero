package org.sphinx.sphinxengine.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animator {
    private final GameObject gameObject;
    private final Sprite targetSprite;
    private final Map<String,List<Texture>> actions = new HashMap<>();
    public Animator(GameObject gameObject,Sprite sprite){
        this.gameObject = gameObject;
        this.targetSprite = sprite;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
