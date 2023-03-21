package game.model;

public class Robot {
    private double positionX = 100;
    private double positionY = 100;

    public static final double maxVelocity = 0.05;
    public static final double maxAngularVelocity = 0.005;

    private volatile double robotDirection = 0;


    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getRobotDirection() {
        return robotDirection;
    }

    public void setRobotDirection(double robotDirection) {
        this.robotDirection = robotDirection;
    }
}
