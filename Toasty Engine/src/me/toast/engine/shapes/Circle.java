package me.toast.engine.shapes;

import static org.lwjgl.opengl.GL11.*;
import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.glVertex2f;

import me.toast.engine.Coordinate;
import me.toast.engine.ObjectBase;

import java.awt.*;

public class Circle extends ObjectBase {

    private Coordinate center;
    private Color color;
    private float radius;

    public Circle(Color color, Coordinate center, float radius) {
        this.center = center;
        this.color = color;
        this.radius = radius;
    }

    @Override
    public void render() {
        glColor3f(color.getRed(), color.getGreen(), color.getBlue());

        float angle;

        glBegin(GL_TRIANGLE_FAN);
            glVertex2f(center.getX(), center.getY());
            for(angle = 1.0f; angle < 361.0f; angle += 0.2) {
                float x2 = (float) (center.getX() + sin(angle) * radius);
                float y2 = (float) (center.getY() + cos(angle) * radius);
                glVertex2f(x2,y2);
            }
        glEnd();
        super.render();
    }

    @Override
    public void update() {
        super.update();
    }
}
