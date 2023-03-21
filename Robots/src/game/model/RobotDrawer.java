package game.model;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RobotDrawer extends Drawer{
    public void drawRobot(Graphics2D g, Robot robot)
    {
        int robotCenterX = (int) Math.round(robot.getPositionX());
        int robotCenterY = (int) Math.round(robot.getPositionY());
        AffineTransform t = AffineTransform.getRotateInstance(robot.getRobotDirection(), robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
    }
}
