package org.sphinx.sphinxengine.engine;

import org.joml.Matrix4f;

public class Transformation {
    private final Matrix4f matrix = new Matrix4f();
    public Matrix4f getWorldMatrix(GameObject gameObject,Camera camera){

        return matrix.identity()
                .ortho2D(camera.getPosition().x - camera.getWidth()/2f,camera.getPosition().x + camera.getWidth()/2f,
                        camera.getPosition().y + camera.getHeight()/2f, camera.getPosition().y - camera.getHeight()/2f)
                .translate(gameObject.getPosition().x,gameObject.getPosition().y,0)
                .rotateZ(gameObject.getRotation() - camera.getRotation())
                .scaleXY(gameObject.getScale() * camera.getScale(),gameObject.getScale() * camera.getScale());
    }
    public Matrix4f getUIMatrix(GameObject gameObject,Camera camera){
        return matrix.identity()
                .ortho2D(0,camera.getWidth(), camera.getHeight(), 0)
                .translate(gameObject.getPosition().x,gameObject.getPosition().y,0)
                .rotateZ(gameObject.getRotation())
                .scaleXY(gameObject.getScale(),gameObject.getScale());
    }
}
