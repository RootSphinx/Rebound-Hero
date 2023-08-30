package org.sphinx.sphinxengine.engine;

public class Transform implements Cloneable{
    public Vector2D position = new Vector2D();
    public float rotation = 0;
    public float scale = 1;
    public void addVector(Vector2D vector){
        this.position.x += vector.x;
        this.position.y += vector.y;
    }
    public Transform addedVector(Vector2D vector) {
        Transform transform = null;
        try {
            transform = (Transform) this.clone();
            transform.position.x = this.position.x + vector.x;
            transform.position.y = this.position.y + vector.y;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return transform;
    }
}
