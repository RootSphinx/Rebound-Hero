package org.sphinx.sphinxengine.engine;

public class Vector2D {
    public float x;
    public float y;
    Vector2D(){
        x = 0;
        y = 0;
    }
    Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }
    /**
     * todo 向量归一化
     *
     */
    public void normalize(){

    }
    public Vector2D normalized(){
        Vector2D tempVector = new Vector2D();
        tempVector.normalize();
        return tempVector;
    }
    /**
     * todo 向量旋转
     *
     */
    public void rotate(float angle){

    }

    public Vector2D rotated(float angle){
        Vector2D tempVector = new Vector2D();
        tempVector.rotate(angle);
        return tempVector;
    }
}
