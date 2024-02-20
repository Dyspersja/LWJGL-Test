package com.dyspersja;

import org.lwjgl.glfw.GLFW;

public class Main {
    public static void main(String[] args) {
        DisplayManager displayManager = new DisplayManager("App Title",400,300);
        displayManager.create();

        while (!GLFW.glfwWindowShouldClose(displayManager.window)) {
            displayManager.update();
        }
        displayManager.destroy();
    }
}