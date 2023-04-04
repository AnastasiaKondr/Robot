package view.draw;

import model.Robot;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class RobotDrawer extends Drawer<Robot> {
    @Override
    public void draw(Robot robot, Graphics2D g) {
        int robotCenterX = (int) Math.round(robot.getPositionX());
        int robotCenterY = (int) Math.round(robot.getPositionY());

        AffineTransform t = AffineTransform.getRotateInstance(robot.getRobotDirection(), robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX  + 10, robotCenterY, 5, 5);
    }

    @Override
    public Class<Robot> getDrawingClass() {
        return Robot.class;
    }
}
