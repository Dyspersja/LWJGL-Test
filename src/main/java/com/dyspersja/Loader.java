package com.dyspersja;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class Loader {

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();

    public Model loadModel(float[] vertices) {
        int id = createVAO();
        storeDataInAttributeList(0, vertices);
        unbindVAO();
        return new Model(id, vertices.length / 3);
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

    private void storeDataInAttributeList(int attributeNumber, float[] data) {
        int vbo = glGenBuffers();
        vbos.add(vbo);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data);
        buffer.flip();

        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(attributeNumber, 3, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void cleanup() {
        for (int vao : vaos) glDeleteVertexArrays(vao);
        for (int vbo : vbos) glDeleteBuffers(vbo);
    }
}
