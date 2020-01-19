package me.toast.engine.shapes;

import me.toast.engine.utils.Coordinate;
import me.toast.engine.utils.ObjectBase;
import me.toast.engine.enums.RenderState;
import me.toast.engine.rendering.Texture;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Polygon extends ObjectBase {

    private Coordinate[] coords;
    private float lineWidth;
    private Color color;
    private RenderState state;

    private Texture texture;
    private float rate;

    public Polygon(Coordinate[] coords, Color color, Texture texture, float lineWidth, RenderState state) {
        if(state == RenderState.FULL) {
            this.coords = coords;
            this.color = color;
            this.texture = null;
            this.lineWidth = 0;
            this.state = state;
        } else if(state == RenderState.HOLLOW) {
            this.coords = coords;
            this.color = color;
            this.texture = null;
            this.lineWidth = lineWidth;
            this.state = state;
        } else if(state == RenderState.TEXTURED) {
            this.coords = coords;
            this.color = null;
            this.texture = texture;
            this.lineWidth = 0;
            this.state = state;

            this.rate = 1 / coords.length;
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

            glBegin(GL_POLYGON);
                for (Coordinate coord : coords)
                    glVertex2f(coord.getX(), coord.getY());
            glEnd();
        } else if(state == RenderState.HOLLOW) {
            glColor3f(color.getRed(), color.getGreen(), color.getBlue());

            glLineWidth(lineWidth);
            glBegin(GL_LINES);
                for(int i = 0; i < coords.length; i++) {
                    glVertex2f(coords[i].getX(), coords[i].getY());
                    glVertex2f(coords[i + 1].getX(), coords[i + 1].getY());
                }
            glEnd();
        } else if(state == RenderState.TEXTURED) {
            glColor3f(1, 1, 1);

            glBegin(GL_POLYGON);
                for(int i = 0; i < coords.length; i++) {
                    glTexCoord2f(i * rate,  i * rate);
                    glVertex2f(coords[i].getX(), coords[i].getY());
                }
            glEnd();
        }
        super.render();
    }

    public Coordinate[] getCoords() { return coords; }
    public void setCoords(Coordinate[] coords) { this.coords = coords; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
}
