package game.model;

public class Target {
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
}
