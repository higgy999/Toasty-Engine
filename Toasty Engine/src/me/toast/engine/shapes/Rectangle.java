package me.toast.engine.shapes;

import me.toast.engine.ObjectBase;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Rectangle extends ObjectBase {

    private int x, y, width, height;
    private Color color;

    public Rectangle(Color color, int x, int y, int width, int height) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        glBegin(GL_QUADS);
            glVertex2f(x, y); //Upper-Left
            glVertex2f(width, y); //Upper-Right
            glVertex2f(width, height); //Bottom-Right
            glVertex2f(x, height); //Bottom-Left
        glEnd();
        super.render();
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
