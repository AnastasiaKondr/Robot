package model;

import java.awt.*;

public class Robot implements Entity {
    private double positionX = 100;
    private double positionY = 100;
    private Target target;

    public Robot(Target target){
        this.target = target;
    }

    private double velocity;
    public static final double maxVelocity = 0.05;
    public static final double maxAngularVelocity = 0.001;
    private double duration = 10.0;
    private volatile double robotDirection = 0;

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }
    public double getDirection() {
        return robotDirection;
    }

/*
    private static double angleTo(double fromX, double fromY, double toX, double toY) {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        double diffX = x1 - x2;
        double diffY = y1 - y2;
        return Math.sqrt(diffX * diffX + diffY * diffY);
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

    public Target getTarget() {
        return target;
    }

    public void setTarget(Point point) {
        this.target.setTargetPosition(point);
    }*/

    private void moveRobot(double angularVelocity) {
        velocity = Math.applyLimits(maxVelocity, 0, maxVelocity);
        angularVelocity = Math.applyLimits(angularVelocity, -maxAngularVelocity, maxAngularVelocity);
        double newX = positionX + velocity / angularVelocity *
                (java.lang.Math.sin(robotDirection  + angularVelocity * duration) -
                        java.lang.Math.sin(robotDirection));
        if (!Double.isFinite(newX)) {
            newX = positionX + velocity * duration * java.lang.Math.cos(robotDirection);
        }
        double newY = positionY - velocity / angularVelocity *
                (java.lang.Math.cos(robotDirection  + angularVelocity * duration) -
                        java.lang.Math.cos(robotDirection));
        if (!Double.isFinite(newY)) {
            newY = positionY + velocity * duration * java.lang.Math.sin(robotDirection);
        }
        positionX = newX;
        positionY = newY;
        double newDirection = Math.asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
        if (positionX < 0) {newDirection = -540; positionX = 10;}
        else if (positionY > 400) {newDirection = -8; positionY = 390;}
        else if (positionX > 500) {newDirection = 8; positionX = 395;}
        else if (positionY < 0) {newDirection = 8; positionY = 10;}
        else newDirection = Math.asNormalizedRadians(robotDirection + angularVelocity * duration);
        robotDirection = newDirection;
    }

    @Override
    public void update() {
        double distance = Math.distance(target.getX(), target.getY(),
                positionX, positionY);
        if (distance < 0.5){
            return;
        }
        double angleToTarget = Math.angleTo(positionX, positionY,
                target.getX(), target.getY());
        double angularVelocity = 0;
        if (java.lang.Math.abs(robotDirection - angleToTarget) < 10e-7) {
            angularVelocity = robotDirection;
        } else if (robotDirection >= java.lang.Math.PI) {
            if (robotDirection - java.lang.Math.PI < angleToTarget && angleToTarget < robotDirection)
                angularVelocity = -Robot.maxAngularVelocity;
            else
                angularVelocity = Robot.maxAngularVelocity;
        } else {
            if (robotDirection < angleToTarget && angleToTarget < robotDirection + java.lang.Math.PI)
                angularVelocity = Robot.maxAngularVelocity;
            else
                angularVelocity = -Robot.maxAngularVelocity;
        }
        moveRobot(angularVelocity);
    }
}
