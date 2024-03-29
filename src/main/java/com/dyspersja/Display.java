package com.dyspersja;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class Display {

    private static Display instance;

    private String title;
    private int width;
    private int height;

    // The window handle
    private long window;

    public static Display getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance of Display class is not initialized");
        }
        return instance;
    }

    public static void initialize(String title, int width, int height) {
        if (instance != null) {
            throw new IllegalStateException("Display class has already been initialized");
        } else {
            var window = new Display(title, width, height);
            window.create();

            instance = window;
        }
    }

    private Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void create() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(
                width,
                height,
                title,
                MemoryUtil.NULL,
                MemoryUtil.NULL
        );

        if (window == MemoryUtil.NULL) throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
        });

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GL.createCapabilities();
    }

    public void update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void destroy() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }
}
