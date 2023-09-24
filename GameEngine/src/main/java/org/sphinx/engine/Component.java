package org.sphinx.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组件类型接口
 */
public abstract class Component {
    protected final String componentName;
    protected final GameObject gameObject;
    protected static final Map<String, Map<Integer,List<Component>>> components = new HashMap<>();
    public static void register(String typeName){
        components.computeIfAbsent(typeName,k->new HashMap<>());
    }
    protected Component(GameObject gameObject,String componentName) {
        this.componentName = componentName;
        this.gameObject = gameObject;
        components.get(componentName).computeIfAbsent(gameObject.getId(),k->new ArrayList<>());
        components.get(componentName).get(gameObject.getId()).add(this);
    }
    protected static List<Component> getComponent(GameObject gameObject, String typeName){
        return components.get(typeName).get(gameObject.getId());
    }
}
