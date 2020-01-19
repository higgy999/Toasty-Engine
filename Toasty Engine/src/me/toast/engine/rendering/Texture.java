package me.toast.engine.rendering;

import org.lwjgl.BufferUtils;

import static me.toast.engine.utils.WindowImage.ioResourceToByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Texture {

    private int t_id;

    public Texture(String filepath) {
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer components = BufferUtils.createIntBuffer(1);
        ByteBuffer data = null;
        try { data = stbi_load_from_memory(ioResourceToByteBuffer(filepath, 1024), w, h, components, 4);
        } catch (IOException e) { e.printStackTrace(); }
        t_id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, t_id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(), h.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);

        glBindTexture(GL_TEXTURE_2D, 0);
        stbi_image_free(data);

    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, t_id);
    }

    public void clean() {
        glDeleteTextures(t_id);
    }
}