package me.toast.engine;

import me.toast.engine.enums.RenderState;
import me.toast.engine.shapes.*;
import me.toast.engine.rendering.Texture;
import me.toast.engine.utils.Coordinate;
import me.toast.engine.utils.WindowImage;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.awt.*;
import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/* Bugs */
//TODO: Fix exact coloring issue

/* TODOs */
//TODO: Make Different Rendering Modes
//TODO: Make Dev Variables
//TODO: Make Event System
//TODO: Make Sound System
//TODO: Make Updater System
//TODO: Enable Multi-Threading
//TODO: Make Rendering Script System
//TODO: Make Global Scale
//TODO: Make Launcher
public class Main {

    private long window;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(1280, 720, "Toasty Engine V0.1.0", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        //Key Detection?
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true);
        });

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            //Center the window
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
        }

        glfwMakeContextCurrent(window);

        // Enable v-sync //Setting FPS?
        glfwSwapInterval(1000/75);

        try { WindowImage.setIcon("./res/Textures/icon.png", window); } catch (Exception e) { e.printStackTrace(); }

        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 1280, 720, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        /* Declare Our Shapes */
        Texture texture = new Texture("./res/Textures/wood.png");
        Circle circle = new Circle(new Color(0, 255, 0), texture, new Coordinate(500, 500), 100, 1, RenderState.FULL);

        while(!glfwWindowShouldClose(window)) {
            glClearColor(0, 0, 0, 0);
                                        /* The glClear below is only for 3D Games */
            glClear(GL_COLOR_BUFFER_BIT);//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            /* Updating */
            circle.update();

            /* Rendering */
            circle.render();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        /* Cleaning */
        circle.clean();
    }

    public static void main(String[] args) { new Main().run(); }
}