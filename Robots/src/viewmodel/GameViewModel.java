package viewmodel;

import log.Logger;
import model.*;
import model.Robot;
import view.GameView;
import view.GameWindow;
import view.draw.Drawer;
import view.draw.RobotDrawer;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameViewModel {
    private GameModel gameModel;
    private final GameView gameView;
    private final java.util.Timer timer = initTimer();
    private static java.util.Timer initTimer() {
        return new Timer("events generator", true);
    }

    public GameViewModel(GameModel gameModel, GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;
        //Map<Class<? extends Entity>, Drawer<?>> drawerBinder = new HashMap<>();
        RobotDrawer robotDrawer = new RobotDrawer();
        //drawerBinder.put(robotDrawer.getDrawingClass(), robotDrawer);
        //drawerBinder.get(entity.getClass()).draw(entity, Graphics2D g);

        timerInit();
        //startGame(10);
        gameView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameModel.change(e.getPoint());
                gameView.repaint();
            }
        });
    }

    private void timerInit() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameView.updateView();
            }
        }, 0, 50);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameModel.updateModel();
            }
        }, 0, 10);
    }

    public GameView getGameView() {
        return gameView;
    }
}
