package org.sphinx.sphinxengine.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.lwjgl.opengl.GL40.*;


public class Renderer {
    private static Camera activeCamera = null;
    private static final List<Render> RENDER_LIST = new ArrayList<>();
    private static Texture loadTexture;
    /**
     * 设置当前渲染器的活动摄像机
     * @param activeCamera 指定摄像机
     */
    public static void setActiveCamera(Camera activeCamera) {
        Renderer.activeCamera = activeCamera;
        Debug.log("渲染器----当前活动摄像机已修改");
    }

    /**
     * 获得当前渲染器的活动摄像机
     * @return 当前活动摄像机
     */
    public static Camera getActiveCamera() {
        return activeCamera;
    }
    protected static void init(){
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        loadTexture = new Texture("/load.png",true);
        Debug.log("渲染器----初始化成功");
    }
    protected static void render(){
        if(!SceneController.isLoadingNextScene){
            if (Objects.isNull(activeCamera)){return;}
            skyboxDraw();
            for (int i = 0; i <= 20; i++){
                for(Render render : RENDER_LIST){
                    if (render.getLayout() == i && render.getGameObject().isEnable()){
                        switch (render.type){
                            case drawer -> renderDrawer((Drawer) render);
                            case sprite -> renderSprite((Sprite) render);
                        }
                    }
                }
            }
            //viewportRender();
        }
        else {
            loadingDraw();
        }
    }

    private static void viewportRender(){
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
    private static void renderDrawer(Drawer drawer){
        ShaderProgram.defaultShader.bind();
        ShaderProgram.defaultShader.setUniform("UIsign", 1);
        switch (drawer.type) {
            case UI ->
                    ShaderProgram.defaultShader.setUniform("matrix", Transformation.getUIMatrix(new Transform()), 16);
            case Item ->
                    ShaderProgram.defaultShader.setUniform("matrix", Transformation.getWorldMatrix(new Transform(), activeCamera), 16);
        }
        drawer.draw();
        ShaderProgram.defaultShader.unbind();
    }
    private static void renderSprite(Sprite sprite){
        sprite.getMesh().bind();
        sprite.getShaderProgram().bind();
        sprite.getTexture().bind();
        switch (sprite.type){
            case UI ->
                sprite.getShaderProgram().setUniform("matrix", Transformation.getUIMatrix(sprite.getGameObject().getTransform().addedVector(sprite.offset)),16);
            case Item ->
                sprite.getShaderProgram().setUniform("matrix", Transformation.getWorldMatrix(sprite.getGameObject().getTransform().addedVector(sprite.offset),activeCamera),16);
        }
        sprite.getShaderProgram().setUniform("UIsign", 0);
        glDrawArrays(GL_QUADS, 0,sprite.getMesh().getVertexCount());

        sprite.getTexture().unbind();
        sprite.getShaderProgram().unbind();
        sprite.getMesh().unbind();
    }
    public static void renderListAdd(Render render){
        RENDER_LIST.add(render);
    }
    protected static void destroyAllRender(){
        Debug.log("渲染器----开始释放精灵");
        while (!RENDER_LIST.isEmpty()){
            Render render = RENDER_LIST.get(0);
            switch (render.type){
                case sprite -> {
                    ((Sprite) render).getMesh().destroy();
                    RENDER_LIST.remove(render);
                }
                case drawer -> RENDER_LIST.remove(render);
            }
        }
    }
    protected static void destroyRender(Sprite sprite){
        sprite.getMesh().destroy();
        RENDER_LIST.remove(sprite);
    }
    private static void skyboxDraw(){
        //Debug.log("渲染器----开始绘制天空盒");
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
    private static void loadingDraw(){
        loadTexture.bind();
        glBegin(GL_QUADS);
        float v1 = (2f / WindowController.getInstance().getWindowWidth() * loadTexture.getWidth()) / 2;
        float v2 = (2f / WindowController.getInstance().getWindowHeight() * loadTexture.getHeight()) / 2;
        glTexCoord2f(0,1);
        glVertex2f(-v1,-v2);
        glTexCoord2f(1,1);
        glVertex2f(v1,-v2);
        glTexCoord2f(1,0);
        glVertex2f(v1,v2);
        glTexCoord2f(0,0);
        glVertex2f(-v1,v2);
        glEnd();
        loadTexture.unbind();
    }
}
