package game.model;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TagretDrawer extends Drawer {
    public void drawTarget(Graphics2D g, Target target)
    {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, target.getX(), target.getY(), 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, target.getX(), target.getY(), 5, 5);
    }
}
