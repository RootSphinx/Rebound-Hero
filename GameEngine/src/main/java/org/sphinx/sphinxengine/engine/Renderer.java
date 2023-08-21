package org.sphinx.sphinxengine.engine;
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
        for (int i = 0; i< 10; i++){
            for (Sprite sprite : spriteList){
                if (sprite.getLayout() == i)
                    render(sprite);
            }
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

    }
    private static void render(Sprite sprite){

        sprite.getMesh().bind();
        sprite.getShaderProgram().bind();
        sprite.getTexture().bind();
        sprite.getShaderProgram().setUniform("matrix", Transformation.getWorldMatrix(sprite.gameObject,activeCamera),16);
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
        glVertex2d(-1,1);
        glVertex2d(-1,-1);
        glVertex2d(15f,-1);
        glEnd();
    }
}
