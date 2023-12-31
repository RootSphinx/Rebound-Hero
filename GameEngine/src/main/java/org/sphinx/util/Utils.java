package org.sphinx.util;

import org.lwjgl.glfw.GLFW;
import org.sphinx.engine.GameObject;
import org.sphinx.engine.GameTimer;
import org.sphinx.engine.Transform;
import org.sphinx.engine.Vector2D;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            Debug.err("文件读取错误",e);
            return "";
        }
    }
    public static void createShaker(GameObject gameObject,int amp,double time){
        new Shaker(gameObject,amp,time);
    }
    public static class Shaker extends GameObject{
        Random random = new Random((long) GLFW.glfwGetTime());
        Vector2D origin;
        GameObject gameObject;
        GameTimer gameTimer = new GameTimer();
        int amp;
        double time;
        private Shaker(GameObject gameObject,int amp,double time){
            this.gameObject = gameObject;
            this.amp = amp;
            this.time = time;
            origin = gameObject.transform.position.multiplied(1);
        }
        @Override
        public void start() {

        }

        @Override
        public void update() {
            if (gameTimer.time < time){
                gameObject.transform.position.x = origin.x + random.nextInt(-amp,amp);
                gameObject.transform.position.y = origin.y + random.nextInt(-amp,amp);
            }
            else {
                transform.position = origin;
                this.destroy();
            }
        }

        @Override
        public void enable() {

        }

        @Override
        public void disable() {

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
            this(clazz,0);
        }

        /**
         * 创建一个对象池
         * @param clazz 对象池对象的Class对象
         * @param count 在对象池中生成对象的数量
         */
        public ObjectPool(Class<? extends GameObject> clazz, int count) {
            this.clazz = clazz;
            createObject(count);
        }
        public void createObject(int count){
            try {
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                for (int i = 0; i < count; i++){
                    GameObject gameObject = (GameObject) constructor.newInstance();
                    gameObject.setActive(false);
                    objectPool.add(gameObject);
                }
            }
            catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
                Debug.err("对象池对象创建失败",e);
            }
        }
        public GameObject get() {
            for (GameObject gameObject : objectPool) {
                if (!gameObject.isEnable()) {
                    gameObject.setActive(true);
                    return gameObject;
                }
            }
            createObject(1);
            return objectPool.get(objectPool.size()-1);
        }
        public void destroyAllObject(){
            for (GameObject gameObject : objectPool){
                gameObject.destroy();
            }
        }
    }
}