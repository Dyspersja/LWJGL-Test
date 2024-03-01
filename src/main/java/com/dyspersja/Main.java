package com.dyspersja;

public class Main {
    public static void main(String[] args) {
        var window = new Display("App Title",400,300);
        var loader = new Loader();
        var renderer = new Renderer();

        Engine engine = new Engine(window, loader, renderer);
        engine.start();
    }
}