package model;

import java.awt.*;

public class GameModel {
    GameState gameState;
    private Dimension dimension;

    public GameModel() {
        gameState = new GameState();
    }
    public GameState getGameState(){return gameState;}
    public void change(Point p){
        gameState.changeTarget(p);
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
    public Dimension getDimension() {
        return this.dimension;
    }

    public void updateModel() {
        gameState.update();
    }


    public Robot getRobot() {
        return gameState.getRobot();
    }

    public Target getTarget() {
        return gameState.getTarget();
    }

}
