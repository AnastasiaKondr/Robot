package view.draw;

import model.Robot;
import model.Target;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TagretDrawer extends Drawer<Target> {
    public void draw(Target target, Graphics2D g)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, target.getX(), target.getY(), 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, target.getX(), target.getY(), 5, 5);
    }
    public Class<model.Target> getDrawingClass() {
        return Target.class;
    }
}
