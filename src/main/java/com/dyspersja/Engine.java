package com.dyspersja;

public class Engine {

    private final Display window;

    private float targetFPS = 1000;
    private boolean running;

    public Engine(Display window) {
        this.window = window;
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

            if (render) render();
        }
    }

    private void initialize() {
        window.create();
    }

    private void stop() {
        if (!running) return;
        running = false;
    }

    private void render() {
        window.update();
    }

    private void cleanup() {
        window.destroy();
    }

}
