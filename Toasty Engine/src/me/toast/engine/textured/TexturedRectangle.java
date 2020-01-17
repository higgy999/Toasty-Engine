package me.toast.engine.textured;

import me.toast.engine.ObjectBase;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import javax.imageio.ImageIO;

import static me.toast.engine.WindowImage.ioResourceToByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class TexturedRectangle extends ObjectBase {

    private int t_id;
    private int x, y, width, height;

    public TexturedRectangle(String filepath, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer components = BufferUtils.createIntBuffer(1);
        ByteBuffer data = null;
        try { data = stbi_load_from_memory(ioResourceToByteBuffer(filepath, 1024), w, h, components, 4);
        } catch (IOException e) { e.printStackTrace(); }
        t_id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, t_id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(), h.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
        stbi_image_free(data);

    }

    public void bind(int sampler) {
        if (sampler >= 0 && sampler <= 31) {
            glActiveTexture(GL_TEXTURE0 + sampler);
            glBindTexture(GL_TEXTURE_2D, t_id);
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        glColor3f(1, 1, 1);
        bind(1);

        glBegin(GL_QUADS);
            glTexCoord2f(0, 0);
            glVertex2i(x, y); //Upper-Left

            glTexCoord2f(1, 0);
            glVertex2i(width, y); //Upper-Right

            glTexCoord2f(1, 1);
            glVertex2i(width, height); //Bottom-Right

            glTexCoord2f(0, 1);
            glVertex2i(x, height); //Bottom-Left
        glEnd();

        super.render();
    }

    @Override
    public void clean() {
        glDeleteTextures(t_id);
        super.clean();
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}