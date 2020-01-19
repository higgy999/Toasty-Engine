package me.toast.engine.shapes;

import me.toast.engine.utils.Coordinate;
import me.toast.engine.utils.ObjectBase;
import me.toast.engine.enums.RenderState;
import me.toast.engine.rendering.Texture;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Triangle extends ObjectBase {

    private Coordinate point1, point2, point3;
    private Color color;
    private Texture texture;
    private RenderState state;
    private float lineWidth;

    public Triangle(Color color, Texture texture, float lineWidth, Coordinate point1, Coordinate point2, Coordinate point3, RenderState state) {
        if(state == RenderState.FULL) {
            this.state = state;

            this.color = color;
            this.texture = null;
            this.lineWidth = 0;

            this.point1 = point1;
            this.point2 = point2;
            this.point3 = point3;
        } else if(state == RenderState.TEXTURED) {
            this.state = state;

            this.color = null;
            this.texture = texture;
            this.lineWidth = 0;

            this.point1 = point1;
            this.point2 = point2;
            this.point3 = point3;
        } else if(state == RenderState.HOLLOW) {
            this.state = state;

            this.color = color;
            this.texture = null;
            this.lineWidth = lineWidth;

            this.point1 = point1;
            this.point2 = point2;
            this.point3 = point3;
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        if(state == RenderState.FULL) {
            glColor3f(color.getRed(), color.getGreen(), color.getBlue());

            glBegin(GL_TRIANGLES);
                glVertex2f(point1.getX(), point1.getY());
                glVertex2f(point2.getX(), point2.getY());
                glVertex2f(point3.getX(), point3.getY());
            glEnd();
        } else if(state == RenderState.HOLLOW) {
            glColor3f(color.getRed(), color.getGreen(), color.getBlue());

            glBegin(GL_LINES);
                glVertex2f(point1.getX(), point1.getY());
                glVertex2f(point2.getX(), point2.getY());

                glVertex2f(point2.getX(), point2.getY());
                glVertex2f(point3.getX(), point3.getY());

                glVertex2f(point3.getX(), point3.getY());
                glVertex2f(point1.getX(), point1.getY());
            glEnd();
        } else if(state == RenderState.TEXTURED) {
            texture.bind();
            glColor3f(1, 1, 1);

            glBegin(GL_TRIANGLES);
                glTexCoord2f(1, 0);
                glVertex2f(point1.getX(), point1.getY());

                glTexCoord2f(1, 1);
                glVertex2f(point2.getX(), point2.getY());

                glTexCoord2f(0, 1);
                glVertex2f(point3.getX(), point3.getY());
            glEnd();
        }
        super.render();
    }

    @Override
    public void clean() {
        super.clean();
    }

    public Coordinate getPoint1() { return point1; }
    public Coordinate getPoint2() { return point2; }
    public Coordinate getPoint3() { return point3; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}
