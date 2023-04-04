package view.draw;

import model.Entity;

import java.awt.*;

public abstract class Drawer<E extends Entity> {
    public abstract void draw(E entity, Graphics2D g);

    public abstract Class<E> getDrawingClass();

    protected static void fillOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.fillOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }

    protected static void drawOval(Graphics g, int centerX, int centerY, int diam1, int diam2) {
        g.drawOval(centerX - diam1 / 2, centerY - diam2 / 2, diam1, diam2);
    }
}
