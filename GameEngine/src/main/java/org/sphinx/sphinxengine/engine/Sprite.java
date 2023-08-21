package org.sphinx.sphinxengine.engine;

public class Sprite {
    public enum Type{
        UI,Item
    }
    GameObject gameObject;
    private Mesh mesh;
    private ShaderProgram shaderProgram = ShaderProgram.defaultShader;
    private Texture texture;
    private int layout = 0;
    public Type type;
    float[] vertices;
    float[] texCoords = new float[]{
            0, 0,
            1, 0,
            1, 1,
            0, 1
    };

    public Sprite(GameObject gameObject, Texture texture,Type type){
        this.gameObject = gameObject;
        Renderer.spriteListAdd(this);
        this.texture = texture;
        vertices = new float[]{
                texture.getWidth()/2f, texture.getHeight()/2f,
                -texture.getWidth()/2f, texture.getHeight()/2f,
                -texture.getWidth()/2f, -texture.getHeight()/2f,
                texture.getWidth()/2f, -texture.getHeight()/2f
        };
        this.mesh = new Mesh(vertices, texCoords);
        this.type = type;
    }
    public Sprite(GameObject gameObject, String path, Type type){
        this.gameObject = gameObject;
        Renderer.spriteListAdd(this);
        this.texture = new Texture(path);
        vertices = new float[]{
                texture.getWidth()/2f, texture.getHeight()/2f,
                -texture.getWidth()/2f, texture.getHeight()/2f,
                -texture.getWidth()/2f, -texture.getHeight()/2f,
                texture.getWidth()/2f, -texture.getHeight()/2f,
        };
        this.mesh = new Mesh(vertices, texCoords);
        this.type = type;
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
