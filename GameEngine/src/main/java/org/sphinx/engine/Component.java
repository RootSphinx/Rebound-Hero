package org.sphinx.engine;

import org.sphinx.util.Debug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组件类型接口
 */
public abstract class Component {
    protected final Class<? extends Component> aClass;
    protected final GameObject gameObject;
    protected static final Map<Class<? extends Component>, Map<Integer,List<Component>>> components = new HashMap<>();
    public static void register(Class<? extends Component> clazz){
        components.computeIfAbsent(clazz,k->new HashMap<>());
    }
    protected Component(GameObject gameObject,Class<? extends Component> componentName) {
        this.aClass = componentName;
        this.gameObject = gameObject;
        components.get(componentName).computeIfAbsent(gameObject.getId(),k->new ArrayList<>());
        components.get(componentName).get(gameObject.getId()).add(this);
    }
    protected static List<Component> getComponent(GameObject gameObject, Class<? extends Component> typeName){
        return components.get(typeName).get(gameObject.getId());
    }

    public static void componentUpdate(){
        try {
            for(Class clazz : components.keySet()){
                Method method = clazz.getDeclaredMethod("update");
                method.setAccessible(true);
                method.invoke(null);
            }
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
            Debug.err("Component ERROR : ");
            e.printStackTrace();
        }
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
