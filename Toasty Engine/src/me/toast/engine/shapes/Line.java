package me.toast.engine.shapes;

import me.toast.engine.Coordinate;
import me.toast.engine.ObjectBase;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Line extends ObjectBase {

    private Color color;
    private Coordinate point1, point2;
    private float width;

    public Line(Color color, Coordinate point1, Coordinate point2, float width) {
        this.color = color;
        this.point1 = point1;
        this.point2 = point2;
        this.width = width;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {
        glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        glLineWidth(width);
        glBegin(GL_LINES);
            glVertex2f(point1.getX(), point1.getY());
            glVertex2f(point2.getX(), point2.getY());
        glEnd();
        super.render();
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public Coordinate getPoint1() { return point1; }
    public void setPoint1(Coordinate point1) { this.point1 = point1; }
    public Coordinate getPoint2() { return point2; }
    public void setPoint2(Coordinate point2) { this.point2 = point2; }
    public float getWidth() { return width; }
    public void setWidth(float width) { this.width = width; }
}
