package org.sphinx.sphinxengine.engine;

public class Transform {
    public Vector2D position = new Vector2D();
    public float rotation = 0;
    public float scale = 1;
    public void addVector(Vector2D vector){
        this.position.x += vector.x;
        this.position.y += vector.y;
    }
}
