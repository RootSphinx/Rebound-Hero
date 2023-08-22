package org.sphinx.sphinxengine.engine;

import org.joml.Matrix4f;

import static java.lang.Math.*;

public class Transformation {
    private static final Matrix4f matrix = new Matrix4f();

    /**
     *
     * todo 摄像机旋转
     */
    public static Matrix4f getWorldMatrix(Transform transform,Camera camera){
        Vector2D topLeft = new Vector2D(camera.getPosition().x - camera.getWidth()/2f * camera.zoom,
                camera.getPosition().y + camera.getHeight()/2f * camera.zoom);
        Vector2D bottomLeft = new Vector2D(camera.getPosition().x - camera.getWidth()/2f * camera.zoom,
                camera.getPosition().y - camera.getHeight()/2f * camera.zoom);
        Vector2D topRight = new Vector2D(camera.getPosition().x + camera.getWidth()/2f * camera.zoom,
                camera.getPosition().y + camera.getHeight()/2f * camera.zoom);
        Vector2D bottomRight = new Vector2D(camera.getPosition().x + camera.getWidth()/2f * camera.zoom,
                camera.getPosition().y - camera.getHeight()/2f * camera.zoom);

        return matrix.identity()
                .ortho2D(topLeft.rotated(camera.getRotation()).x,
                        bottomRight.rotated(camera.getRotation()).x,
                        bottomLeft.rotated(camera.getRotation()).y,
                        topRight.rotated(camera.getRotation()).y)
                .translate(transform.position.x,transform.position.y,0)
                .rotateZ(transform.rotation - camera.getRotation())
                .scaleXY(transform.scale,transform.scale);
    }
    public static Matrix4f getUIMatrix(GameObject gameObject,Camera camera){
        return matrix.identity()
                .ortho2D(0,camera.getWidth(), camera.getHeight(), 0)
                .translate(gameObject.getPosition().x,gameObject.getPosition().y,0)
                .rotateZ(gameObject.getRotation())
                .scaleXY(gameObject.getScale(),gameObject.getScale());
    }
}
