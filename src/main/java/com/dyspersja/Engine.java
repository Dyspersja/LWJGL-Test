package com.dyspersja;

import com.dyspersja.shaders.StaticShader;

public class Engine {

    private final Display window;
    private final Loader loader;
    private final Renderer renderer;
    private final StaticShader shader;

    private float targetFPS = 1000;
    private boolean running;

    public Engine(
            Loader loader,
            Renderer renderer,
            StaticShader shader
    ) {
        this.window = Display.getInstance();
        this.loader = loader;
        this.renderer = renderer;
        this.shader = shader;
    }

    public void start() {
        initialize();
        run();
        cleanup();
    }

    private void run() {
        running = true;

        double frameTime = 1.0f / targetFPS;
        long lastTime = System.nanoTime();
        double deltaTime = 0;

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f
        };

        int[] indices = {
                0,1,3,
                3,1,2
        };
        Model testModel = loader.loadModel(vertices, indices);

        while (running) {
            boolean render = false;
            long currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / 1e9;
            lastTime = currentTime;

            while (deltaTime > frameTime) {
                render = true;
                deltaTime -= frameTime;

                if (window.shouldClose()) stop();
            }

            if (render) render(testModel);
        }
    }

    private void initialize() {

    }

    private void stop() {
        if (!running) return;
        running = false;
    }

    private void render(Model testModel) {
        renderer.clear();
        shader.start();
        renderer.render(testModel);
        shader.stop();
        window.update();
    }

    private void cleanup() {
        shader.cleanup();
        loader.cleanup();
        window.destroy();
    }

}
