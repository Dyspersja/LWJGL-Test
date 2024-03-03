package com.dyspersja.shaders;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_SHADER_FILE = "src/main/java/com/dyspersja/shaders/vertexShader.vsh";
    private static final String FRAGMENT_SHADER_FILE = "src/main/java/com/dyspersja/shaders/fragmentShader.fsh";

    public StaticShader() {
        super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
