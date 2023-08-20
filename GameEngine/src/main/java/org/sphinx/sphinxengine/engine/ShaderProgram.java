package org.sphinx.sphinxengine.engine;
import static org.lwjgl.opengl.GL40.*;

public class ShaderProgram {
    private int programId;

    /**
     * todo 创建gl程序加载器
     */
    ShaderProgram(){

    }


    public void bind(){
        glUseProgram(programId);
    }
    public void unbind(){
        glUseProgram(0);
    }
}
