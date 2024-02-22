package com.dyspersja;

public class Engine {

    private final Display window;

    public Engine(Display window) {
        this.window = window;
    }

    private void init() {
        window.create();
    }

    public void render() {
        window.update();
    }

    public void cleanup() {
        window.destroy();
    }

}
