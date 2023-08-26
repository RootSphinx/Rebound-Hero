package org.sphinx.sphinxengine.engine;

public class Sprite {
    public enum Type{
        UI,Item
    }
    private GameObject gameObject;
    private Mesh mesh;
    private ShaderProgram shaderProgram = ShaderProgram.defaultShader;
    private Texture texture;
    private int layout = 0;
    public Type type;
    float[] vertices;
    float[] texCoords = new float[]{
            0, 0,
            -1, 0,
            -1, 1,
            0, 1
    };

    public Sprite(GameObject gameObject, Texture texture,Type type){
        this.texture = texture;
        mSprite(gameObject,type);
    }
    public Sprite(GameObject gameObject, String path, Type type){
        this.texture = new Texture(path);
        mSprite(gameObject,type);
    }
    private void mSprite(GameObject gameObject, Type type){
        this.gameObject = gameObject;
        Renderer.spriteListAdd(this);
        vertices = new float[]{
                texture.getWidth()/2f, texture.getHeight()/2f,
                -texture.getWidth()/2f, texture.getHeight()/2f,
                -texture.getWidth()/2f, -texture.getHeight()/2f,
                texture.getWidth()/2f, -texture.getHeight()/2f,
        };
        this.mesh = new Mesh(vertices, texCoords);
        Mesh.MESH_MAP.put(gameObject.getId(),mesh);
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
    public GameObject getGameObject(){
        return gameObject;
    }
    public void setShaderProgram(ShaderProgram shaderProgram){
        this.shaderProgram = shaderProgram;
    }
}
