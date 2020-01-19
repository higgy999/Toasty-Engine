package me.toast.engine.shapes;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;

import me.toast.engine.utils.Coordinate;
import me.toast.engine.utils.ObjectBase;
import me.toast.engine.enums.RenderState;
import me.toast.engine.rendering.Texture;

import java.awt.*;

public class Circle extends ObjectBase {

    private Coordinate center;
    private Color color;
    private Texture texture;
    private RenderState state;
    private float radius;
    private float lineWidth;

    private final int quality = 80;

    public Circle(Color color, Texture texture, Coordinate center, float radius, float lineWidth, RenderState state) {
        if(state == RenderState.FULL) {
            this.state = state;
            this.center = center;
            this.color = color;
            this.radius = radius;
            this.lineWidth = 0;
            this.texture = null;
        } else if(state == RenderState.HOLLOW) {
            this.state = state;
            this.center = center;
            this.color = color;
            this.radius = radius;
            this.lineWidth = lineWidth;
            this.texture = null;
        } else if(state == RenderState.TEXTURED) {
            this.state = state;
            this.center = center;
            this.color = null;
            this.radius = radius;
            this.lineWidth = 0;
            this.texture = texture;
        }
    }

    @Override
    public void render() {
        float delta_angle = (float) (PI * 2 / 20);
        if(state == RenderState.FULL) {
            glColor3f(color.getRed(), color.getGreen(), color.getBlue());

            glBegin(GL_TRIANGLE_FAN);
                glVertex2f(center.getX(), center.getY());
                for (int i = 0; i <= quality; i++)
                    glVertex2f(center.getX() + (float) (radius * cos(i * PI * 2 / quality)), center.getY() + (float) (radius * sin(i * PI * 2 / quality)));
            glEnd();
        } else if(state == RenderState.HOLLOW) {
            glColor3f(color.getRed(), color.getGreen(), color.getBlue());

            glLineWidth(lineWidth);
            glBegin(GL_LINE_LOOP);
                for(int i = 0; i <= 100; i++)
                    glVertex2f(center.getX() + (float) (radius * cos(i *  PI * 2 / 100)), center.getY() + (float) (radius* sin(i * PI * 2 / 100)));
            glEnd();
        } else if(state == RenderState.TEXTURED) {
            glColor3f(1, 1, 1);
            texture.bind();

            glBegin(GL_TRIANGLE_FAN);
                glTexCoord2f(0.5f, 0.5f);
                glVertex2f(center.getX(), center.getY());
                for (int i = 0; i <= 20; i++) {
                    glTexCoord2f((float) (cos(delta_angle * i) + 1.0f) * 0.5f, (float) (sin(delta_angle * i) + 1.0f) * 0.5f);
                    glVertex2f(center.getX() + (float) (radius * cos(i * PI * 2 / 20)), center.getY() + (float) (radius * sin(i * PI * 2 / 20)));
                }
            glEnd();
        }
        super.render();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void clean() {
        if(texture != null)
            texture.clean();
        super.clean();
    }
}
