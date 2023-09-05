package org.sphinx.sphinxengine.engine;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static String getFileContent(String path) {
        try {
            InputStream stream = Utils.class.getResourceAsStream(path);
            byte[] bytes = stream.readAllBytes();
            return new String(bytes);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static class ObjectPool {
        private final List<GameObject> objectPool = new ArrayList<>();
        public Class<? extends GameObject> clazz;

        public ObjectPool(Class<? extends GameObject> clazz) {
            this.clazz = clazz;
        }

        public ObjectPool(Class<? extends GameObject> clazz, int count) {
            this.clazz = clazz;
            try {
                for (int i = 0; i < count; i++){
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    objectPool.add((GameObject) constructor.newInstance());
                }
            }
            catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }
}