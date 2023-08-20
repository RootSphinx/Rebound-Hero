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

    protected static void startSpriteRender(){
        for (int i = 0; i< 10; i++){
            for (Sprite sprite : spriteList){
                if (sprite.getLayout() == i)
                    render(sprite);
            }
        }
    }

    protected static void startNormalRender(){

/*        for (Sprite sprite : spriteList){
                render(sprite);
        }*/

    }
    private static void render(Sprite sprite){

        sprite.getMesh().bind();
        sprite.getShaderProgram().bind();
        //sprite.getTexture().bind();

        glBindVertexArray(sprite.getMesh().getVaoId());

        //sprite.getTexture().unbind();
        sprite.getShaderProgram().unbind();
        sprite.getMesh().unbind();
    }
    public static void spriteListAdd(Sprite sprite){
        spriteList.add(sprite);
    }
}
