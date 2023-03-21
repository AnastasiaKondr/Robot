package game.view;
import game.model.GameModel;
import game.model.RobotDrawer;
import game.model.TagretDrawer;


import javax.swing.*;
import java.awt.*;

import javax.swing.JPanel;


public class GameVisualizer extends JPanel
{
    private final GameModel gameModel;
    RobotDrawer robotDrawer;
    TagretDrawer targetDrawer;

    public GameVisualizer(GameModel gameModel)
    {
        robotDrawer = new RobotDrawer();
        targetDrawer = new TagretDrawer();
        this.gameModel = gameModel;
        setDoubleBuffered(true);
    }

    public void updateView() {
        onRedrawEvent();
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        robotDrawer.drawRobot(g2d,  gameModel.getRobot());
        targetDrawer.drawTarget(g2d, gameModel.getTarget());
    }
}
