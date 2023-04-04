package viewmodel;

import log.Logger;
import model.Entity;
import model.GameModel;
import model.Robot;
import model.Target;
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
    private GameWindow gameWindow;
    private Entity entity;
    private final Robot robot;
    private final Target target;
    private final GameView gameView;
    private final java.util.Timer timer = initTimer();
    private static java.util.Timer initTimer() {
        return new Timer("events generator", true);
    }

    public GameViewModel(GameModel gameModel, GameWindow gameWindow) {
        this.gameModel = gameModel;
        this.gameWindow = gameWindow;

        Map<Class<? extends Entity>, Drawer<?>> drawerBinder = new HashMap<>();
        RobotDrawer robotDrawer = new RobotDrawer();
        drawerBinder.put(robotDrawer.getDrawingClass(), robotDrawer);

        drawerBinder.get(entity.getClass()).draw(entity, Graphics2D g);

        gameView = new GameView(gameModel);
        robot = gameModel.getRobot();
        target = gameModel.getTarget();

        timerInit();
        userUpdate();
    }



        /*timer.schedule(new TimerTask() {
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
        });*/
    private void timerInit() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameModel.setDimension(gameWindow.getSize());
                gameModel.updateModel();

            }
        }, 0, 50);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameModel.updateModel();
            }
        }, 0, 10);
    }
    private void userUpdate(){
        //ViewModel также занимается прослушиванием необходимых событий окна (например, нажатия мыши) и передаёт данные в модель
        gameWindow.getGameView().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                target.setTargetPosition(e.getPoint());
                getGameView().repaint();
            }
        });
        gameWindow.getGameView().addComponentListener(new ComponentAdapter() {
           /*@Override
            public void componentResized(final ComponentEvent e) {
                super.componentResized(e);
                System.out.println("resize");
                gameModel.setDimension((gameWindow.getSize()));
                System.out.println(gameModel.getDimension());
            }*/
        });
    }

    public GameView getGameView() {
        return gameWindow.getGameView();
    }

    //sheduler на обновление игры, в котором она вызывает метод gameModel.update()
    public void startGame(int interval) {
        ScheduledExecutorService scheduler;
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //gameModel.updateModel();
                robot.update();
                gameView.updateView();
            }
        }, 0, interval, TimeUnit.MILLISECONDS);
    }
    //sheduler на рендер кадра, в котором она извлекает все имеющиеся Entity из модели и для каждого из них вызывает соответствующий Drawer
    private void frame(HashMap binder){

    }
}
