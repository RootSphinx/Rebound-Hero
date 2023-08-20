package org.sphinx.sphinxengine.engine;

public class Camera extends GameObject{
    private Transform transform;
    private int width;
    private int height;
    private float zoom = 1;
    public Transformation transformation = new Transformation();
    public Camera(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getZoom() {
        return zoom;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
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
