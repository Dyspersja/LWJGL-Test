package com.dyspersja;

public class Main {
    public static void main(String[] args) {
        Display window = new Display("App Title",400,300);
        window.create();

        while (!window.shouldClose()) {
            window.update();
        }
        window.destroy();
    }
}