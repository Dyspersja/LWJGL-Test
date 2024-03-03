package com.dyspersja;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    public void render(Model model) {
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

        glBindVertexArray(model.getId());
        glEnableVertexAttribArray(0);
        glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }

    public void clear() {
        glClearColor(1,0,0,1);
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
