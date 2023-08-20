package org.sphinx.sphinxengine.engine;
import static org.lwjgl.opengl.GL40.*;

public class Mesh {
    private int vaoId = 0;

    /**
     * todo 创建VAO和VBO
     */
    Mesh(){

    }

    public int getVaoId() {
        return vaoId;
    }
    public void bind(){
        glBindVertexArray(vaoId);
    }
    public void unbind(){
        glBindVertexArray(0);
    }
}
