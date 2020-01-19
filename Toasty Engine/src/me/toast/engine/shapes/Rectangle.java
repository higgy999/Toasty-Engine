package me.toast.engine.shapes;

import me.toast.engine.utils.ObjectBase;
import me.toast.engine.enums.RenderState;
import me.toast.engine.rendering.Texture;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Rectangle extends ObjectBase {

    private int x, y, width, height;
    private float lineWidth;
    private Color color;
    private RenderState state;
    private Texture texture;

    public Rectangle(Color color, Texture texture, float lineWidth, int x, int y, int width, int height, RenderState state) {
        this.state = state;

        if(state == RenderState.FULL) {
            this.color = color;
            this.texture = null;
            this.lineWidth = 0;

            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        } else if(state == RenderState.TEXTURED) {
            this.color = null;
            this.texture = texture;
            this.lineWidth = 0;

            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        } else if(state == RenderState.HOLLOW) {
            this.color = color;
            this.texture = null;
            this.lineWidth = lineWidth;

            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        } else { System.err.println("INVALID RENDER STATE!"); System.exit(1); }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        if(state == RenderState.FULL) {
            glColor3f(color.getRed(), color.getGreen(), color.getBlue());

            glBegin(GL_QUADS);
                glVertex2f(x, y); //Upper-Left
                glVertex2f(width, y); //Upper-Right
                glVertex2f(width, height); //Bottom-Right
                glVertex2f(x, height); //Bottom-Left
            glEnd();
        } else if(state == RenderState.TEXTURED) {
            glColor3f(1, 1, 1);
            texture.bind();

            glBegin(GL_QUADS);
                glTexCoord2f(0, 0);
                glVertex2f(x, y); //Upper-Left

                glTexCoord2f(1, 0);
                glVertex2f(width, y); //Upper-Right

                glTexCoord2f(1, 1);
                glVertex2f(width, height); //Bottom-Right

                glTexCoord2f(0, 1);
                glVertex2f(x, height); //Bottom-Left
            glEnd();
        } else if(state == RenderState.HOLLOW) {
            glColor3f(color.getRed(), color.getGreen(), color.getBlue());

            glLineWidth(lineWidth);

            glBegin(GL_LINES);
                glVertex2f(x, y);
                glVertex2f(width, y);

                glVertex2f(width, y);
                glVertex2f(width, height);

                glVertex2f(width, height);
                glVertex2f(x, height);

                glVertex2f(x, height);
                glVertex2f(x, y);
            glEnd();
        }
        super.render();
    }

    @Override
    public void clean() {
        if(texture != null)
            texture.clean();
        super.clean();
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public Texture getTexture() { return texture; }
    public void setTexture(Texture texture) { this.texture = texture; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
