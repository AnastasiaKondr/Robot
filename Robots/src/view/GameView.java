package view;
import model.GameModel;
import view.draw.RobotDrawer;
import view.draw.TagretDrawer;


import java.awt.*;

import javax.swing.JPanel;


public class GameView extends JPanel
{
    private final GameModel gameModel;
    RobotDrawer robotDrawer;
    TagretDrawer targetDrawer;

    public GameView(GameModel gameModel)
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
        robotDrawer.draw(gameModel.getRobot(), g2d);
        targetDrawer.draw(gameModel.getTarget(), g2d);
    }
}
