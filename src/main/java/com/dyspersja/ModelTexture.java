package com.dyspersja;

public class ModelTexture {

    private int width;
    private int height;

    private int textureId;

    public ModelTexture(int width, int height, int textureId) {
        this.width = width;
        this.height = height;
        this.textureId = textureId;
    }

    public int getTextureID() {
        return textureId;
    }
}