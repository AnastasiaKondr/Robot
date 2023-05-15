package model;

import java.awt.*;

public class GameState {

    private Robot robot;
    private Target target;

    public GameState() {
        this.target = new Target();
        this.robot = new Robot(target);
    }

    public void changeTarget(Point p) {
        target.setX(p.x);
        target.setY(p.y);
    }

    public Robot getRobot() {
        return robot;
    }

    public Target getTarget() {
        return target;
    }

    public void update(){
        robot.update();
    }
}
