package org.sphinx.sphinxengine.engine;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL40.*;

public class Mesh {
    private final int vaoId;
    private final List<Integer> vboIdList = new ArrayList<>();
    private int vertexCount;

    /**
     * todo 创建VAO和VBO
     */
    Mesh(float[] vertices, float[] texCoords){
        FloatBuffer vertexBuffer = null;
        FloatBuffer coordBuffer = null;
        vaoId = glGenVertexArrays();
        try {
            glBindVertexArray(vaoId);
            int vboId = glGenBuffers();
            vboIdList.add(vboId);
            glBindBuffer(GL_ARRAY_BUFFER,vboId);
            vertexBuffer = MemoryUtil.memAllocFloat(vertices.length);
            vertexBuffer.put(vertices).flip();
            glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 2,GL_FLOAT, false, 0, 0);
            vertexCount = vertices.length/2;

            vboId = glGenBuffers();
            vboIdList.add(vboId);
            glBindBuffer(GL_ARRAY_BUFFER,vboId);
            coordBuffer = MemoryUtil.memAllocFloat(texCoords.length);
            coordBuffer.put(texCoords).flip();
            glBufferData(GL_ARRAY_BUFFER, coordBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(1);
            glVertexAttribPointer(1, 2,GL_FLOAT, false, 0, 0);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        }
        catch (RuntimeException e){
            e.printStackTrace();
        }
        finally {
            MemoryUtil.memFree(vertexBuffer);
            MemoryUtil.memFree(coordBuffer);
        }
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void bind(){
        glBindVertexArray(vaoId);
    }
    public void unbind(){
        glBindVertexArray(0);
    }

    /**
     * todo vao清理
     */
    public void cleanup(){
        glDeleteVertexArrays(vaoId);
        for (int i : vboIdList){
            glDeleteBuffers(i);
            vboIdList.remove(i);
        }
    }
}
