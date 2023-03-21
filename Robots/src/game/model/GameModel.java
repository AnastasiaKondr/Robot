package game.model;

import java.awt.*;

public class GameModel {
    private Robot robot;
    private Target target;
    private Dimension dimension;

    public GameModel() {
        this.robot = new Robot();
        this.target = new Target();
    }

    public void setTargetPosition(Point p) {
        target.setX(p.x);
        target.setY(p.y);
    }
    protected Point getTargetPosition() {
        return new Point(target.getX(), target.getY());
    }
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
    public Dimension getDimension() {
        return this.dimension;
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
    }

    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    private static double asNormalizedRadians(double angle) {
        while (angle < 0) {
            angle += 2 * Math.PI;
        }
        while (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }
    private double normalizedPositionX(double x) {
        if (x < 0)
            return 0;
        if (x > dimension.height)
            return dimension.height;
        return x;
    }
    private double normalizedPositionY(double y) {
        if (y < 0)
            return 0;
        if (y > dimension.width)
            return dimension.width;
        return y;
    }
    private static double applyLimits(double value, double min, double max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    private void moveRobot(double velocity, double angularVelocity, double duration) {
        velocity = applyLimits(velocity, 0, Robot.maxVelocity);
        angularVelocity = applyLimits(angularVelocity, -Robot.maxAngularVelocity, Robot.maxAngularVelocity);
        double newX = robot.getPositionX() + velocity / angularVelocity *
                (Math.sin(robot.getRobotDirection() + angularVelocity * duration) -
                        Math.sin(robot.getRobotDirection()));
        if (!Double.isFinite(newX)) {
            newX = robot.getPositionX() + velocity * duration * Math.cos(robot.getRobotDirection());
        }
        double newY = robot.getPositionY() - velocity / angularVelocity *
                (Math.cos(robot.getRobotDirection() + angularVelocity * duration) -
                        Math.cos(robot.getRobotDirection()));
        if (!Double.isFinite(newY)) {
            newY = robot.getPositionY() + velocity * duration * Math.sin(robot.getRobotDirection());
        }
        robot.setPositionX(normalizedPositionX(newX));
        robot.setPositionY(normalizedPositionY(newY));
        double newDirection = asNormalizedRadians(robot.getRobotDirection() + angularVelocity * duration);
        robot.setRobotDirection(newDirection);
    }

    public void updateModel() {
        double distance = distance(target.getX(), target.getY(),
                robot.getPositionX(), robot.getPositionY());
        if (distance < 0.5) {
            return;
        }
        double velocity = Robot.maxVelocity;
        double angleToTarget = angleTo(robot.getPositionX(), robot.getPositionY(),
                target.getX(), target.getY());
        double angularVelocity = 0;
        if (angleToTarget > robot.getRobotDirection()) {
            angularVelocity = Robot.maxAngularVelocity;
        }
        if (angleToTarget < robot.getRobotDirection()) {
            angularVelocity = -Robot.maxAngularVelocity;
        }

        moveRobot(velocity, angularVelocity, 10);
    }

    public Robot getRobot() {
        return robot;
    }

    public Target getTarget() {
        return target;
    }

}
