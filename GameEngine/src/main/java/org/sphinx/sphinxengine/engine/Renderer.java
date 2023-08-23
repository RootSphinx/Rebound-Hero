package org.sphinx.sphinxengine.engine;
import org.lwjgl.opengl.GL30;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL40.*;


public class Renderer {
    private static Camera activeCamera = null;
    private static final List<Sprite> spriteList = new ArrayList<>();

    public static void setActiveCamera(Camera activeCamera) {
        Renderer.activeCamera = activeCamera;
    }

    public static Camera getActiveCamera() {
        return activeCamera;
    }
    protected static void init(){
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    protected static void startSpriteRender(){
        skyboxDraw();
        //for (int i = 0; i< 10; i++){
            for (Sprite sprite : spriteList){
              //  if (sprite.getLayout() == i)
                    render(sprite);
            //}
        }
    }

    protected static void startNormalRender(){
        glLineWidth(3);
        glBegin(GL_LINE_LOOP);
        glColor4f(1,1,0,1);
        glVertex2d(0.5f,0.5f);
        glColor4f(1,0,1,1);
        glVertex2d(-0.5f,0.5f);
        glColor4f(0,1,0,1);
        glVertex2d(-0.5f,-0.5f);
        glColor4f(0,1,1,1);
        glVertex2d(0.5f,-0.5f);
        glEnd();

        ShaderProgram.defaultShader.bind();
        Vector2D vector2D = new Vector2D(0,0);
        ShaderProgram.defaultShader.setUniform("matrix", Transformation.getWorldMatrix(new Transform(),activeCamera),16);
        ShaderProgram.defaultShader.setUniform("UIsign", 1);
        glBegin(GL_LINE_LOOP);
        glVertex2f(activeCamera.transform.position.x,
                activeCamera.transform.position.y);
        glVertex2f(vector2D.rotated(activeCamera.getRotation()).x,
                vector2D.rotated(activeCamera.getRotation()).y);
        glEnd();
        ShaderProgram.defaultShader.unbind();

        GL30.glPointSize(30);
        ShaderProgram.defaultShader.bind();
        ShaderProgram.defaultShader.setUniform("matrix", Transformation.getWorldMatrix(new Transform(),activeCamera),16);
        ShaderProgram.defaultShader.setUniform("UIsign", 1);
        GL30.glBegin(GL_POINTS);
        glVertex2f(activeCamera.transform.position.x,
                activeCamera.transform.position.y);
        glVertex2f(activeCamera.transform.position.x- activeCamera.getWidth()/2f * activeCamera.zoom
                ,activeCamera.transform.position.y + activeCamera.getHeight()/2f* activeCamera.zoom);
        glVertex2f(activeCamera.transform.position.x+ activeCamera.getWidth()/2f* activeCamera.zoom,
                activeCamera.transform.position.y- activeCamera.getHeight()/2f* activeCamera.zoom);
        GL30.glEnd();
        ShaderProgram.defaultShader.unbind();
    }
    private static void render(Sprite sprite){

        sprite.getMesh().bind();
        sprite.getShaderProgram().bind();
        sprite.getTexture().bind();
        switch (sprite.type){
            case UI -> {
                sprite.getShaderProgram().setUniform("matrix", Transformation.getUIMatrix(sprite.getGameObject().transform,activeCamera),16);
            }
            case Item -> {
                sprite.getShaderProgram().setUniform("matrix", Transformation.getWorldMatrix(sprite.getGameObject().transform,activeCamera),16);
            }
        }
        ShaderProgram.defaultShader.setUniform("UIsign", 0);
        glDrawArrays(GL_POLYGON, 0,sprite.getMesh().getVertexCount());

        sprite.getTexture().unbind();
        sprite.getShaderProgram().unbind();
        sprite.getMesh().unbind();
    }
    public static void spriteListAdd(Sprite sprite){
        spriteList.add(sprite);
    }
    private static void skyboxDraw(){
        glBegin(GL_POLYGON);
        glColor4f(0.2f,0.2f,0.5f,1);
        glVertex2d(1,1);
        glColor4f(0.2f,0.5f,0.2f,1);
        glVertex2d(-1,1);
        glColor4f(0.5f,0.2f,0.5f,1);
        glVertex2d(-1,-1);
        glColor4f(0.2f,0.5f,0.5f,1);
        glVertex2d(1f,-1);
        glEnd();
    }
}
