package org.sphinx.sphinxengine.engine;

public class Sprite {
    private Mesh mesh;
    private ShaderProgram shaderProgram;
    //private Texture texture;
    private int layout = 0;
    float[] vertices;
    float[] texCoords;
    public Sprite(){

    }

    public Sprite(Texture texture){
        //Renderer.spriteListAdd(this);
        //this.texture = texture;

    }
    public Sprite(String path){
        Renderer.spriteListAdd(this);
        //this.texture = new Texture(path);
        this.mesh = new Mesh();
        this.shaderProgram = new ShaderProgram();

    }
    public Mesh getMesh() {
        return mesh;
    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

/*    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }*/
}
