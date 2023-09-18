package org.sphinx.engine;

import java.util.ArrayList;
import java.util.List;

public class SplitTexture {
    private final List<Texture> textures = new ArrayList<>();

    /**
     * 创建一个切割纹理
     * @param path 指定图片所在路径
     * @param row 切割行数
     * @param col 切割列数
     */
    public SplitTexture(String path, int row, int col){
        for (int i = 0; i < row * col; i++){
            textures.add(new Texture(path, row, col,i));
        }
    }
    /**
     * 返回切割后指定索引的纹理
     * @param index 索引
     * @return 纹理
     */
    public Texture getTexture(int index){
        return textures.get(index);
    }

    /**
     * 返回一定范围内的纹理列表
     * @param start 起始索引
     * @param end 结束索引
     * @return 纹理列表
     */
    public List<Texture> getTextures(int start,int end){
        List<Texture> tempTexture = new ArrayList<>();
        for (int i = start; i < end ; i++){
            tempTexture.add(textures.get(i));
        }
        return tempTexture;
    }
    /**
     * 返回指定顺序的纹理列表
     * @param indexes 索引顺序
     * @return 纹理列表
     */
    public List<Texture> getTextureIndex(int ... indexes){
        List<Texture> tempTexture = new ArrayList<>();
        for (int i : indexes){
            tempTexture.add(textures.get(i));
        }
        return tempTexture;
    }

    /**
     * 返回切割后的纹理列表
     * @return 纹理列表
     */
    public List<Texture> getTextureList() {
        return textures;
    }
}