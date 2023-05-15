package view;
import model.EntityStateProvider;
import model.GameModel;
import view.draw.RobotDrawer;
import view.draw.TagretDrawer;


import java.awt.*;

import javax.swing.JPanel;


public class GameView extends JPanel
{
    RobotDrawer robotDrawer;
    TagretDrawer targetDrawer;

    private final EntityStateProvider entityState;
    public GameView(EntityStateProvider provider)
    {
        this.entityState = provider;
        robotDrawer = new RobotDrawer();
        targetDrawer = new TagretDrawer();
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
        robotDrawer.draw(entityState.getCurrentRobot(), g2d);
        targetDrawer.draw(entityState.getCurrentTarget(), g2d);
    }
}
