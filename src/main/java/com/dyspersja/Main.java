package com.dyspersja;

import com.dyspersja.shaders.StaticShader;

public class Main {
    public static void main(String[] args) {
        Display.initialize("App Title",400,300);

        var loader = new Loader();
        var renderer = new Renderer();
        var shader = new StaticShader();

        Engine engine = new Engine(loader, renderer, shader);
        engine.start();
    }
}