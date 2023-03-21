package game.model;

import game.view.GameVisualizer;
import game.view.GameWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameVisualModel {
    private GameModel gameModel;
    private GameWindow gameWindow;
    private final java.util.Timer timer = initTimer();

    private static java.util.Timer initTimer() {
        return new Timer("events generator", true);
    }

    public GameVisualModel(GameModel gameModel, GameWindow gameWindow) {
        this.gameModel = gameModel;
        this.gameWindow = gameWindow;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameModel.setDimension(gameWindow.getSize());
                getGameView().updateView();
            }
        }, 0, 50);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameModel.updateModel();
            }
        }, 0, 10);
        gameWindow.getGameView().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameModel.setTargetPosition(e.getPoint());
                getGameView().repaint();
            }
        });
    }

    public GameVisualizer getGameView() {
        return gameWindow.getGameView();
    }
}
