package com.dyspersja;

import org.lwjgl.system.MemoryUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class Loader {

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();
    private List<Integer> textures = new ArrayList<>();

    public Model loadModel(float[] vertices, float[] textureCoordinates, int[] indices) {
        int id = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, vertices);
        storeDataInAttributeList(1, 2, textureCoordinates);
        unbindVAO();
        return new Model(id, indices.length);
    }

    public ModelTexture loadTexture(String filePath) {
        int width;
        int height;
        int[] pixels;
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(filePath));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            throw new RuntimeException("Could not load textures");
        }

        ByteBuffer buffer = MemoryUtil.memAlloc(pixels.length * 4);
        for (int i = 0; i < width * height; i++) {
                buffer.putInt(pixels[i]);
        }
        buffer.flip();

        int textureId = glGenTextures();
        textures.add(textureId);

        glBindTexture(GL_TEXTURE_2D, textureId);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_BGRA, GL_UNSIGNED_BYTE, buffer);
        glBindTexture(GL_TEXTURE_2D, 0);
        return new ModelTexture(width, height, textureId);
    }

    private int createVAO() {
        int id = glGenVertexArrays();
        vaos.add(id);
        glBindVertexArray(id);
        return id;
    }

    private void unbindVAO() {
        glBindVertexArray(0);
    }

    private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data) {
        int vbo = glGenBuffers();
        vbos.add(vbo);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data);
        buffer.flip();

        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(attributeNumber, coordinateSize, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    void bindIndicesBuffer(int[] indices) {
        int vbo = glGenBuffers();
        vbos.add(vbo);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);

        IntBuffer buffer = MemoryUtil.memAllocInt(indices.length);
        buffer.put(indices);
        buffer.flip();

        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }

    public void cleanup() {
        for (int vao : vaos) glDeleteVertexArrays(vao);
        for (int vbo : vbos) glDeleteBuffers(vbo);
        for (int texture : textures) glDeleteTextures(texture);
    }
}
