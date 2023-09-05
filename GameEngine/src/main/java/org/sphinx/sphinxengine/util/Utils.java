package org.sphinx.sphinxengine.util;

import org.sphinx.sphinxengine.engine.GameObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具
 */
public class Utils {
    /**
     * 获得指定文件中的内容
     * @param path 文件路径
     * @return 文件内容
     */
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
    /**
     * 对象池
     */
    public static class ObjectPool {
        private final List<GameObject> objectPool = new ArrayList<>();
        private final Class<? extends GameObject> clazz;

        /**
         * 创建一个对象池
         * @param clazz 对象池对象的Class对象
         */
        public ObjectPool(Class<? extends GameObject> clazz) {
            this.clazz = clazz;
        }

        /**
         * 创建一个对象池
         * @param clazz 对象池对象的Class对象
         * @param count 在对象池中生成对象的数量
         */
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
        /**
         * todo 完善对象池
         */
    }
}