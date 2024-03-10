package com.dyspersja;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    public void render(TexturedModel texturedModel) {
//        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        Model model = texturedModel.getModel();

        glBindVertexArray(model.getId());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
        glDrawElements(GL_TRIANGLES, model.getVertexCount(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
    }

    public void clear() {
        glClearColor(0.2F,0.2F,0.2F,1);
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
