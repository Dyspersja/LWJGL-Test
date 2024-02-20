package com.dyspersja;

public class Main {
    public static void main(String[] args) {
        DisplayManager displayManager = new DisplayManager("App Title",400,300);
        displayManager.create();

        while (!displayManager.windowShouldClose()) {
            displayManager.update();
        }
        displayManager.destroy();
    }
}