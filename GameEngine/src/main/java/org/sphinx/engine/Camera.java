package org.sphinx.engine;

/**
 * 摄像机对象
 */
public class Camera extends GameObject{
    private int width;
    private int height;

    private float zoom = 1;

    /**
     * 创建一个摄像机对象
     * @param width 摄像机的视野宽度
     * @param height 摄像机的视野高度
     */
    public Camera(int width, int height){
        this.width = width;
        this.height = height;
        tag = "Camera";
        name = "Camera";
    }

    /**
     * 获得摄像机的视野宽度
     * @return 摄像机的视野宽度
     */
    public int getWidth() {
        return width;
    }
    /**
     * 获得摄像机的视野高度
     * @return 摄像机的视野高度
     */
    public int getHeight() {
        return height;
    }

    /**
     * 设置当前摄像机的缩放倍率
     * @param zoom 缩放倍率
     */
    public void setZoom(float zoom) {
        if (zoom < 0){
            this.zoom=0;
        }
        else {
            this.zoom = zoom;
        }
    }

    /**
     * 在当前缩放倍率的基础上加上新的缩放倍率值
     * @param add 被加缩放倍率
     */
    public void addZoom(float add) {
        zoom+=add;
        if (zoom < 0.000001){
            zoom = 0.000001f;
        }
    }

    /**
     * 返回当前摄像机缩放倍率
     * @return 缩放倍率
     */
    public float getZoom() {
        return zoom;
    }
    @Override
    public void start() {
        System.out.println("camera.start()");
    }

    @Override
    public void update() {
        //System.out.println("camera.update()");
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }


}
