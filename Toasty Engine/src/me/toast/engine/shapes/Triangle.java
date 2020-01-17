package me.toast.engine.shapes;

import me.toast.engine.Coordinate;
import me.toast.engine.ObjectBase;
import me.toast.engine.RenderState;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Triangle extends ObjectBase {

    private Coordinate point1, point2, point3;
    private Color color;

    public Triangle(Color color, Coordinate point1, Coordinate point2, Coordinate point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.color = color;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        glBegin(GL_TRIANGLES);
            glVertex2f(point1.getX(), point1.getY());
            glVertex2f(point2.getX(), point2.getY());
            glVertex2f(point3.getX(), point3.getY());
        glEnd();
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
