package model;

import java.awt.*;

public class Target implements Entity{
    private volatile int x;
    private volatile int y;

    public Target() {
        this.x = 150;
        this.y = 100;
    }

    public Target(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTargetPosition(Point p) {
        setX(p.x);
        setY(p.y);
    }
    protected Point getTargetPosition() {
        return new Point(getX(), getY());
    }

    @Override
    public void update() {

    }
}
