package org.sphinx.sphinxengine.engine;

import static java.lang.Math.*;

public class Vector2D {
    public float x;
    public float y;
    public Vector2D(){
        x = 0;
        y = 0;
    }
    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }
    /**
     * todo 向量归一化
     *
     */
    public void normalize(){
        float rad = (float)atan2(y,x);
        x = x!=0?(float)cos(rad):0;
        y = (float)sin(rad);
    }
    public Vector2D normalized(){
        Vector2D tempVector = new Vector2D(x,y);
        tempVector.normalize();
        return tempVector;
    }
    /**
     * todo 向量旋转
     *
     */
    public void multiply(float num){
        this.x *= num;
        this.y *= num;
    }
    public Vector2D multiplied(float num){
        Vector2D tempVector = new Vector2D(x,y);
        tempVector.multiply(num);
        return tempVector;
    }
    public float getDistance(Vector2D vec){
        return (float) sqrt(pow(vec.x-x, 2)+pow(vec.y-y, 2));
    }
    public float getLength(){
        return this.getDistance(new Vector2D(0,0));
    }
    public void rotate(float angle){
        float length = getLength();
        float rad = (float) atan2(y,x);
        x = x!=0? (float) cos(rad+angle):0;
        y = (float) sin(rad+angle);
        this.multiply(length);
    }

    public Vector2D rotated(float angle){
        Vector2D tempVector = new Vector2D(x,y);
        tempVector.rotate(angle);
        return tempVector;

    }
    @Override
    public String toString(){
        return this.x+" "+this.y;
    }
}
